package fr.lip6.move.coloane.api.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IFkInfo;
import fr.lip6.move.coloane.api.interfaces.IFkVersion;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;
import fr.lip6.move.coloane.api.interfaces.observables.ISessionObservable;
import fr.lip6.move.coloane.api.interfaces.observers.IConnectionObserver;
import fr.lip6.move.coloane.api.interfaces.observers.ISessionObserver;


/**
 * Observable des évènements en rapport avec la session.
 * @author kahoo & uu
 *
 */

public class SessionObservable implements ISessionObservable{


	/** liste des observeurs */
	private ArrayList<ISessionObserver> list;

	/** créer un thread ? */
	private boolean createThread = false;

	/**
	 * Constructeur
	 */
	public SessionObservable(){
		list = new ArrayList<ISessionObserver>();
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
	public void addObserver(ISessionObserver o){
		this.list.add(o);
	}

	/**
	 * Notifier tous les observers
	 * @param arg argument de la notification.
	 */
	public void notifyObservers(IFkInfo fkInfo, ArrayList<IMenu> menu,ArrayList<IUpdateItem> update){

		if(!this.createThread){ /* Option sans création de thread */
			for(int i=0; i<this.list.size(); i++)
				this.list.get(i).update(fkInfo, menu, update);
		}
		else{/* Option avec création de thread */
			ThreadNotifier thread = new ThreadNotifier(this.list,fkInfo,menu,update );
			new Thread(thread,"threadSession").start();
		}

	}

	/**
	 * Cette classe est utilisée pour créer un thread
	 * lors de la notification, si cette option est active.
	 *cette classe est interne.
	 * @author kahoo & uu
	 *
	 */
	private class ThreadNotifier implements Runnable{
		private ArrayList<ISessionObserver> listObservers;
		private IFkInfo fkInfo;
		private ArrayList<IMenu> menu;
		private ArrayList<IUpdateItem> update;

		public ThreadNotifier(ArrayList<ISessionObserver> list,IFkInfo fkInfo,ArrayList<IMenu> menu,ArrayList<IUpdateItem> update ){
			this.listObservers = list;
			this.fkInfo= fkInfo;
			this.menu= menu;
			this.update= update;
		}

		public void run() {
			for(int i=0; i<this.listObservers.size(); i++)
				this.listObservers.get(i).update(this.fkInfo,this.menu,this.update);
		}

	}



}
