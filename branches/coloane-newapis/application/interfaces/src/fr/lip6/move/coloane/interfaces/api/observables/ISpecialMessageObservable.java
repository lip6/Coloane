package fr.lip6.move.coloane.interfaces.api.observables;

import fr.lip6.move.coloane.interfaces.api.objects.ISpecialMessage;
import fr.lip6.move.coloane.interfaces.api.observers.ISpecialMessageObserver;

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
