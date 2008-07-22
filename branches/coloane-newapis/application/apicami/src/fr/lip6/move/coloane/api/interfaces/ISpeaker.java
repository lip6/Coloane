package fr.lip6.move.coloane.api.interfaces;

import java.io.IOException;

import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;




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
	public void startCommunication(String login, String password)throws IOException;

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
	 * @throws IOException
	 */
	void closeConnection() throws IOException;

	/**
	 *   demande a ISpeaker d'envoyer a FK l'ouverture d'une session.
	 *   @param le nom de la session.
	 *   @param date de dernière modification du modèle.
	 *   @param formalisme de la session
	 *   @param	interlocuteur (outil invoqué)
	 *   @param mode batch ou interactif
	 * 	 @throws IOException
	 *
 	*/
	void openSession(String sessionName, String date, String sessionFormalism, String interlocutor, int mode) throws IOException;

	/**
	 * demande a ISpeaker d'envoyer a FK la fermeture d'une session.
	 *   @param le nom de la session.
	 * @throws IOException
	 */
	void closeSession(boolean continueProcessing) throws IOException;

	/**
	 * demande a ISpeaker d'envoyer a FK la suspension d'une session.
	 * @param le nom de la session.
	 * @throws IOException
	 */
	void suspendSession() throws IOException;

	/**
	 * demande a ISpeaker d'envoyer a FK la reprise d'une session.
	 *   @param le nom de la session.
	 * @throws IOException
	 */
	void resumeSession(String sessionName) throws IOException;

	/**
	 *  demande a ISpeaker d'envoyer a FK la demande de service.
	 *   @param le nom du chemin.
	 *   @param le nom du service.
	 * @throws IOException
	 */
	void askForService(String rootName, String menuName, String serviceName) throws IOException;

	/**
	 *  demande a ISpeaker d'envoyer a FK la demande de service.
	 *   @param le nom du chemin.
	 *   @param le nom du service.
	 *   @param la date.
	 * @throws IOException
	 */
	void askForService(String rootName, String menuName, String serviceName, String date) throws IOException;

	/**
	 *  demande a ISpeaker d'envoyer a FK un model.
	 *   @param le model.
	 * @throws IOException
	 */
	void sendModel(IModel m) throws IOException;

	/**
	 *  demande a ISpeaker d'envoyer a FK une boite de dialogue.
	 *   @param le dialogue.
	 * @throws IOException 
	 */
	void sendDialogResponse(IDialogAnswer dialogAnswer) throws IOException;

	public void invalidModel() throws IOException;

	

}
