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

	/**
	 * Constructeur
	 */
	public ConnectionObservable(){
		list = new ArrayList<IConnectionObserver>();
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
		for(int i=0; i<this.list.size(); i++)
			this.list.get(i).update(arg);
	}

}
