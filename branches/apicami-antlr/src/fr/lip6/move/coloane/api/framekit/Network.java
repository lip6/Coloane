package fr.lip6.move.coloane.api.framekit;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public final class Network {

	private Socket socket;
	private InputStream input;
	private OutputStream output;
	
	public Network(String ip, int port) throws UnknownHostException, IOException {
		this.socket = new Socket( InetAddress.getByName(ip) , port);
		this.output = this.socket.getOutputStream();
		this.input = this.socket.getInputStream();
	}
	
	public InputStream getInput() {
		return input;
	}

	public OutputStream getOutput() {
		return output;
	}
}
