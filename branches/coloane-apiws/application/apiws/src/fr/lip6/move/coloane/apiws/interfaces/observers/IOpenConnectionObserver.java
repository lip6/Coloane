package fr.lip6.move.coloane.apiws.interfaces.observers;

import fr.lip6.move.coloane.apiws.interfaces.objects.IAnswerOpenConnection;

public interface IOpenConnectionObserver {

	/**
	 * Mettre a jour l'observateur d'evenement : ouverture de connection
	 * @param s la reponse de l'ouverture de connection
	 */
	public void update(IAnswerOpenConnection s);
}
