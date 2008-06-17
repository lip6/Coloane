package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.observers.IBrutalInterruptObserver;

public interface IBrutalInterruptObservable {
	
	/**
	 * Recupere le message d'erreur de l'evenement
	 * @return le message d'erreur de l'evenement
	 */
	public String getMessageBrutalInterrup();
	
	/**
	 * Ajouter un IAskForModelObserver a la liste des observateurs de l'evenement ???
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(IBrutalInterruptObserver o);
	
	/**
	 * Supprimer un IAskForModelObserver de la liste des observateurs de l'evenement ???
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(IBrutalInterruptObserver o);
	
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
