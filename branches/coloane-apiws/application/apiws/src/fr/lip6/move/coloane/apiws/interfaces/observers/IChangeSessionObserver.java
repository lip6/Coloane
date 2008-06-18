package fr.lip6.move.coloane.apiws.interfaces.observers;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerChangeSession;


public interface IChangeSessionObserver {

	/**
	 * Mettre a jour l'observateur d'evenement : changement de session
	 * @param s la reponse du changement de session
	 */
	public void update(IAnswerChangeSession s);
}
