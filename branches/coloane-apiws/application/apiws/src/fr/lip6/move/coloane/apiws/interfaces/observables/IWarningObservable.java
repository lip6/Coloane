package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.observers.IWarningObserver;

public interface IWarningObservable {
	
	/**
	 * Recupere le message d'avertissement de l'evenement
	 * @return le message d'avertissement de l'evenement
	 */
	public String getMessageWarning();
	
	/**
	 * Ajouter un IWarningObserver a la liste des observateurs de l'evenement ???
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(IWarningObserver o);
	
	/**
	 * Supprimer un IWarningObserver de la liste des observateurs de l'evenement ???
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(IWarningObserver o);
	
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
