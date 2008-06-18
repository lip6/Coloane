package fr.lip6.move.coloane.apiws.interfaces.observers;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IReceptWarningMessage;


public interface IWarningMessageObserver {
	
	/**
	 * Mettre a jour l'observateur d'evenement : reception d'un message d'avertissement de la part du wrapper
	 * @param m le message d'avertissement recu a envoyer aux observateurs de l'evenement
	 */
	public void update(IReceptWarningMessage m);
}
