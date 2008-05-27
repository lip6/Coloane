package fr.lip6.move.coloane.api.interfaces.observables;

import java.io.IOException;

import fr.lip6.move.coloane.api.interfaces.observers.IAskForModelObserver;

public interface IAskForModelObservable {


	/**
	 *
	 * @param createThread notification avec ou sans création de thread
	 */
	public void setCreateThread(boolean createThread);

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public void addObserver(IAskForModelObserver o);

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 * @throws IOException
	 */
	public void notifyObservers() throws IOException;





}
