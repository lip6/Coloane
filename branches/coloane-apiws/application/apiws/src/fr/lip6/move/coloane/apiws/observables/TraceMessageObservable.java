package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IReceptTraceMessage;
import fr.lip6.move.coloane.apiws.interfaces.observables.ITraceMessageObservable;
import fr.lip6.move.coloane.apiws.interfaces.observers.ITraceMessageObserver;

public class TraceMessageObservable implements ITraceMessageObservable{

	/**
	 * Liste des observateurs pour l'evenement : 
	 */
	private ArrayList<ITraceMessageObserver> listObservers;
	
	/**
	 * Determine s'il faut creer un thread
	 */
	private boolean createThread;
	
	/**
	 * Constructeur de l'observable de l'evenement :
	 */
	public TraceMessageObservable(){
		this.listObservers = new ArrayList<ITraceMessageObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur de l'observable de l'evenement :
	 * @param createThread notification avec ou sans thread
	 */
	public TraceMessageObservable(boolean createThread){
		this.listObservers = new ArrayList<ITraceMessageObserver>();
		this.createThread = createThread;
	}
	
	public void addObserver(ITraceMessageObserver o) {
		listObservers.add(o);
	}

	public void notifyObservers(IReceptTraceMessage e) {
		if (!createThread){
			for(ITraceMessageObserver o : listObservers){
				o.update(e);
			}
		}
		else{
			/**
			 * A completer
			 */
		}
	}

	public void removeObserver(ITraceMessageObserver o) {
		listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

}
