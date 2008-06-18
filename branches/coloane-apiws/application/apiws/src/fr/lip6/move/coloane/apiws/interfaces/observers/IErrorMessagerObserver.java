package fr.lip6.move.coloane.apiws.interfaces.observers;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IReceptErrorMessage;


public interface IErrorMessagerObserver {
	
	/**
	 * Mettre a jour l'observateur d'evenement : reception d'un message d'erreur de la part du wrapper
	 * @param m le message d'erreur recu a envoyer aux observateurs de l'evenement
	 */
	public void update(IReceptErrorMessage m);
}
