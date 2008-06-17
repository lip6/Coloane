package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.objects.IAnswerOpenConnection;
import fr.lip6.move.coloane.apiws.interfaces.observers.IConnectionObserver;

public interface IConnectionObservable {
	
	/**
	 * Recupere le resultat de la connection de l'evenement
	 * @return le resultat de la connection de l'evenement
	 */
	public IAnswerOpenConnection getAnswerOpenConnection();
	
	/**
	 * Ajouter un IConnectionObserver a la liste des observateurs de l'evenement ???
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(IConnectionObserver o);
	
	/**
	 * Supprimer un IConnectionObserver de la liste des observateurs de l'evenement ???
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(IConnectionObserver o);
	
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
