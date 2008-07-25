package fr.lip6.move.coloane.api.FkCommunication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Cette class gère les communications de bas niveau avec la plateforme FrameKit, en particulier la gestion des sockets.
 * Elle offre des méthodes de création de socket, de lecture et d'écriture sur cette socket.
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 * TODO : Gérer le cas où le clientse trouve derrière un proxy
 *
 */

public class FkComLowLevel {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

	/** Socket de communication avec FrameKit */
	private Socket socket;

	/** Objet pour écrire dans la socket */
	private DataOutputStream socketOutput;

	/** Objet pour lire à partir de la socket */
	private DataInputStream socketInput;


	/**
	 * Constructeur : initialisation de la connexion:
	 * <ul>
	 * 	<li>Création de la socket</li>
	 * 	<li>Création de DataInputStream</li>
	 * 	<li>Création de DataOutPutStream</li>
	 * </ul>
	 * @param ip Adresse IP de la machine hébérgeant FrameKit.
	 * @param port Port d'écoute de FrameKit.
	 * @throws IOException En cas de problème
	 */
	public FkComLowLevel(String ip, int port) throws IOException {
		this.socket = new Socket();
		try {
			this.socket.connect(new InetSocketAddress(ip, port));
		} catch (IOException e) {
			throw e;
		}

		// Ouvrir un flux DataInputStream sur la socket
		this.socketInput = new DataInputStream(this.socket.getInputStream());

		// Ouvrir un flux DataOutputStream sur la socket
		this.socketOutput = new DataOutputStream(this.socket.getOutputStream());
	}


	/**
	 * Cette méthode lit un ensemble de commandes (flux) à partir de la socket.
	 * Les commandes sont visibles à un niveau plus haut (Listener)
	 * @return une liste de commandes CAMI
	 * @throws IOException En cas de problème de lecture
	 */
	public final String readCommand() throws IOException {
		String commands = "";
		try {
			// Lecture des 4 premiers octets donnant la taille du message
			int messageLength = this.socketInput.readInt();

			// Lecture selon la longueur donnée
			for (int i = 0; i < messageLength; i++) {
				char car = (char) this.socketInput.readByte();
				commands += car;
			}
		} catch (IOException e) {
			throw e;
		}
		LOGGER.finest("[FK-->CO] : " + commands);
		return commands;
	}


	/**
	 * Ecriture sur la socket
	 * @param command LA commande à écrire
	 * @throws IOException En cas de problème d'écriture
	 */
	public final void writeCommand(byte[] command) throws IOException {
		try {
			this.socketOutput.write(command, 0, command.length);
			String debug = new String(command, 4, command.length - 4);
			LOGGER.finest("[CO-->FK] : " + debug);
		} catch (IOException e) {
			throw e;
		}
	}


	/**
	 * Cette méthode s'occupe de fermer la connexion
	 */

	public final void closeCommunication() {
		try {
			// Fermer le flux de lecture de la socket
			this.socketInput.close();

			// Fermer le flux d'écriture de la socket
			this.socketOutput.close();

			// Fermer la socket
			this.socket.close();

		} catch (IOException e) {
			LOGGER.warning("Echec de la fermeture de connexion de bas niveau avec la plate-forme");
			e.printStackTrace();
		}
	}
}
