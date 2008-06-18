package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerChangeSession;
import fr.lip6.move.coloane.apiws.interfaces.observers.IChangeSessionObserver;

public interface IChangeSessionObservable {
	
	/**
	 * Ajouter un observateur a la liste des observateurs de l'evenement : changement de session
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(IChangeSessionObserver o);
	
	/**
	 * Supprimer un observateur de la liste des observateurs de l'evenement : changement de session
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(IChangeSessionObserver o);
	
	/**
	 * Notifier tous les observateurs de l'evenement : changement de session
	 */
	public void notifyObservers(IAnswerChangeSession e);
	
	/**
	 * Definir s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);

}
