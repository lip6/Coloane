package fr.lip6.move.coloane.apiws.interfaces.observers;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAskDialog;


public interface IAskDialogObserver {
	
	/**
	 * Mettre a jour l'observateur d'evenement : reception d'une de boite de dialogue de la part du wrapper
	 * @param d la boite de dialogue recu a envoyer aux observateurs de l'evenement
	 */
	public void update(IAskDialog d);
}
