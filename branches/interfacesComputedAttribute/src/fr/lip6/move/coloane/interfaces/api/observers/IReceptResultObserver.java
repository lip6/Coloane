package fr.lip6.move.coloane.interfaces.api.observers;

import fr.lip6.move.coloane.interfaces.objects.result.IResult;

/**
 * Cette interface définie un observeur pour l'événement: récéption d'un résultat.
 */
public interface IReceptResultObserver {

	/**
	 * Met a jour l'observateur d'evenement : reception de resultats
	 * @param result Les objets qui representent les resultats
	 */
	void update(IResult result);
}
