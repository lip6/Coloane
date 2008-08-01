package fr.lip6.move.coloane.api.communications;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class FkInputStream extends FilterInputStream {

	private int nbToRead;

	public FkInputStream(InputStream input) {
		super(input);
		this.nbToRead = 0;
	}

	public int available() throws IOException {
		return super.in.available();
	}

	public int read() throws IOException {
		byte[] b = new byte[1];		
		if( this.read(b,0,1) == -1 )
			return -1;		
		return new Byte(b[0]).intValue();
	}

	public int read(byte[] b) throws IOException {
		return this.read(b,0,b.length);
	}

	public int read(byte[] b, int offset, int length) throws IOException {

		int nbRead = 0;
		if( this.nbToRead == 0 ) {
			byte[] size = new byte[4];
			if( super.in.read(size,0,4) == -1 )
				return -1;
			this.nbToRead = new Byte(size[3]).intValue();
		}

		if (length > this.nbToRead )
			length = this.nbToRead;

		if( (nbRead = super.in.read(b,0,length)) == -1 )
			return -1;
		this.nbToRead -= nbRead;
		System.err.println("FkInputStream has read: " + new String(b));
		return nbRead;
	}

}
