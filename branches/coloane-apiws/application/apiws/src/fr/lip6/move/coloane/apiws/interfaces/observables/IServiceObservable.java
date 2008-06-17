package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.observers.IServiceObserver;

public interface IServiceObservable {
	
	/**
	 * Ajouter un IServiceObserver a la liste des observateurs de l'evenement ???
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(IServiceObserver o);
	
	/**
	 * Supprimer un IServiceObserver de la liste des observateurs de l'evenement ???
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(IServiceObserver o);
	
	/**
	 * Notifier tous les observateurs de l'evenement ???
	 */
	public void notifyObservers();
	
	/**
	 * Definir s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}
