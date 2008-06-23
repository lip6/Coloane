package fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication;

import fr.lip6.move.wrapper.ws.CException;

public interface IListener {

	/**
	 * Fonction de polling.
	 * Permet de demander periodiquement au wrapper s'il y a des messages ou boites de dialog a traiter
	 */
	public void ping() throws CException;
	
}
