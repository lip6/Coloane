package fr.lip6.move.coloane.api.camiParser;

import java.io.IOException;
import java.io.InputStream;

import org.antlr.runtime.CharStream;

public class ANTLRSocketStreamDebug implements CharStream {

	 ANTLRSocketStream input;
	
	public ANTLRSocketStreamDebug(InputStream input) {
		this.input = new ANTLRSocketStream(input);
	}
	
	public int LT(int i) {
		return LA(i);
	}
	
	public int getCharPositionInLine() {
		System.err.println("-> getCharPositionInLine()");
		int result = this.input.getCharPositionInLine();
		System.err.println("<- getCharPositionInLine()");
		return result;
	}

	public int getLine() {
		System.err.println("-> getLine()");
		int result = this.input.getLine();
		System.err.println("<- getLine()");
		return result;
	}

	public void setCharPositionInLine(int pos) {
		System.err.println("-> setCharPositionInLine(" + pos + ")");
		this.input.setCharPositionInLine(pos);
		System.err.println("<- setCharPositionInLine(" + pos + ")");		
	}

	public void setLine(int line) {
		System.err.println("-> setLine(" + line + ")");
		this.input.setLine(line);
		System.err.println("<- setLine(" + line + ")");
	}

	public String substring(int start, int stop) {
		System.err.println("-> substring(" + start + ", " + stop + ")");
		String result = this.input.substring(start, stop);
		System.err.println("<- substring(" + start + ", " + stop + ")");
		return result;
	}

	public int LA(int i) {
		System.err.println("-> LA(" + i + ")");
		int result = this.input.LA(i);
		System.err.println("<- LA(" + i + ")");
		return result;
	}

	public void consume() {
		System.err.println("-> consume()");
		this.input.consume();
		System.err.println("<- consume()");
	}

	public int index() {
		System.err.println("-> index()");
		int result = this.input.index();
		System.err.println("<- index()");
		return result;
	}

	public int mark() {
		System.err.println("-> mark()");
		int result = this.input.mark();
		System.err.println("<- mark()");
		return result;
	}

	public void release(int marker) {
		System.err.println("-> release(" + marker + ")");
		this.input.release(marker);
		System.err.println("<- release(" + marker + ")");
	}

	public void rewind() {
		System.err.println("-> rewind()");
		this.input.rewind();
		System.err.println("<- rewind()");
	}

	public void rewind(int marker) {
		System.err.println("-> rewind(" + marker + ")");
		this.input.rewind();
		System.err.println("<- rewind(" + marker + ")");
	}

	public void seek(int index) {
		System.err.println("-> seek(" + index + ")");
		this.input.seek(index);
		System.err.println("<- seek(" + index + ")");
	}

	public int size() {
		System.err.println("-> size()");
		int result = this.input.size();
		System.err.println("<- size()");
		return result;
	}
	
	public void fillBufffer() throws IOException {
		System.err.println("-> fillBuffer()");
		this.input.fillBufffer();
		System.err.println("<- fillBuffer()");
	}

}
