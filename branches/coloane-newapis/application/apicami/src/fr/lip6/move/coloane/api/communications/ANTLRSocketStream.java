package fr.lip6.move.coloane.api.communications;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.CharStream;

public final class ANTLRSocketStream implements CharStream {

	/** Represents the stream which we are reading from */
	private InputStream input;

	/** Keep track of the position in the line*/
	private int charPositionInLine;

	/** Keep track of the line number*/
	private int lineNumber;

	private StringBuilder data;
	private int pos;
	private int count;

	private int markDepth;
	private List<CharStreamState> markers;
	private int lastMarker;

	private boolean encounteredEOF;

	public ANTLRSocketStream(InputStream input) {
		super();
		this.input = input;
		this.charPositionInLine = 0;
		this.lineNumber = 1;
		this.pos = 0;
		this.count = 0;
		this.data = new StringBuilder(4096);
		this.markDepth = 0;
		this.markers = null;
		this.lastMarker = 0;

		this.encounteredEOF = true;
	}

	public int LT(int i) {
		return LA(i);
	}

	public int getCharPositionInLine() {
		return charPositionInLine;
	}

	public int getLine() {
		return lineNumber;
	}

	public void setCharPositionInLine(int pos) {
		this.charPositionInLine = pos;
	}

	public void setLine(int line) {
		this.lineNumber = line;
	}

	public String substring(int start, int stop) {
		return data.substring(start, stop+1);
	}

	public int LA(int i) {

		try {
			if (this.encounteredEOF && (this.pos == this.count)) {
				this.fillBufffer();
				this.encounteredEOF = false;
			}

			if (i == 0) {
				return 0;
			}

			if (i < 0) {
				++i;
				if ((this.pos + i - 1) < 0) {
					this.encounteredEOF = true;
					return CharStream.EOF;
				}
			}

			if ((this.pos + i - 1) >= this.count) {
				this.encounteredEOF = true;
				return CharStream.EOF;
			}

			return data.charAt(pos + i - 1);

		} catch (IOException e) {
			return CharStream.EOF;
		}
	}

	public void consume() {

		try {
			this.fillBufffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (this.pos < this.count) {

			this.charPositionInLine++;
			if (data.charAt(pos) == '\n') {
				this.lineNumber++;
				this.charPositionInLine = 0;
			}
			this.pos++;
		}

	}

	public int index() {
		return this.pos;
	}

	public int mark() {
		if (this.markers == null) {
			this.markers = new ArrayList<CharStreamState>();
			this.markers.add(null); // depth 0 means no backtracking, leave blank
		}

		this.markDepth++;
		CharStreamState state = null;

		if (this.markDepth >= this.markers.size()) {
			state = new CharStreamState();
			this.markers.add(state);
		} else {
			state = this.markers.get(this.markDepth);
		}

		state.p = pos;
		state.line = lineNumber;
		state.charPositionInLine = charPositionInLine;

		this.lastMarker = this.markDepth;

		return this.markDepth;
	}

	public void release(int marker) {
		this.markDepth = marker;
		this.markDepth--;
	}

	public void rewind() {
		rewind(lastMarker);
	}

	public void rewind(int marker) {
		CharStreamState state = markers.get(marker);
		this.seek(state.p);
		this.lineNumber = state.line;
		this.charPositionInLine = state.charPositionInLine;
		this.release(marker);
	}

	public void seek(int index) {
		if (index <= this.pos) {
			this.pos = index;
			return;
		}
		while (this.pos < index) {
			this.consume();
		}
	}

	public int size() {
		return this.count;
	}

	public void fillBufffer() throws IOException {

		if (this.pos == this.count) {

			byte[] tmp = new byte[256];
			int nbRead = this.input.read(tmp);

			if  (nbRead != -1) {
				this.data.append(new String(tmp, 0, nbRead));
				this.count += nbRead;
			} else {
				throw new IOException();
			}
		}

	}
}
