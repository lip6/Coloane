package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerExecutService;
import fr.lip6.move.coloane.apiws.interfaces.observers.IExecutServiceObserver;

public interface IExecutServiceObservable {
	
	/**
	 * Ajouter un observateur a la liste des observateurs de l'evenement : execution d'un service
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(IExecutServiceObserver o);
	
	/**
	 * Supprimer un observateur de la liste des observateurs de l'evenement : execution d'un service
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(IExecutServiceObserver o);
	
	/**
	 * Notifier tous les observateurs de l'evenement : execution d'un service
	 */
	public void notifyObservers(IAnswerExecutService e);
	
	/**
	 * Definir s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);

}
