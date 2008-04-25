package fr.lip6.move.coloane.api.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IFkVersion;
import fr.lip6.move.coloane.api.interfaces.observables.IConnectionObservable;
import fr.lip6.move.coloane.api.interfaces.observers.IConnectionObserver;

/**
 * Observable des évènements de la connexion
 * @author kahoo & uu
 *
 */

public class ConnectionObservable implements IConnectionObservable{

	/** liste des observateurs */
	private ArrayList<IConnectionObserver> list;

	/** créer un thread ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public ConnectionObservable(){
		list = new ArrayList<IConnectionObserver>();
	}

	/**
	 * set de la variable createThread
	 * @param createThread notification avec ou sans création de thread
	 */
	public void setCreateThread(boolean createThread){
		this.createThread = createThread;
	}

	/**
	 * Ajoute un observer
	 * @param o L'observer à ajouter
	 */
	public void addObserver(IConnectionObserver o){
		this.list.add(o);
	}

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public void notifyObservers(IFkVersion arg){

		if(!this.createThread){ /* Option sans création de thread */
			for(int i=0; i<this.list.size(); i++)
				this.list.get(i).update(arg);
		}
		else{/* Option avec crétion de thread */
			ThreadNotifier thread = new ThreadNotifier(this.list, arg);
			new Thread(thread,"threadConnection").start();
		}

	}

	/**
	 * Cette classe est utilisée pour créer un thread
	 * lors de la notification, si cette option est active.
	 *
	 * @author kahoo & uu
	 *
	 */
	private class ThreadNotifier implements Runnable{
		private ArrayList<IConnectionObserver> listObservers;
		private IFkVersion version;
		public ThreadNotifier(ArrayList<IConnectionObserver> list, IFkVersion arg){
			this.listObservers = list;
			this.version = arg;
		}

		public void run() {
			for(int i=0; i<this.listObservers.size(); i++)
				this.listObservers.get(i).update(version);
		}

	}

}
