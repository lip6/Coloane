package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAskDialog;
import fr.lip6.move.coloane.apiws.interfaces.observers.IAskDialogObserver;

public interface IAskDialogObservable {
	
	/**
	 * Ajouter un observateur a la liste des observateurs de l'evenement : reception d'une boite de dialogue
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(IAskDialogObserver o);
	
	/**
	 * Supprimer un observateur de la liste des observateurs de l'evenement : reception d'une boite de dialogue
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(IAskDialogObserver o);
	
	/**
	 * Notifier tous les observateurs de l'evenement : reception d'une boite de dialogue
	 */
	public void notifyObservers(IAskDialog e);
	
	/**
	 * Definir s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);

}
