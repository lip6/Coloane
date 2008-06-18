package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerOpenSession;
import fr.lip6.move.coloane.apiws.interfaces.observers.IOpenSessionObserver;

public interface IOpenSessionObservable {
	
	/**
	 * Ajouter un observateur a la liste des observateurs de l'evenement : ouverture de session
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(IOpenSessionObserver o);
	
	/**
	 * Supprimer un observateur de la liste des observateurs de l'evenement : ouverture de session
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(IOpenSessionObserver o);
	
	/**
	 * Notifier tous les observateurs de l'evenement : ouverture de session
	 */
	public void notifyObservers(IAnswerOpenSession e);
	
	/**
	 * Definir s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);

}
