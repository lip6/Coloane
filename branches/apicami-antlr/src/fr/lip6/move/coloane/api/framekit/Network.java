package fr.lip6.move.coloane.api.framekit;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public final class Network {

	private Socket socket;
	private FkOutputStream output;
	private FkInputStream input;
	
	public Network(String ip, int port) throws IOException {
		this.socket = new Socket( InetAddress.getByName(ip) , port);
		this.output = new FkOutputStream(this.socket.getOutputStream());
		this.input = new  FkInputStream(this.socket.getInputStream());
	}
	
	public InputStream getInput() throws IOException {
		//this.reconnect();
		// System.out.println("Network.getInput()");
		//return this.socket.getInputStream();
		return this.input;
	}

	public OutputStream getOutput() throws IOException {
		//this.reconnect();
		return this.output;
	}

//	private void reconnect() throws IOException {		
//		if( socket.isClosed() )
//			socket.connect(this.socket.getRemoteSocketAddress());
//	}
}
