package proxyDimitri;

import java.lang.Thread;
import java.net.*;
import java.io.*;

public class MainProxy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket proxy;
		Socket serv;
		Socket client;
		
		InetSocketAddress addProxy;
		InetSocketAddress addServ;
		
		int portDistant;
		final int portProxy = 8080;
		
		String serveur;
		String locale="localhost";
		
		if(args.length != 2){
			System.out.println("Usage: Addresse_Serveur_Distant Port_Serveur_Distant");
			System.exit(0);
		}
		try{
		
		portDistant = Integer.parseInt(args[1]);
		serveur = args[0];
		
		addServ = new InetSocketAddress(serveur, portDistant);
		addProxy = new InetSocketAddress(locale, portProxy);
		proxy = new ServerSocket();
		proxy.bind(addProxy);
		System.out.println("Initialisation.....");
		System.out.println("Serveur initialisé sur " + addProxy +":"+portDistant);
		
		while(true){
			client = proxy.accept();
			serv = new Socket();
			serv.connect(addServ);
			
			TraitementIO t = new TraitementIO(client, serv);
			t.start();
			t.run();
		}
		
	}catch(IOException e){e.printStackTrace();}
	
	
	}

}
