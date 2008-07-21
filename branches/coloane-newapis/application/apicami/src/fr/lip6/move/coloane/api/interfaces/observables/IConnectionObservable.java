package fr.lip6.move.coloane.api.interfaces.observables;

import fr.lip6.move.coloane.api.interfaces.IConnectionVersion;
import fr.lip6.move.coloane.api.interfaces.observers.IConnectionObserver;

public interface IConnectionObservable {

	/**
	 *
	 * @param createThread notification avec ou sans création de thread
	 */
	public void setCreateThread(boolean createThread);

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public void addObserver(IConnectionObserver o);

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public void notifyObservers(IConnectionVersion arg);


}
