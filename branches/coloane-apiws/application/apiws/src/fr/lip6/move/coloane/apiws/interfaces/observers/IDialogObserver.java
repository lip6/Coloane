package fr.lip6.move.coloane.apiws.interfaces.observers;

import fr.lip6.move.coloane.apiws.interfaces.observables.IDialogObservable;

public interface IDialogObserver {
	
	/**
	 * Mettre a jour l'obsevateur
	 * @param o l'objet qui a provoquer la mise a jour
	 */
	public void update(IDialogObservable o);
}
