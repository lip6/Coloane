package fr.lip6.move.coloane.api.observables;

import java.util.ArrayList;


import fr.lip6.move.coloane.api.interfaces.observables.ICloseConnectionObservable;
import fr.lip6.move.coloane.api.interfaces.observers.ICloseConnectionObserver;



public class CloseConnectionObservable implements ICloseConnectionObservable{

	/** liste des observateurs */
	private ArrayList<ICloseConnectionObserver> list;

	/** créer un thread ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public CloseConnectionObservable() {
		list = new ArrayList<ICloseConnectionObserver>();
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
	public void addObserver(ICloseConnectionObserver o) {
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
		private ArrayList<ICloseConnectionObserver> listObservers;
		

		public ThreadNotifier(ArrayList<ICloseConnectionObserver> list) {
			this.listObservers = list;
		
		}

		public void run() {
			for (int i = 0; i < this.listObservers.size(); i++)
				this.listObservers.get(i).update();
		}

	}

	

}
