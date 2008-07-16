package fr.lip6.move.coloane.interfaces.api.observers;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptError;

public interface IReceptErrorObserver {
	/**
	 * Met a jour l'observateur d'evenement :  reception d'une erreur
	 * @param e l'objet qui represent l'erreur
	 */
	public void update(IReceptError e);

}