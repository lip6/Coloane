package fr.lip6.move.coloane.api.FkCommunication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

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
	 * Cette méthode lit un nombre de bytes donné en paramètre
	 * à partir de la socket. Les commandes sont visibles à un niveau
	 * plus haut (Listener)
	 * @return une liste de commandes
	 * @throws IOException
	 */
	public ArrayList<String> readCommand() throws IOException{

		// TODO
		// TODO fo pas oublier de logguer

		// le tableau de commandes à retourner
		ArrayList<String> list = new ArrayList<String>();
		String command = "";

		// Lecture des 4 premiers octets donnant la taille du message
		int messageLength = this.socketInput.readInt();

		// Lecture selon la longueur donnée
		for(int i=0; i<messageLength; i++){
			try{
				byte car = this.socketInput.readByte();
				if(car == '\n'){
					// Nouvelle commande
					list.add(command);
					command = "";
				}
				else{
					// Commande en cours de lecture
					command += car;
				}

			} catch (IOException e) {
				// TODO Logguer
				throw e;
			}
		}
		return list;
	}


	/**
	 * Cette méthode écrit les commandes à envoyer à la plateforme
	 * FrameKit
	 * @param
	 * @throws IOException
	 */
	boolean writeCommand(byte[] commandd) throws IOException{
		// TODO
		// TODO logguer

		try {
			this.socketOutput.write(commandd, 0commandandd.length);
			return true;
		} catch (IOException e) {
			// TODO logguer
			throw e;
		}
	}


	/**
	 * Cette méthode s'occupe de fermer la connexion
	 * @throws IOException
	 */

	void closeCommunication() throws IOException{

		try {
			/** fermer le flux de lecture de la socket */
			this.socketInput.close();

			/** fermer le flux d'écriture de la socket */
			this.socketOutput.close();

			/** fermer la socket */
			this.socket.close();

		} catch (IOException e) {
			// TODO logguer
			throw e;
		}

	}

}
