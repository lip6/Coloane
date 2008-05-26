package fr.lip6.move.coloane.api.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IFkInfo;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;
import fr.lip6.move.coloane.api.interfaces.observables.IAskForModelObservable;
import fr.lip6.move.coloane.api.interfaces.observers.IAskForModelObserver;
import fr.lip6.move.coloane.api.interfaces.observers.ISessionObserver;


public class AskForModelObservable implements IAskForModelObservable {


	/** liste des observeurs */
	private ArrayList<IAskForModelObserver> list;

	/** créer un thread ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public AskForModelObservable() {
		list = new ArrayList<IAskForModelObserver>();
	}
	public void addObserver(IAskForModelObserver o) {
		this.list.add(o);

	}


	public void notifyObservers() {
		if (!this.createThread) { /* Option sans création de thread */
			for (int i = 0; i < this.list.size(); i++)
				this.list.get(i).update();
		} else {/* Option avec création de thread */
			ThreadNotifier thread = new ThreadNotifier();
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
	private ArrayList<IAskForModelObserver> listObservers;


	public ThreadNotifier() {
		listObservers = new ArrayList<IAskForModelObserver> ();
	}

	public void run() {
		for (int i = 0; i < this.listObservers.size(); i++)
			this.listObservers.get(i).update();
	}

}

}

