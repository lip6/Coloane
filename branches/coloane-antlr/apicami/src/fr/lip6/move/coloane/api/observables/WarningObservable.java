package fr.lip6.move.coloane.api.observables;

import java.io.IOException;
import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.observables.IWarningObservable;
import fr.lip6.move.coloane.api.interfaces.observers.ITraceMessageObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IWarningObserver;


public class WarningObservable implements IWarningObservable{

	/** liste des observeurs */
	private ArrayList<IWarningObserver> list;

	/** créer un thread ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public WarningObservable() {
		list = new ArrayList<IWarningObserver>();
	}

	public void addObserver (IWarningObserver o) {
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
	private ArrayList<IWarningObserver> listObservers;
    private String message;

	public ThreadNotifier(String message) {
		this.listObservers = new ArrayList<IWarningObserver> ();
		this.message = message;
	}

	public void run()  {
		for (int i = 0; i < this.listObservers.size(); i++)
			this.listObservers.get(i).update(message);
	}

	

}

}

