package fr.lip6.move.coloane.apiws.interfaces.observers;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IReceptTraceMessage;


public interface ITraceMessageObserver {
	
	/**
	 * Mettre a jour l'observateur d'evenement : reception d'un message de trace de la part du wrapper
	 * @param m le message de trace recu a envoyer aux observateurs de l'evenement
	 */
	public void update(IReceptTraceMessage m);
}
