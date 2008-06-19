package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerOpenSession;
import fr.lip6.move.coloane.apiws.interfaces.observables.IOpenSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observers.IOpenSessionObserver;

public class OpenSessionObservable implements IOpenSessionObservable {

	/**
	 * Liste des observateurs pour l'evenement : 
	 */
	private ArrayList<IOpenSessionObserver> listObservers;
	
	/**
	 * Determine s'il faut creer un thread
	 */
	private boolean createThread;
	
	/**
	 * Constructeur de l'observable de l'evenement :
	 */
	public OpenSessionObservable(){
		this.listObservers = new ArrayList<IOpenSessionObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur de l'observable de l'evenement :
	 * @param createThread notification avec ou sans thread
	 */
	public OpenSessionObservable(boolean createThread){
		this.listObservers = new ArrayList<IOpenSessionObserver>();
		this.createThread = createThread;
	}
	
	public void addObserver(IOpenSessionObserver o) {
		listObservers.add(o);
	}

	public void notifyObservers(IAnswerOpenSession e) {
		if (!createThread){
			for(IOpenSessionObserver o : listObservers){
				o.update(e);
			}
		}
		else{
			/**
			 * A completer
			 */
		}
	}

	public void removeObserver(IOpenSessionObserver o) {
		listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

}
