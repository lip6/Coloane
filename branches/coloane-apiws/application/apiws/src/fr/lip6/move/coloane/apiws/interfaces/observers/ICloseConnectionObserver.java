package fr.lip6.move.coloane.apiws.interfaces.observers;

import fr.lip6.move.coloane.apiws.interfaces.objects.IAnswerCloseConnection;

public interface ICloseConnectionObserver {

	/**
	 * Mettre a jour l'observateur d'evenement : fermeture de connexion
	 * @param s la reponse de la fermeture de connexion
	 */
	public void update(IAnswerCloseConnection s);
}
