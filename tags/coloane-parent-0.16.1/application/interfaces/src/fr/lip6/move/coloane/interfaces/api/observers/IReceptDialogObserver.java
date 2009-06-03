package fr.lip6.move.coloane.interfaces.api.observers;

import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

/**
 * Cette interface définie un observeur pour l'événement: récéption d'une boîte de dialogue.
 */
public interface IReceptDialogObserver {

	/**
	 * Met a jour l'observateur d'evenement : reception d'une boite de dialogue
	 * @param dialog la boite de dialogue recu
	 */
	void update(IDialog dialog);

}
