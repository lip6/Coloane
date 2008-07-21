package fr.lip6.move.coloane.api.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.observables.ICloseSessionObservable;
import fr.lip6.move.coloane.api.interfaces.observers.ICloseSessionObserver;


public class CloseSessionObservable implements ICloseSessionObservable{

	/** liste des observateurs */
	private ArrayList<ICloseSessionObserver> list;

	/** créer un thread ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public CloseSessionObservable() {
		list = new ArrayList<ICloseSessionObserver>();
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
	public void addObserver(ICloseSessionObserver o) {
		this.list.add( o);
	}

	/**
	 * Notifier tous les observers
	 *
	 * @param arg
	 *            argument de la notification.
	 */
	public void notifyObservers(String num) {

		if (!this.createThread) { /* Option sans création de thread */
			for (int i = 0; i < this.list.size(); i++)
				this.list.get(i).update(num);
		} else {/* Option avec création de thread */
			ThreadNotifier thread = new ThreadNotifier(list,num);
			new Thread(thread, "threadCloseSession").start();
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
		private ArrayList<ICloseSessionObserver> listObservers;
		private String num;

		public ThreadNotifier(ArrayList<ICloseSessionObserver> list, String num) {
			this.listObservers = list;
			this.num = num;
		
		}

		public void run() {
			for (int i = 0; i < this.listObservers.size(); i++)
				this.listObservers.get(i).update(num);
		}

	}

	

}
