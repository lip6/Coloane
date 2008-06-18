package fr.lip6.move.coloane.apiws.interfaces.observers;

import fr.lip6.move.coloane.apiws.interfaces.objects.IAnswerExecutService;

public interface IExecutServiceObserver {

	/**
	 * Mettre a jour l'observateur d'evenement : execution d'un service
	 * @param s la reponse de l'execution d'un service
	 */
	public void update(IAnswerExecutService s);

}
