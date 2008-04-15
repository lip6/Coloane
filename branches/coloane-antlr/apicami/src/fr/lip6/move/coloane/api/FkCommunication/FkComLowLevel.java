package fr.lip6.move.coloane.api.FkCommunication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author uu & kahoo
 * 
 * Cette class gère les communications de bas niveau avec la plateforme
 * FrameKit, en particulier la gestion des sockets.
 * Elle offre des méthodes de création de socket, de lecture et 
 * d'écriture sur cette socket.
 * 
 */

// TODO Ne pas oublier si on a le temps le cas dérrière proxy

public class FkComLowLevel {
	
	/** Socket de communication avec FrameKit */
	private Socket socket;
	
	/** Objet pour écrire dans la socket */
	private DataOutputStream socketOutput;

	/** Objet pour lire à partir de la socket */ 
	private DataInputStream socketInput;
	
	
	/**
	 * Constructeur : initialisation la connexion: Création de la 
	 * socket, création de DataInputStream et DataOutPutStream sur
	 * cette socket. 
	 * @param ip Adresse IP de la machine hébérgeant FrameKit.
	 * @param port : port d'écoute de FrameKit.
	 * @throws IOException 
	 */
	public FkComLowLevel(String ip, int port) throws IOException{
		
		this.socket = new Socket();
		try {
			this.socket.connect(new InetSocketAddress(ip, port));
		} catch (IOException e) {
			// TODO log ...
			throw e;
		}
		
		/** Ouvrir un flux DataInputStream sur la socket */
		this.socketInput = new DataInputStream(this.socket.getInputStream());
		
		/** Ouvrir un flux DataOutputStream sur la socket */
		this.socketOutput = new DataOutputStream(this.socket.getOutputStream());
		
	}
	
	
	/**
	 * Cette méthode lit de la socket une commandes reçue 
	 * de la plateforme FrameKit.
	 * @return une commande
	 */
	byte[] readCommand(){
		// TODO		
		return null;
	}

	/**
	 * Cette méthode écrit les commandes à envoyer à la plateforme
	 * FrameKit
	 *
	 */
	void writeCommand(){
		// TODO
	}

}
