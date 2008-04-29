package fr.lip6.move.coloane.api.interfaces.observables;

import fr.lip6.move.coloane.api.interfaces.IFkInfo;
import fr.lip6.move.coloane.api.interfaces.IFkVersion;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;
import fr.lip6.move.coloane.api.interfaces.observers.IConnectionObserver;
import fr.lip6.move.coloane.api.interfaces.observers.ISessionObserver;

/**
 *
 * @author kahoo && uu
 *
 */
public interface ISessionObservable {


	/**
	 *
	 * @param createThread notification avec ou sans création de thread
	 */
	public void setCreateThread(boolean createThread);

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public void addObserver(ISessionObserver o);

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public void notifyObservers(IFkInfo fkInfo, IMenu menu,IUpdateItem update);

}
