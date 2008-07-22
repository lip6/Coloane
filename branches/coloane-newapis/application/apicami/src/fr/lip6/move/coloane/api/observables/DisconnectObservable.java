package fr.lip6.move.coloane.api.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.api.observables.IDisconnectObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IDisconnectObserver;







public class DisconnectObservable implements IDisconnectObservable{

	/** liste des observateurs */
	private ArrayList<IDisconnectObserver> list;

	/** créer un thread ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public DisconnectObservable() {
		list = new ArrayList<IDisconnectObserver>();
	}

	/**
	 * set de la variable createThread
	 *
	 * @param createThread
	 *            notification avec ou sans création de thread
	 */
	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

	/**
	 * Ajoute un observer
	 *
	 * @param o
	 *            L'observer à ajouter
	 */
	public void addObserver(IDisconnectObserver o) {
		this.list.add( o);
	}

	/**
	 * Notifier tous les observers
	 *
	 * @param arg
	 *            argument de la notification.
	 */
	public void notifyObservers() {

		if (!this.createThread) { /* Option sans création de thread */
			for (int i = 0; i < this.list.size(); i++)
				this.list.get(i).update();
		} else {/* Option avec création de thread */
			ThreadNotifier thread = new ThreadNotifier(list);
			new Thread(thread, "threadCloseConnection").start();
		}

	}

	/**
	 * Cette classe est utilisée pour créer un thread lors de la notification,
	 * si cette option est active.
	 *
	 * @author kahoo & uu
	 *
	 */
	private class ThreadNotifier implements Runnable {
		private ArrayList<IDisconnectObserver> listObservers;
		

		public ThreadNotifier(ArrayList<IDisconnectObserver> list) {
			this.listObservers = list;
		
		}

		public void run() {
			for (int i = 0; i < this.listObservers.size(); i++)
				this.listObservers.get(i).update();
		}

	}

	public void removeObserver(IDisconnectObserver o) {
	
		
	}

	

}
