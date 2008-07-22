package fr.lip6.move.coloane.api.interfaces.observables;

import fr.lip6.move.coloane.api.interfaces.ISpecialMessage;

import fr.lip6.move.coloane.api.interfaces.observers.ISpecialMessageObserver;

public interface ISpecialMessageObservable {

	/**
	 *
	 * @param createThread notification avec ou sans création de thread
	 */
	public void setCreateThread(boolean createThread);

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public void addObserver(ISpecialMessageObserver o);

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public void notifyObservers(ISpecialMessage arg);
}
