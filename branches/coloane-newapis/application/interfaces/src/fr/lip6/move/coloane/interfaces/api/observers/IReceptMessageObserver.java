package fr.lip6.move.coloane.interfaces.api.observers;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;

public interface IReceptMessageObserver {
	
	/**
	 * Met a jour l'observateur d'evenement : reception d'un message
	 * @param e l'objet qui represent un message
	 */
	public void update(IReceptMessage e);

}
