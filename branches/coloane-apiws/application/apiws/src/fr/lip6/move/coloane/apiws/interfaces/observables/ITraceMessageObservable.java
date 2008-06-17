package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.observers.ITraceMessageObserver;

public interface ITraceMessageObservable {
	
	/**
	 * Recupere le message de trace de l'evenement
	 * @return le message de trace de l'evenement
	 */
	public String getTraceMessage();
	
	/**
	 * Ajouter un ITraceMessageObserver a la liste des observateurs de l'evenement ???
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(ITraceMessageObserver o);
	
	/**
	 * Supprimer un ITraceMessageObserver de la liste des observateurs de l'evenement ???
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(ITraceMessageObserver o);
	
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
