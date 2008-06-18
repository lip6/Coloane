package fr.lip6.move.coloane.apiws.interfaces.observers;

import fr.lip6.move.coloane.apiws.interfaces.objects.IAnswerOpenSession;

public interface IOpenSessionObserver {

	/**
	 * Mettre a jour l'observateur d'evenement : ouverture de session
	 * @param s la reponse de l'ouverture de session
	 */
	public void update(IAnswerOpenSession s);
}
