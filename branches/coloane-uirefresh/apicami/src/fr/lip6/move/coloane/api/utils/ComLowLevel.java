package fr.lip6.move.coloane.api.utils;

import fr.lip6.move.coloane.api.exceptions.CommunicationCloseException;
import fr.lip6.move.coloane.api.main.Api;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Vector;

/**
 * Cette class gere les communications de bas niveau avec la plateforme
 * FrameKit, et en particulier la gestion des sockets.
 * Mais aucun traitement des commandes CAMI n'est fait ici.
 */

public class ComLowLevel {

	/** Identifiant de socket permettant de dialoguer avec la plateforme */
	private Socket socket;

	/** Objet que vont partager tous les threads pour ecrire sur le canal de communication */
	private DataOutputStream socketOutput;

	/** Objet qui permet de recevoir les communications entrantes */
	private DataInputStream socketInput;

	/**
	 * Ecrire sur le flux de sortie vers Framekit
	 * @param commande commande a envoyer a FrameKit
	 * @return Retourne true si OK
	 * @throws CommunicationCloseException si l'ecriture se passe mal
	 */
	public final boolean writeCommande(byte[] commande) throws CommunicationCloseException {
		try {
			if (!this.socket.isOutputShutdown()) {
				String msg = new String(commande, 4, commande.length - 4);
				Api.getLogger().finer("[CO-->FK] : " + msg);
				this.socketOutput.write(commande, 0, commande.length);
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			Api.getLogger().warning("Erreur lors de l'ecriture sur la socket : " + e.getMessage());
			throw new CommunicationCloseException(e.getMessage());
		}
	}


	/**
	 * Lis une commande sur le flux d'entree
	 * @return Vector L'ensemble des commande qu'on a lues
	 * @throws CommunicationCloseException En cas d'echec de lecture sur la socket
	 */
	public final synchronized Vector<String> readCommande() throws CommunicationCloseException {
		Vector<String> liste = new Vector<String>();
		String commande = "";

		try {
			// Lecture des 4 premiers octets donnant la taille du message
			int longueurMessage = this.socketInput.readInt();

			// Lecture de la socket selon la longueur donnee.
			for (int j = 0; j < longueurMessage; j++) {
				char monChar = (char) this.socketInput.readByte();

				if (monChar == '\n') {
					// Nouvelle commande detectee
					Api.getLogger().finer("[CO<--FK] : " + commande);
					liste.add(commande);
					commande = "";
				} else {
					commande += monChar;
				}
			}
			Api.getLogger().finer("[CO<--FK] : " + commande);
			liste.add(commande);
			return liste;

		} catch (EOFException e) {
			Api.getLogger().warning("La socket est vide...");
			throw new CommunicationCloseException("Socket vide (lecture)");
		} catch (IOException e) {
			Api.getLogger().warning("Erreur lors de la lecture sur la socket");
			throw new CommunicationCloseException(e.getMessage());
		}
	}


	/**
	 * Creation d'une socket (utilisation des parametres par defaut)
	 * @return Retourne true si OK
	 * @throws IOException En cas d'echec lors de la creation de la socket
	 */
	public final boolean createCom(String ip, int port) throws IOException {
		this.socket = new Socket();

		// Ouverture d'une socket
		try {
			this.socket.connect(new InetSocketAddress(ip, port));
		} catch (IOException e) {
			Api.getLogger().warning("Erreur lors de la creation de la socket :");
			throw e;
		}

		this.socketInput = new DataInputStream(this.socket.getInputStream());
		this.socketOutput = new DataOutputStream(this.socket.getOutputStream());
		return true;
	}

	/**
	 * Ferme le flux de donnees entrant<br>
	 * Ferme le flux de donnees sortant<br>
	 * Ferme la socket<br>
	 * @return boolean Indicateur de reussite de l'operation
	 */
	public final boolean closeCom() {
		try {
			this.socketInput.close();
			this.socketOutput.close();
			this.socket.close();
			return true;
		} catch (IOException e) {
			Api.getLogger().warning("Erreur lors de la destruction de la socket :");
			return false;
		}
	}
}
