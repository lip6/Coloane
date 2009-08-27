package fr.lip6.move.coloane.interfaces.api.observers;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptServiceState;

/**
 * Cette interface définie un observeur pour l'événement: récéption d'une information sur un service en cours d'exécution.
 */
public interface IReceptServiceStateObserver {

	/**
	 * Met a jour l'observateur d'evenement : récéption d'une information sur un service en cours d'exécution.
	 * @param e l'objet qui represent l'information sur le service en cours d'exécution.
	 */
	void update(IReceptServiceState e);
}
