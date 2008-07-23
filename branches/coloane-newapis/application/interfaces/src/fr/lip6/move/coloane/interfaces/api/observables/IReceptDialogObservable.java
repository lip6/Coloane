package fr.lip6.move.coloane.interfaces.api.observables;

import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

public interface IReceptDialogObservable {
	
	/**
	 * Ajoute un observateur a la liste des observateurs de l'evenement : reception d'une boite de dialogue
	 * @param o l'observateur a ajouter
	 */
	public void addObserver(IReceptDialogObserver o);
	
	/**
	 * Supprime un observateur de la liste des observateurs de l'evenement : reception d'une boite de dialogue
	 * @param o l'observateur a supprimer
	 */
	public void removeObserver(IReceptDialogObserver o);
	
	/**
	 * Notifie tous les observateurs de l'evenement : reception d'une boite de dialogue
	 */
	public void notifyObservers(IDialog dialog);
	
	/**
	 * Defini s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}