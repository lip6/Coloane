package fr.lip6.move.coloane.api.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IConnectionVersion;
import fr.lip6.move.coloane.api.interfaces.ISpecialMessage;
import fr.lip6.move.coloane.api.interfaces.observables.ISpecialMessageObservable;
import fr.lip6.move.coloane.api.interfaces.observers.IConnectionObserver;
import fr.lip6.move.coloane.api.interfaces.observers.ISpecialMessageObserver;


public class SpecialMessageObservable implements ISpecialMessageObservable {

	/** liste des observateurs */
	private ArrayList<ISpecialMessageObserver> list;

	/** créer un thread ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public SpecialMessageObservable() {
		list = new ArrayList<ISpecialMessageObserver>();
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
	public void addObserver(ISpecialMessageObserver o) {
		this.list.add(o);
	}

	/**
	 * Notifier tous les observers
	 *
	 * @param arg
	 *            argument de la notification.
	 */
	public void notifyObservers(ISpecialMessage arg) {

		if (!this.createThread) { /* Option sans création de thread */
			for (int i = 0; i < this.list.size(); i++)
				this.list.get(i).update(arg);
		} else {/* Option avec création de thread */
			ThreadNotifier thread = new ThreadNotifier(this.list, arg);
			new Thread(thread, "threadConnectionSpecialMessage").start();
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
		private ArrayList<ISpecialMessageObserver> listObservers;
		private ISpecialMessage version;

		public ThreadNotifier(ArrayList<ISpecialMessageObserver> list,
				ISpecialMessage arg) {
			this.listObservers = list;
			this.version = arg;
		}

		public void run() {
			for (int i = 0; i < this.listObservers.size(); i++)
				this.listObservers.get(i).update(version);
		}

	}

	
	

}
