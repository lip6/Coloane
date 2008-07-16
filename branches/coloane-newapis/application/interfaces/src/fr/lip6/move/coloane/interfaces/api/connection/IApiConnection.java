package fr.lip6.move.coloane.interfaces.api.connection;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.observers.IDisconnectObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptErrorObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

public interface IApiConnection {
	
	/**
	 * Initialiser l'adresse IP du serveur
	 * @return true, si l'initialisation a reussie, false sinon
	 */
	public boolean setIpServer(String ipServer);

	/**
	 * Initialiser le port du serveur
	 * @return true, si l'initialisation a reussie, false sinon
	 */
	public boolean setPortServer(int portServer);

	/**
	 * Initialiser le login
	 * @return true, si l'initialisation a reussie, false sinon
	 */
	public boolean setLogin(String login);

	/**
	 * Initialiser le password
	 * @return true, si l'initialisation a reussie, false sinon
	 */
	public boolean setPassword(String password);
	
	/**
	 * Positionne un observateur pour l'evenement : reception d'une boite de dialogue
	 * @param o l'observateur qui sera notifier par l'evenement : reception d'une boite de dialogue
	 * @param createThread definie s'il faut creer un thread pour la notification
	 * @return true, si le l'observateur a bien etait ajouter, false sinon
	 */
	public boolean setReceptDialogObserver(IReceptDialogObserver o,boolean createThread);
	
	/**
	 * Positionne un observateur pour l'evenement : reception d'un menu
	 * @param o l'observateur qui sera notifier par l'evenement : reception d'un menu
	 * @param createThread definie s'il faut creer un thread pour la notification
	 * @return true, si le l'observateur a bien etait ajouter, false sinon
	 */
	public boolean setReceptMenuObserver(IReceptMenuObserver o,boolean createThread);
	
	/**
	 * Positionne un observateur pour l'evenement : reception d'un message
	 * @param o l'observateur qui sera notifier par l'evenement : reception d'un message
	 * @param createThread definie s'il faut creer un thread pour la notification
	 * @return true, si le l'observateur a bien etait ajouter, false sinon
	 */
	public boolean setReceptMessageObserver(IReceptMessageObserver o,boolean createThread);
	
	/**
	 * Positionne un observateur pour l'evenement : reception d'un resultat
	 * @param o l'observateur qui sera notifier par l'evenement : reception d'un resultat
	 * @param createThread definie s'il faut creer un thread pour la notification
	 * @return true, si le l'observateur a bien etait ajouter, false sinon
	 */
	public boolean setReceptResultObserver(IReceptResultObserver o,boolean createThread);
	
	/**
	 * Positionne un observateur pour l'evenement : reception d'une erreur
	 * @param o l'observateur qui sera notifier par l'evenement : reception d'une erreur
	 * @param createThread definie s'il faut creer un thread pour la notification
	 * @return true, si le l'observateur a bien etait ajouter, false sinon
	 */
	public boolean setReceptErrorObserver(IReceptErrorObserver o,boolean createThread);
	
	/**
	 * Positionne un observateur pour l'evenement : deconnexion ordonnee
	 * @param o l'observateur qui sera notifier par l'evenement : deconnexion ordonnee
	 * @param createThread definie s'il faut creer un thread pour la notification
	 * @return true, si le l'observateur a bien etait ajouter, false sinon
	 */
	public boolean setDisconnectObserver(IDisconnectObserver o,boolean createThread);
	
	/**
	 * Ouvre une connexion
	 * @return les informations sur la connection
	 */
	public IConnectionInfo openConnection() throws ApiException;
	
	/**
	 * Ferme une connexion
	 * @return true, si la fermeture a reussie, false sinon
	 */
	public boolean closeConnection() throws ApiException;
	
	/**
	 * Creer une session
	 * @return une session
	 */
	public IApiSession getApiSession() throws ApiException;
	
}