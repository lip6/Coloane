package fr.lip6.move.coloane.interfaces.api.observers;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptResult;

/**
 * Cette interface définie un observeur pour l'événement: récéption d'un résultat.
 */
public interface IReceptResultObserver {

	/**
	 * Met a jour l'observateur d'evenement : reception de resultats
	 * @param e l'objet qui represent les resultats
	 */
	void update(IReceptResult e);
}
