package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerOpenConnection;
import fr.lip6.move.coloane.apiws.interfaces.observers.IOpenConnectionObserver;

public interface IOpenConnectionObservable {
	
	/**
	 * Ajouter un observateur a la liste des observateurs de l'evenement : ouveture de connexion
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(IOpenConnectionObserver o);
	
	/**
	 * Supprimer un observateur de la liste des observateurs de l'evenement : ouveture de connexion
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(IOpenConnectionObserver o);
	
	/**
	 * Notifier tous les observateurs de l'evenement : ouveture de connexion
	 */
	public void notifyObservers(IAnswerOpenConnection e);
	
	/**
	 * Definir s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);

}
