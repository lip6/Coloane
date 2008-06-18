package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.objects.IAnswerCloseConnection;
import fr.lip6.move.coloane.apiws.interfaces.observers.ICloseConnectionObserver;

public interface ICloseConnectionObservable {
	
	/**
	 * Ajouter un observateur a la liste des observateurs de l'evenement : fermeture de connexion
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(ICloseConnectionObserver o);
	
	/**
	 * Supprimer un observateur de la liste des observateurs de l'evenement : fermeture de connexion
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(ICloseConnectionObserver o);
	
	/**
	 * Notifier tous les observateurs de l'evenement : fermeture de connexion
	 */
	public void notifyObservers(IAnswerCloseConnection e);
	
	/**
	 * Definir s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);

}
