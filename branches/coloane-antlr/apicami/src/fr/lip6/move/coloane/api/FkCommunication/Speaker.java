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
	 * @param uiName
	 * @param uiVersion
	 * @param login
	 */
	public void openConnection(String uiName, String uiVersion, String login) throws IOException {

		/** fabrique la commande OC */
		byte[] cmdToSend = CamiGenerator.generateCmdOC(uiName,uiVersion, login);
		/** envoie de la commande SC */
		this.fkll.writeCommand(cmdToSend);

	}


	public void openSession(String sessionName, int date, String SessionFormalism) {
		// TODO Auto-generated method stub

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