package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerExecutService;
import fr.lip6.move.coloane.apiws.interfaces.observables.IExecutServiceObservable;
import fr.lip6.move.coloane.apiws.interfaces.observers.IExecutServiceObserver;

public class ExecutServiceObservable implements IExecutServiceObservable {

	/**
	 * Liste des observateurs pour l'evenement : 
	 */
	private ArrayList<IExecutServiceObserver> listObservers;
	
	/**
	 * Determine s'il faut creer un thread
	 */
	private boolean createThread;
	
	/**
	 * Constructeur de l'observable de l'evenement :
	 */
	public ExecutServiceObservable(){
		this.listObservers = new ArrayList<IExecutServiceObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur de l'observable de l'evenement :
	 * @param createThread notification avec ou sans thread
	 */
	public ExecutServiceObservable(boolean createThread){
		this.listObservers = new ArrayList<IExecutServiceObserver>();
		this.createThread = createThread;
	}
	
	public void addObserver(IExecutServiceObserver o) {
		listObservers.add(o);
	}

	public void notifyObservers(IAnswerExecutService e) {
		if (!createThread){
			for(IExecutServiceObserver o : listObservers){
				o.update(e);
			}
		}
		else{
			/**
			 * A completer
			 */
		}
	}

	public void removeObserver(IExecutServiceObserver o) {
		listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

}
