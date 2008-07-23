package fr.lip6.move.coloane.interfaces.api.observables;


import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.observers.IConnectionObserver;

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
	public void notifyObservers(IConnectionInfo arg);


}
