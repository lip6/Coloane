package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerCloseConnection;
import fr.lip6.move.coloane.apiws.interfaces.observables.ICloseConnectionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observers.ICloseConnectionObserver;

public class CloseConnectionObservable implements ICloseConnectionObservable {

	/**
	 * Liste des observateurs pour l'evenement : 
	 */
	private ArrayList<ICloseConnectionObserver> listObservers;
	
	/**
	 * Determine s'il faut creer un thread
	 */
	private boolean createThread;
	
	/**
	 * Constructeur de l'observable de l'evenement :
	 */
	public CloseConnectionObservable(){
		this.listObservers = new ArrayList<ICloseConnectionObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur de l'observable de l'evenement :
	 * @param createThread notification avec ou sans thread
	 */
	public CloseConnectionObservable(boolean createThread){
		this.listObservers = new ArrayList<ICloseConnectionObserver>();
		this.createThread = createThread;
	}
	
	public void addObserver(ICloseConnectionObserver o) {
		listObservers.add(o);
	}

	public void notifyObservers(IAnswerCloseConnection e) {
		if (!createThread){
			for (ICloseConnectionObserver o: listObservers){
				o.update(e);
			}
		}
		else{
			/**
			 * A completer
			 */
		}
	}

	public void removeObserver(ICloseConnectionObserver o) {
		listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

}
