
package fr.lip6.move.coloane.api.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Vector;  

import fr.lip6.move.coloane.api.exceptions.CommunicationCloseException;
import fr.lip6.move.coloane.api.log.LogsUtils;
import fr.lip6.move.coloane.api.main.Api;

/**
 * Cette class g�re les communications de bas niveau avec la plateforme
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

	/** Fournit des outils necessaires pour formater les messsages de logs*/ 
	private final LogsUtils logsutils = new LogsUtils();

	/**
	 * Ecrire sur le flux de sortie vers Framekit
	 * @param commande commande � envoyer � FrameKit
	 * @return Retourne true si OK
	 * @throws CommunicationCloseException si la lecture se passe mal
	 */
	public boolean writeCommande(byte[] commande) throws CommunicationCloseException {
		Api.apiLogger.entering("ComLowLevel", "writeCommand", commande);
		try {
			if (!this.socket.isOutputShutdown()) {

				// <DEBUG>
				String msg = new String(commande, 4, commande.length - 4);
				Api.apiLogger.finer("[CO-->FK] : " + msg);
				//System.out.println("[CO-->FK] : " + msg);
				// </DEBUG>

				this.socketOutput.write(commande, 0, commande.length);
				Api.apiLogger.exiting("ComLowLevel", "writeCommande", true);
				return true;
			} else {
				Api.apiLogger.exiting("ComLowLevel", "writeCommande", false);
				return false;
			}
		} catch (Exception e) {
			//System.err.println("Erreur lors de l'�criture sur la socket" + e.getMessage());
			CommunicationCloseException cce = new CommunicationCloseException(e.getMessage());
			Api.apiLogger.throwing("ComLowLevel", "writeCommande", cce);
			Api.apiLogger.warning("Erreur lors de l'ecriture sur la socket");
			Api.apiLogger.warning(logsutils.StackToString(e));
			throw cce;
		}
	}


	/**
	 * Lis une commande sur le flux d'entr�e
	 * @return Vector L'ensemble des commande qu'on a lues
	 */
	public synchronized Vector readCommande() {
		Api.apiLogger.entering("ComLowLevel", "readCommande");
		Vector<String> liste = new Vector<String>();    	
		int longueurMessage = 0;
		String commande = "";

		try {

			// Lecture des 4 premiers octets donnant la taille du message
			longueurMessage = this.socketInput.readInt();

			// Lecture de la socket selon la longueur donnee.
			for (int j = 0; j < longueurMessage; j++) {
				char monChar = (char) this.socketInput.readByte();

				if (monChar == '\n') {
					// Nouvelle commande detectee
					Api.apiLogger.finer("[CO<--FK] : "+commande);
					//System.out.println("[CO<--FK] : "+commande);
					liste.add(commande);
					commande = "";
				} else {
					commande += monChar;
				}
			}
			Api.apiLogger.finer("[CO<--FK] : " + commande);
			//System.out.println("[CO<--FK] : "+commande);
			liste.add(commande);
			Api.apiLogger.exiting("ComLowlevel", "readCommande", liste);
			return liste;

		} catch (Exception e) {
			Api.apiLogger.throwing("ComLowLevel", "readCommande", e);
			Api.apiLogger.warning(logsutils.StackToString(e));
			Api.apiLogger.exiting("ComLowLevel", "readCommande", null);
			return null;	
		}
	}


	/**
	 * Permet de creer une socket (utilisation des parametres par d�faut)
	 * @return Retourne true si OK
	 * @throws Exception une exception est levee si il y a un probleme
	 */
	public boolean createCom(String ip, int port) throws Exception {
		Object[] param = {ip, port};
		Api.apiLogger.entering("ComLowLevel", "createCom", param);
		this.socket = new Socket();

		// Ouverture d'une socket
		try {
			this.socket.connect(new InetSocketAddress(ip, port));
		} catch (Exception e) {
			Api.apiLogger.warning("Erreur lors de la creation de la socket :" + logsutils.StackToString(e));
			//System.err.println("Erreur lors de la creation de la socket :" + e.getMessage());
			throw e;
		}

		this.socketInput = new DataInputStream(this.socket.getInputStream());
		this.socketOutput = new DataOutputStream(this.socket.getOutputStream());
		Api.apiLogger.exiting("ComLowLevel", "createCom", true);
		return true;
	}

	/**
	 * Ferme les connexions ouvertes
	 * @return boolean R�ussite de l'op�ration
	 */
	public boolean closeCom() {
		Api.apiLogger.entering("ComLowLevel", "closeCom");
		try {
			this.socketInput.close();
			this.socketOutput.close();
			Api.apiLogger.exiting("ComLowLevel", "closeCom", true);
			return true;
		} catch (IOException e) {
			Api.apiLogger.throwing("ComLowLevel", "closeCom",e);
			Api.apiLogger.warning(logsutils.StackToString(e));
			Api.apiLogger.exiting("ComLowLevel", "closeCom", false);
			return false;
		}
	}
}
