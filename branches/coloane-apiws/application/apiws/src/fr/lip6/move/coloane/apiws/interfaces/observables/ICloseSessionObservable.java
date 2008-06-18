package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerCloseSession;
import fr.lip6.move.coloane.apiws.interfaces.observers.ICloseSessionObserver;

public interface ICloseSessionObservable {
	
	/**
	 * Ajouter un observateur a la liste des observateurs de l'evenement : fermeture de session
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(ICloseSessionObserver o);
	
	/**
	 * Supprimer un observateur de la liste des observateurs de l'evenement : fermeture de session
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(ICloseSessionObserver o);
	
	/**
	 * Notifier tous les observateurs de l'evenement : fermeture de session
	 */
	public void notifyObservers(IAnswerCloseSession e);
	
	/**
	 * Definir s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);

}
