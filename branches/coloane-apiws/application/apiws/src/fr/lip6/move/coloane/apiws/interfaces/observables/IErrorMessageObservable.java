package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.objects.IReceptErrorMessage;
import fr.lip6.move.coloane.apiws.interfaces.observers.IErrorMessagerObserver;

public interface IErrorMessageObservable {
	
	/**
	 * Ajouter un observateur a la liste des observateurs de l'evenement : reception d'un message d'erreur
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(IErrorMessagerObserver o);
	
	/**
	 * Supprimer un observateur de la liste des observateurs de l'evenement : reception d'un message d'erreur
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(IErrorMessagerObserver o);
	
	/**
	 * Notifier tous les observateurs de l'evenement : reception d'un message d'erreur
	 */
	public void notifyObservers(IReceptErrorMessage e);
	
	/**
	 * Definir s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);

}
