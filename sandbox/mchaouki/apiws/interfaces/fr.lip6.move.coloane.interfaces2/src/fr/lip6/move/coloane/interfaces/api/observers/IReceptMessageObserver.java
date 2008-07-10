package fr.lip6.move.coloane.interfaces.api.observers;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;

public interface IReceptMessageObserver {
	
	/**
	 * Met a jour l'observateur d'evenement : reception d'un message
	 * @param e le message recu
	 */
	public void update(IReceptMessage e);

}
