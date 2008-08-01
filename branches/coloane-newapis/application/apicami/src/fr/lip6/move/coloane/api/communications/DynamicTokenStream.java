package fr.lip6.move.coloane.api.communications;

/*
 [The "BSD licence"]
 Copyright (c) 2005-2006 Terence Parr
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.util.List;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;

/** The most common stream of tokens is one where every token is buffered up
 *  and tokens are prefiltered for a certain channel (the parser will only
 *  see these tokens and cannot change the filter channel number during the
 *  parse).
 *
 *  This is a test variation where I try out a dynamic version that only
 *  fetches from lexer when you need it in LT(1) or whatever.
 */
public class DynamicTokenStream extends CommonTokenStream {
	public DynamicTokenStream(TokenSource tokenSource) {
		super(tokenSource);
	}

	public DynamicTokenStream(TokenSource tokenSource, int channel) {
		super(tokenSource, channel);
	}

	/** Load all tokens from the token source and put in tokens.
	 *  This is done upon first LT request because you might want to
	 *  set some token type / channel overrides before filling buffer.
	 */
	protected void fillBuffer() {
		throw new UnsupportedOperationException("should not be called");
	}

	public void fetch() {
		Token t = tokenSource.nextToken();
		// is there a channel override for token type?
		if ( channelOverrideMap!=null ) {
			Integer channelI = (Integer)
			channelOverrideMap.get(new Integer(t.getType()));
			if ( channelI!=null ) {
				t.setChannel(channelI.intValue());
			}
		}
		boolean discard = false;
		if ( discardSet!=null &&
				discardSet.contains(new Integer(t.getType())) )
		{
			discard = true;
		}
		else if ( discardOffChannelTokens && t.getChannel()!=this.channel ) {
			discard = true;
		}
		if ( !discard )	{
			int index = tokens.size();
			t.setTokenIndex(index);
			tokens.add(t);
		}
	}

	/** Fill between p and n; do nothing if n is p.  If p==-1 and n==0,
	 *  fill in first token.
	 */
	protected void fill(int n) {
		for (int i=p; i<n; i++) {
			fetch();
		}
		if ( p<0 ) {
			p=0; // init
		}
	}

	/** Given a start and stop index, return a List of all tokens in
	 *  the token type BitSet.  Return null if no tokens were found.  This
	 *  method looks at both on and off channel tokens.
	 *  Make sure we have at least stop tokens.
	 */
	public List getTokens(int start, int stop, BitSet types) {
		fill(stop);
		return super.getTokens(start, stop, types);
	}

	/** Get the ith token from the current position 1..n where k=1 is the
	 *  first symbol of lookahead.
	 */
	public Token LT(int k) {
		if ( k==0 ) {
			return null;
		}
		if ( k<0 ) {
			return LB(-k);
		}
		//System.out.println("LT(p="+p+","+k+")");
		fill(p+k);
		//System.out.println(tokens.get(p+k-1));
		int i = p;
		int n = 1;
		// find k good tokens
		while ( n<k ) {
			// skip off-channel tokens
			i = skipOffTokenChannels(i+1); // leave p on valid token
			n++;
		}
		return (Token)tokens.get(i);
	}

	/** Look backwards k tokens on-channel tokens */
	protected Token LB(int k) {
		//System.out.print("LB(p="+p+","+k+") ");
		if ( k==0 ) {
			return null;
		}
		if ( (p-k)<0 ) {
			return null;
		}

		int i = p;
		int n = 1;
		// find k good tokens looking backwards
		while ( n<=k ) {
			// skip off-channel tokens
			i = skipOffTokenChannelsReverse(i-1); // leave p on valid token
			n++;
		}
		if ( i<0 ) {
			return null;
		}
		return (Token)tokens.get(i);
	}

	public String toString(int start, int stop) {
		if ( start<0 || stop<0 ) {
			return null;
		}
		if ( stop>=tokens.size() ) {
			stop = tokens.size()-1;
		}
		StringBuffer buf = new StringBuffer();
		for (int i = start; i <= stop; i++) {
			Token t = (Token)tokens.get(i);
			buf.append(t.getText());
		}
		return buf.toString();
	}

	public String toString(Token start, Token stop) {
		if ( start!=null && stop!=null ) {
			return toString(start.getTokenIndex(), stop.getTokenIndex());
		}
		return null;
	}
}