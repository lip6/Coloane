	package fr.lip6.move.coloane.api.FkCommunication;

import java.io.IOException;

import fr.lip6.move.coloane.api.cami.CamiGenerator;
import fr.lip6.move.coloane.api.interfaces.IDialog;
import fr.lip6.move.coloane.api.interfaces.IModel;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;

/**
 *
 * @author UU & Kahoo
 * Le rôle de cette classe est d'offrir une interface de haut niveau
 * aux modules de API pour envoyer des commandes à FrameKit.
 */

public class Speaker implements ISpeaker{

	/** objet de communication bas niveau */
	private FkComLowLevel fkll;

	/**
	 *
	 * @param lowLevel Un objet de type FkComLowLevel pour qui Speaker
	 * 			délèguera les écritures sur la socket.
	 */

	public Speaker(FkComLowLevel lowLevel) {
		this.fkll = lowLevel;
	}


	public void askForService(String rootName, String serviceName) {
		// TODO Auto-generated method stub


	}


	public void askForService(String rootName, String serviceName, String date) {
		// TODO Auto-generated method stub

	}


	public void closeConnection() {
		// TODO Auto-generated method stub

	}


	public void closeSession(String SessionName) {
		// TODO Auto-generated method stub

	}

	/** initie la connexion avec FrameKit
	 * @param login
	 * @param password
	 * @throws IOException
	 */
	public void startCommunication(String login, String password) throws IOException {

		/** fabrique la commande SC */
		byte[] cmdToSend = CamiGenerator.generateCmdSC(login, password);
		/** envoie de la commande SC */
		this.fkll.writeCommand(cmdToSend);

	}

	/**
	 * @param uiName  Nom de l'interface utilisateur (client)
	 * @param uiVersion version du client
	 * @param login login déja passé dans la commande SC de la connexion
	 * 		  gardé pour des raisons de compatibilité ascendante
	 */
	public void openConnection(String uiName, String uiVersion, String login) throws IOException {

		/** fabrique la commande OC */
		byte[] cmdToSend = CamiGenerator.generateCmdOC(uiName,uiVersion, login);
		/** envoie de la commande SC */
		this.fkll.writeCommand(cmdToSend);

	}


	/**
	 *   demande a ISpeaker d'envoyer a FK l'ouverture d'une session.
	 *   @param le nom de la session.
	 *   @param date de dernière modification du modèle.
	 *   @param formalisme de la session
	 *   @param	interlocuteur (outil invoqué)
	 *   @param mode batch ou interactif
	 * @throws IOException
	 *
 	*/
	public void openSession(String sessionName, String date,
			String sessionFormalism, String interlocutor, int mode) throws IOException {

		/* envoi de la commande OS */
		byte[] cmdToSend = CamiGenerator.generateCmdOS(sessionName, date, sessionFormalism);
		this.fkll.writeCommand(cmdToSend);

		/* envoi de la commande DI */
		cmdToSend = CamiGenerator.generateCmdDI();
		this.fkll.writeCommand(cmdToSend);

		/* envoi de la commande CI */
		cmdToSend = CamiGenerator.generateCmdCI(interlocutor, mode);
		this.fkll.writeCommand(cmdToSend);

		/* envoi de la commande FI */
		cmdToSend = CamiGenerator.generateCmdFI();
		this.fkll.writeCommand(cmdToSend);

	}



	public void resumeSession(String SessionName) {
		// TODO Auto-generated method stub

	}


	public void sendDialogResponse(IDialog d) {
		// TODO Auto-generated method stub

	}


	public void sendModel(IModel m) {
		// TODO Auto-generated method stub

	}


	public void suspendSession(String SessionName) {
		// TODO Auto-generated method stub

	}
}