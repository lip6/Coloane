package fr.lip6.move.coloane.apiws.interfaces.observers;

import fr.lip6.move.coloane.apiws.interfaces.objects.IAnswerCloseSession;

public interface ICloseSessionObserver {

	/**
	 * Mettre a jour l'observateur d'evenement : fermeture de seccion
	 * @param s la reponse de la fermeture de session
	 */
	public void update(IAnswerCloseSession s);
}
