package fr.lip6.move.coloane.interfaces.api.connection;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.observers.IBrutalInterruptObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IDisconnectObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

/**
 * Définition d'une session sur une plate-forme
 */
public interface IApiConnection {

	/**
	 * Initialiser l'adresse IP du serveur
	 * @param ipServer L'IP de la plate-forme sur laquelle on souhaite se connecter
	 */
	void setIpServer(String ipServer);

	/**
	 * Initialiser le port du serveur
	 * @param portServer Le port de la plate-forme sur laquelle on souhaite se connecter
	 */
	void setPortServer(int portServer);

	/**
	 * Initialiser le login
	 * @param login Le login à utiiser pour la connexion
	 */
	void setLogin(String login);

	/**
	 * Initialiser le password
	 * @param password Le password à utiliser pour la connexion
	 */
	void setPassword(String password);

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
	 * Ouvre une connexion
	 * @return les informations sur la connection
	 * @throws ApiException si l'ouverture de session échoue
	 */
	IConnectionInfo openConnection() throws ApiException;

	/**
	 * Ferme une connexion
	 * @return true, si la fermeture a reussie, false sinon
	 * @throws ApiException si la fermeture de session échoue
	 */
	boolean closeConnection() throws ApiException;

	/**
	 * Creer une session
	 * @return une session
	 * @throws ApiException si la création de la session échoue: l'utilisateur n'est pas connecté.
	 */
	IApiSession createApiSession() throws ApiException;
}
