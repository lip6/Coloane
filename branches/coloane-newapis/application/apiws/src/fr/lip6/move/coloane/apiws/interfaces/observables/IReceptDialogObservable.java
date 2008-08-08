package fr.lip6.move.coloane.apiws.interfaces.observables;

import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

/**
 * Cette interface définie l'événement observable: récéption d'une boîte de dialogue.
 *
 * @author Monir CHAOUKI
 */
public interface IReceptDialogObservable {

	/**
	 * Ajoute un observateur a la liste des observateurs de l'evenement : reception d'une boite de dialogue
	 * @param o l'observateur a ajouter
	 */
	void addObserver(IReceptDialogObserver o);

	/**
	 * Supprime un observateur de la liste des observateurs de l'evenement : reception d'une boite de dialogue
	 * @param o l'observateur a supprimer
	 */
	void removeObserver(IReceptDialogObserver o);

	/**
	 * Notifie tous les observateurs de l'evenement : reception d'une boite de dialogue
	 * @param dialog la boîte de dialogue reçu
	 */
	void notifyObservers(IDialog dialog);

	/**
	 * Defini s'il faut creer un thread pour la notification
	 * @param createThread avec ou sans thread
	 */
	void setCreateThread(boolean createThread);
}
