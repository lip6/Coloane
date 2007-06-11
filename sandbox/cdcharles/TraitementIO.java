/**
 * Classe pour le traitement des entrées sorties des sockets
 */
package proxyDimitri;

import java.net.*;
import java.io.*;
import java.lang.Thread;
import java.util.*;
/**
 * @author aragorn
 * 
 */
public class TraitementIO extends Thread {
	
	private Socket socClient;

	private Socket socServer;
 
	private BufferedReader brC, brS;

	private PrintWriter pwC, pwS;

	private DataInputStream diC, diS;

	private DataOutputStream doC, doS;

	private byte[] mess;

	private int bytesLues;

	private final int tailleMsg = 4096;

	private int connex;

	public TraitementIO(Socket socClient, Socket socServer) {
		this.socClient = socClient;
		this.socServer = socServer;

		try {
			this.diC = new DataInputStream(this.socClient.getInputStream());
			this.brC = new BufferedReader(new InputStreamReader(this.socClient
					.getInputStream()));
			this.doC = new DataOutputStream(this.socClient.getOutputStream());
			this.pwC = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(this.socClient.getOutputStream())));

			this.diS = new DataInputStream(this.socServer.getInputStream());
			this.brS = new BufferedReader(new InputStreamReader(this.socServer
					.getInputStream()));
			this.doS = new DataOutputStream(this.socServer.getOutputStream());
			this.pwS = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(this.socServer.getOutputStream())));

		} catch (IOException e) {
			System.out.println("Flux introuvable");
		}

		mess = new byte[this.tailleMsg];
		this.bytesLues = 0;
		this.connex = 0;
	}
	
	public void run(){
		
		
		while(this.socClient.isConnected() && this.socServer.isConnected()){
			
			System.out.println("Connexion :" + this.connex);
			try {
				bytesLues = this.diC.read(mess);				
				System.out.println("Bytes lues par client -->" + this.bytesLues + "\n" + new String(mess));
				this.doS.write(mess);
			}catch(IOException e){ System.out.println("Erreur I/O client"); break;}
			
			try{
				bytesLues = this.diS.read(mess);
				System.out.println("Bytes lues par server -->" + this.bytesLues + "\n" + new String(mess));
				this.doC.write(mess);
			}catch(IOException e){ System.out.println("Erreur I/O server"); break;}
			this.connex++;
		}
		try {
			this.diC.close();
			this.diS.close();
			this.doC.close();
			this.doS.close();
		} catch(IOException e) {System.out.println("Erreur de fermeture de buffers\n"); e.printStackTrace();}
		
		try{
			this.socClient.close();
			this.socServer.close();
		}catch(IOException e) {System.out.println("Erreur de fermeture des sockets\n"); e.printStackTrace();}
	}
}
