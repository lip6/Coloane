package fr.lip6.move.coloane.apicami.interfaces;

import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;
import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;

import java.io.IOException;
import java.util.List;


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
	 * @throws IOException En cas de problème
	 */
	void startCommunication(String login, String password) throws IOException;

	/**
	 * Ouverture de connexion
	 * @param uiName Nom de l'interface utilisateur (client)
	 * @param uiVersion Version du client
	 * @param login Login déja passé dans la commande SC de la connexion gardé pour des raisons de compatibilité ascendante
	 * @throws IOException En cas de problème
	 */
	void openConnection(String uiName, String uiVersion, String login) throws IOException;

	/**
	 * Demande FK la fermeture de la connection.
	 * @throws IOException En cas de problème
	 */
	void closeConnection() throws IOException;

	/**
	 *   Demande l'ouverture d'une session.
	 *   @param sessionName Le nom de la session.
	 *   @param date Date de dernière modification du modèle.
	 *   @param sessionFormalism Formalisme de la session
	 *   @param	interlocutor Interlocuteur (outil invoqué)
	 *   @param mode Mode batch ou interactif
	 * 	 @throws IOException En cas de problème
	 */
	void openSession(String sessionName, int date, String sessionFormalism, String interlocutor, int mode) throws IOException;

	/**
	 * Demande la fermeture de la session active (du coté FK)
	 * @param continueProcessing TODO : ???
	 * @throws IOException En cas de problème
	 */
	void closeSession(boolean continueProcessing) throws IOException;

	/**
	 * Demande la suspension de la session active (du coté FK)
	 * @throws IOException En cas de problème
	 */
	void suspendSession() throws IOException;

	/**
	 * Demande la reprise d'une session.
	 * @param sessionName Le nom de la session à reprendre
	 * @throws IOException En cas de problème
	 */
	void resumeSession(String sessionName) throws IOException;

	/**
	 * Demande l'exécution d'un service
	 * @param rootName Le nom du menu principal
	 * @param menuName Le nom du menu parent responsable
	 * @param serviceName Le nom du service à exécuter
	 * @param options Liste des options actives
	 * @throws IOException En cas de problème
	 */
	void askForService(String rootName, String menuName, String serviceName, List<IOptionMenu> options) throws IOException;

	/**
	 * Demande lenvoi de la nouvelle date
	 * @param date La nouvelle date de mise à jour du modèle
	 * @throws IOException En cas de problème
	 */
	void sendDate(int date) throws IOException;

	/**
	 * Demande la transmission du modèle à FrameKit
	 * @param m Le modèle à transmettre
	 * @throws IOException En cas de problème
	 */
	void sendModel(IGraph m) throws IOException;

	/**
	 * Envoie une réponse à une boite de dialogue à FK
	 * @param dialogAnswer L'objet contenant toutes les informations de réponse
	 * @throws IOException En cas de problème
	 */
	void sendDialogResponse(IDialogAnswer dialogAnswer) throws IOException;

	/**
	 * Notifie FrameKit de la mise à jour du modèle coté client
	 * @throws IOException En cas de problème
	 */
	void invalidModel() throws IOException;
}
