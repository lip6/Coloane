package fr.lip6.move.coloane.apicami.communications;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Cette classe construit
 * <ul>
 * 	<li>La socket de communcation avec la plate-forme et la connecte</li>
 * 	<li>Un objet Listener</li>
 * 	<li>Un objet Speaker</li>
 * </ul>
 *
 *  @author Jean-Baptiste Voron
 */
public class ComObjects {
	/** Le logger */
	private static Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

	private Listener listener;

	private Speaker speaker;

	private Socket socket;


	/**
	 * Constructeur
	 * @param ip L'adresse IP de la plate-forme
	 * @param port Le port sur lequel écoute la plate-forme
	 * @throws IOException En cas de problème de communication global
	 */
	public ComObjects(String ip, int port) throws IOException {
		/** Création de la socket */
		 this.socket = new Socket();
		 this.socket.connect(new InetSocketAddress(ip, port));
		LOGGER.finer("Creation de la socket");

		this.listener = new Listener(this.socket);
		this.speaker = new Speaker(this.socket);
	}

	/**
	 * @return le listener
	 */
	public final Listener getListener() {
		return listener;
	}

	/**
	 * @return le speaker
	 */
	public final Speaker getSpeaker() {
		return speaker;
	}
}
