package fr.lip6.move.coloane.api.interfaces;

import java.io.IOException;


/**
 * cette interface représente notre speaker.
 * @author KAHOO & UU
 *
 */

public interface ISpeaker {

	/**
	 *
	 * @param login
	 * @param password
	 * @throws IOException
	 */

	void startCommunication(String login ,String password) throws IOException;


	/**
	 * demande a ISpeaker d'envoyer a FK l'ouverture de la connection.
	 * @param uiName
	 * @param uiVersion
	 * @param loginName
	 * @throws IOException
	 */
	void openConnection(String uiName ,String uiVersion, String login) throws IOException;

	/**
	 *  demande a ISpeaker d'envoyer a FK la fermeture de la connection.
	 */
	void closeConnection();

	/**
	 *   demande a ISpeaker d'envoyer a FK l'ouverture d'une session.
	 *   @param le nom de la session.
	 *   @param le nom du formalisme.
 	*/
	void openSession(String sessionName, int date, String sessionFormalism);

	/**
	 * demande a ISpeaker d'envoyer a FK la fermeture d'une session.
	 *   @param le nom de la session.
	 */
	void closeSession(String sessionName);

	/**
	 * demande a ISpeaker d'envoyer a FK la suspension d'une session.
	 *   @param le nom de la session.
	 */
	void suspendSession(String sessionName);

	/**
	 * demande a ISpeaker d'envoyer a FK la reprise d'une session.
	 *   @param le nom de la session.
	 */
	void resumeSession(String sessionName);

	/**
	 *  demande a ISpeaker d'envoyer a FK la demande de service.
	 *   @param le nom du chemin.
	 *   @param le nom du service.
	 */
	void askForService(String rootName, String serviceName);

	/**
	 *  demande a ISpeaker d'envoyer a FK la demande de service.
	 *   @param le nom du chemin.
	 *   @param le nom du service.
	 *   @param la date.
	 */
	void askForService(String rootName, String serviceName, String date);

	/**
	 *  demande a ISpeaker d'envoyer a FK un model.
	 *   @param le model.
	 */
	void sendModel(IModel m);

	/**
	 *  demande a ISpeaker d'envoyer a FK une boite de dialogue.
	 *   @param le dialogue.
	 */
	void sendDialogResponse(IDialog d);

}
