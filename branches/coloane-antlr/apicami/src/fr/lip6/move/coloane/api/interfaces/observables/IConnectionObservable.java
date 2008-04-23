package fr.lip6.move.coloane.api.interfaces.observables;

import fr.lip6.move.coloane.api.interfaces.IFkVersion;
import fr.lip6.move.coloane.api.interfaces.observers.IConnectionObserver;

public interface IConnectionObservable {
	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public void addObserver(IConnectionObserver o);

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public void notifyObservers(IFkVersion arg);


}
