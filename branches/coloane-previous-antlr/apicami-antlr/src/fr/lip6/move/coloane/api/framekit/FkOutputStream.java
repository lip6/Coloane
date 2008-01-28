package fr.lip6.move.coloane.api.framekit;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class FkOutputStream extends FilterOutputStream {

	public FkOutputStream(OutputStream out) {
		super(out);
	}
	
	public void write(byte[] data) throws IOException {
				
		byte[] toSend = new byte[data.length + 4];
		toSend[0] = 0;
		toSend[1] = 0;
		toSend[2] = 0;
		toSend[3] = (byte) data.length;
		
		for( int i = 0 ; i<data.length ; ++i)
			toSend[i+4] =  data[i];
		
		super.write(toSend);
	}
	
}
