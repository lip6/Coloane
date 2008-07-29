package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.core.motor.Motor;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptResult;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;

/**
 * Permet d'être notifié de la fin de l'execution d'un service et on récupère le resultat.
 */
public class ReceptResultObserver implements IReceptResultObserver {

	/** {@inheritDoc} */
	public final void update(IReceptResult e) {
		Motor.getInstance().endService();
	}

}
