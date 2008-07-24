package fr.lip6.move.coloane.api.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.api.observables.IBrutalInterruptObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IBrutalInterruptObserver;

public class BrutalInterruptObservable implements IBrutalInterruptObservable{

	/** liste des observeurs */
	private ArrayList<IBrutalInterruptObserver> list;

	/** créer un thread ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public BrutalInterruptObservable() {
		list = new ArrayList<IBrutalInterruptObserver>();
	}

	public void addObserver (IBrutalInterruptObserver o) {
		this.list.add(o);

	}


	public void notifyObservers(String message) {
		if (!this.createThread) { /* Option sans création de thread */
			for (int i = 0; i < this.list.size(); i++)
				this.list.get(i).update(message);
		} else {/* Option avec création de thread */
			ThreadNotifier thread = new ThreadNotifier(message);
			new Thread(thread, "threadAskForModel").start();
		}

	}


	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;

	}




	/**
	 * Cette classe est utilisée pour créer un thread lors de la notification,
	 * si cette option est active. cette classe est interne.
	 *
	 * @author kahoo & uu
	 *
	 */
	private class ThreadNotifier implements Runnable {
		private ArrayList<IBrutalInterruptObserver> listObservers;
		private String message;

		public ThreadNotifier(String message) {
			this.listObservers = new ArrayList<IBrutalInterruptObserver> ();
			this.message = message;
		}

		public void run()  {
			for (int i = 0; i < this.listObservers.size(); i++)
				this.listObservers.get(i).update(message);
		}

	}




	public void removeObserver(IBrutalInterruptObserver o) {
		// TODO Auto-generated method stub

	}

}


