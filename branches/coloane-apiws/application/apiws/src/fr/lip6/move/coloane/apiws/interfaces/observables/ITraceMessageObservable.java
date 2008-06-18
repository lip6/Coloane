package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IReceptTraceMessage;
import fr.lip6.move.coloane.apiws.interfaces.observers.ITraceMessageObserver;

public interface ITraceMessageObservable {
	
	/**
	 * Ajouter un observateur a la liste des observateurs de l'evenement : reception d'un message de trace
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(ITraceMessageObserver o);
	
	/**
	 * Supprimer un observateur de la liste des observateurs de l'evenement : reception d'un message de trace
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(ITraceMessageObserver o);
	
	/**
	 * Notifier tous les observateurs de l'evenement : reception d'un message de trace
	 */
	public void notifyObservers(IReceptTraceMessage e);
	
	/**
	 * Definir s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}
