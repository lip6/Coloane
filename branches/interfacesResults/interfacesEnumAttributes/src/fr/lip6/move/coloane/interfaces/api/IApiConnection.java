package fr.lip6.move.coloane.interfaces.api;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.observers.IBrutalInterruptObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IDisconnectObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptServiceStateObserver;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

/**
 * Définition d'une session sur une plate-forme
 */
public interface IApiConnection {

	/**
	 * Positionne un observateur pour l'evenement : reception d'une boite de dialogue
	 * @param o l'observateur qui sera notifier par l'evenement : reception d'une boite de dialogue
	 * @param createThread definie s'il faut creer un thread pour la notification
	 */
	void setReceptDialogObserver(IReceptDialogObserver o, boolean createThread);

	/**
	 * Positionne un observateur pour l'evenement : reception d'un menu
	 * @param o l'observateur qui sera notifier par l'evenement : reception d'un menu
	 * @param createThread definie s'il faut creer un thread pour la notification
	 */
	void setReceptMenuObserver(IReceptMenuObserver o, boolean createThread);

	/**
	 * Positionne un observateur pour l'evenement : reception d'un message
	 * @param o l'observateur qui sera notifier par l'evenement : reception d'un message
	 * @param createThread definie s'il faut creer un thread pour la notification
	 */
	void setReceptMessageObserver(IReceptMessageObserver o, boolean createThread);

	/**
	 * Positionne un observateur pour l'evenement : reception d'un resultat
	 * @param o l'observateur qui sera notifier par l'evenement : reception d'un resultat
	 * @param createThread definie s'il faut creer un thread pour la notification
	 */
	void setReceptResultObserver(IReceptResultObserver o, boolean createThread);

	/**
	 * Positionne un observateur pour l'evenement : deconnexion ordonnee
	 * @param o l'observateur qui sera notifier par l'evenement : deconnexion ordonnee
	 * @param createThread definie s'il faut creer un thread pour la notification
	 */
	void setDisconnectObserver(IDisconnectObserver o, boolean createThread);

	/**
	 * Positionne un observateur pour l'evenement : reception d'une erreur
	 * @param o l'observateur qui sera notifier par l'evenement : reception d'une erreur
	 * @param createThread definie s'il faut creer un thread pour la notification
	 */
	void setBrutalInterruptObserver(IBrutalInterruptObserver o, boolean createThread);

	/**
	 * Positionne un observateur pour l'evenement : récéption d'une information sur un service en cours d'exécution.
	 * @param o l'observateur qui sera notifier par l'evenement : récéption d'une information sur un service en cours d'exécution.
	 * @param createThread definie s'il faut creer un thread pour la notification
	 */
	void setReceptServiceStateObserver(IReceptServiceStateObserver o, boolean createThread);

	/**
	 * Ouvre une connexion
	 * @param login Le login d'authentification
	 * @param pass Le mot de passe associé
	 * @param ip L'adresse IP de la plate-forme
	 * @param port Le port sur lequel la plate-forme est en écoute
	 * @return les informations sur la connection
	 * @throws ApiException si l'ouverture de session échoue
	 */
	IConnectionInfo openConnection(String login, String pass, String ip, int port) throws ApiException;

	/**
	 * Ferme une connexion
	 * @param softMode Si <code>true</code> alors toutes les sessions doivent être proprement déconnectées d'abord.
	 * Si <code>false</code> on se contente de couper les différentes socket.
	 */
	void closeConnection(boolean softMode);

	/**
	 * Creer une session
	 * @return une session
	 * @throws ApiException si la création de la session échoue: l'utilisateur n'est pas connecté.
	 */
	IApiSession createApiSession() throws ApiException;
}
