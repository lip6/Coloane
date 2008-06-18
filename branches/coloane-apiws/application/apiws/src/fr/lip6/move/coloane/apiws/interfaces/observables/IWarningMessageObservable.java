package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.objects.IReceptWarningMessage;
import fr.lip6.move.coloane.apiws.interfaces.observers.IWarningMessageObserver;

public interface IWarningMessageObservable {
	
	/**
	 * Ajouter un observateur a la liste des observateurs de l'evenement : reception d'un message d'avertissement
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(IWarningMessageObserver o);
	
	/**
	 * Supprimer un observateur de la liste des observateurs de l'evenement : reception d'un message d'avertisement
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(IWarningMessageObserver o);
	
	/**
	 * Notifier tous les observateurs de l'evenement : reception d'un message d'avertissement
	 */
	public void notifyObservers(IReceptWarningMessage e);
	
	/**
	 * Definir s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);

}
