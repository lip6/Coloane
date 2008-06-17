package fr.lip6.move.coloane.apiws.interfaces.observers;

import fr.lip6.move.coloane.apiws.interfaces.observables.ISessionObservable;

public interface IServiceObserver {
	
	/**
	 * Mettre a jour l'obsevateur
	 * @param o l'objet qui a provoquer la mise a jour
	 */
	public void update(ISessionObservable o);
}
