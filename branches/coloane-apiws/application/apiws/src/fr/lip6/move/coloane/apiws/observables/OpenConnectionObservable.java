package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerOpenConnection;
import fr.lip6.move.coloane.apiws.interfaces.observables.IOpenConnectionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observers.IOpenConnectionObserver;

public class OpenConnectionObservable implements IOpenConnectionObservable{

	/**
	 * Liste des observateurs pour l'evenement : 
	 */
	private ArrayList<IOpenConnectionObserver> listObservers;
	
	/**
	 * Determine s'il faut creer un thread
	 */
	private boolean createThread;
	
	/**
	 * Constructeur de l'observable de l'evenement :
	 */
	public OpenConnectionObservable(){
		this.listObservers = new ArrayList<IOpenConnectionObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur de l'observable de l'evenement :
	 * @param createThread notification avec ou sans thread
	 */
	public OpenConnectionObservable(boolean createThread){
		this.listObservers = new ArrayList<IOpenConnectionObserver>();
		this.createThread = createThread;
	}
	
	public void addObserver(IOpenConnectionObserver o) {
		listObservers.add(o);
	}

	public void notifyObservers(IAnswerOpenConnection e) {
		if (!createThread){
			for(IOpenConnectionObserver o : listObservers){
				o.update(e);
			}
		}
		else{
			/**
			 * A completer
			 */
		}
	}

	public void removeObserver(IOpenConnectionObserver o) {
		listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}
	
}
