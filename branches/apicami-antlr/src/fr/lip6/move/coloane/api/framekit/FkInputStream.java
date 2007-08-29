package fr.lip6.move.coloane.api.framekit;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class FkInputStream extends FilterInputStream {

	private InputStream input;
	private int nbToRead;
	
	public FkInputStream(InputStream input) {
		super(input);
		this.input = input;
		this.nbToRead = 0;
	}
	
	public int read(byte[] b, int offset, int length) throws IOException {
		
		int nbRead = 0;
		
		if( this.nbToRead == 0 ) {
			
			System.out.println("this.nbToRead == 0");
			
			byte[] size = new byte[4];
			if( input.read(size,0,4) == -1 )
				return -1;
			this.nbToRead = new Byte(size[3]).intValue();
			
			System.out.println("this.nbToRead = " + this.nbToRead);
		}
		
		ByteBuffer buffer = ByteBuffer.allocate(length);
		
		while( length > this.nbToRead ) {
			
			System.out.println("length > this.nbToRead");
			
			byte[] tmp = new byte[nbToRead];
			
			if( (nbRead = input.read(tmp) ) == 0 ) 
				return -1;
			
			buffer.put(tmp);
			
			System.out.println("buffer = " + new String(buffer.array()));
			
			byte[] size = new byte[4];
			if ( input.read(size,0,4) == -1 )
				return -1;
			this.nbToRead = new Byte(size[3]).intValue();
			
			System.out.println("this.nbToRead = " + this.nbToRead);
			
			length -= nbRead;
		} 
		
		// length <= this.nbToRead
		byte[] tmp = new byte[length];
		if( (nbRead = input.read(tmp)) == -1 )
			return -1;
		buffer.put(tmp);
		this.nbToRead -= nbRead;
		
		b = buffer.array();
		System.out.println("buffer = " + new String(buffer.array()));
		System.out.println("b = " + new String(b));
		
		return nbRead;
	}
	
}
