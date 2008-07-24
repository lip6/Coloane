package fr.lip6.move.coloane.api.interfaces;

import java.io.IOException;

import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;

/**
 * cette interface représente notre speaker.
 * @author KAHOO & UU
 *
 */

public interface ISpeaker {

	/** 
	 * Initie la connexion avec FrameKit
	 * @param login Le login pour la connexion
	 * @param password Le mot de passe associé
	 * @throws IOException
	 */
	public void startCommunication(String login, String password)throws IOException;

	/**
	 * Ouverture de connexion
	 * @param uiName Nom de l'interface utilisateur (client)
	 * @param uiVersion Version du client
	 * @param Login login déja passé dans la commande SC de la connexion gardé pour des raisons de compatibilité ascendante
	 */
	void openConnection(String uiName ,String uiVersion, String login) throws IOException;

	/**
	 * Demande FK la fermeture de la connection.
	 * @throws IOException
	 */
	void closeConnection() throws IOException;

	/**
	 *   Demande l'ouverture d'une session.
	 *   @param sessionName Le nom de la session.
	 *   @param date Date de dernière modification du modèle.
	 *   @param sessionFormalism Formalisme de la session
	 *   @param	interlocutor Interlocuteur (outil invoqué)
	 *   @param mode Mode batch ou interactif
	 * 	 @throws IOException
	 */
	void openSession(String sessionName, int date, String sessionFormalism, String interlocutor, int mode) throws IOException;

	/**
	 * Demande la fermeture de la session active (du coté FK)
	 * @param continueProcessing TODO : ???
	 * @throws IOException
	 */
	void closeSession(boolean continueProcessing) throws IOException;

	/**
	 * Demande la suspension de la session active (du coté FK)
	 * @throws IOException
	 */
	void suspendSession() throws IOException;

	/**
	 * Demande la reprise d'une session.
	 * @param sessionName Le nom de la session à reprendre
	 * @throws IOException
	 */
	void resumeSession(String sessionName) throws IOException;

	/**
	 * Demande l'exécution d'un service
	 * @param rootName Le nom du menu principal 
	 * @param menuName Le nom du menu parent responsable
	 * @param serviceName Le nom du service à exécuter
	 * @throws IOException
	 */
	void askForService(String rootName, String menuName, String serviceName) throws IOException;

	/**
	 * Demande l'exécution d'un service en précisant la mise à jour du modèle
	 * @param rootName Le nom du menu principal 
	 * @param menuName Le nom du menu parent responsable
	 * @param serviceName Le nom du service à exécuter
	 * @param date La nouvelle date de mise à jour du modèle
	 * @throws IOException
	 */
	void askForService(String rootName, String menuName, String serviceName, String date) throws IOException;

	/**
	 * Demande la transmission du modèle à FrameKit
	 * @param m Le modèle à transmettre
	 * @throws IOException
	 */
	void sendModel(IGraph m) throws IOException;

	/**
	 * Envoie une réponse à une boite de dialogue à FK
	 * @param dialogAnswer L'objet contenant toutes les informations de réponse
	 * @throws IOException
	 */
	void sendDialogResponse(IDialogAnswer dialogAnswer) throws IOException;

	/**
	 * Notifie FrameKit de la mise à jour du modèle coté client
	 * @throws IOException
	 */
	void invalidModel() throws IOException;
}
