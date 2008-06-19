package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerChangeSession;
import fr.lip6.move.coloane.apiws.interfaces.observables.IChangeSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observers.IChangeSessionObserver;

public class ChangeSessionObservable implements IChangeSessionObservable {

	/**
	 * Liste des observateurs pour l'evenement : 
	 */
	private ArrayList<IChangeSessionObserver> listObservers;
	
	/**
	 * Determine s'il faut creer un thread
	 */
	private boolean createThread;
	
	/**
	 * Constructeur de l'observable de l'evenement :
	 */
	public ChangeSessionObservable(){
		this.listObservers = new ArrayList<IChangeSessionObserver>();
		this.createThread = false;
	}
	
	/**
	 * Constructeur de l'observable de l'evenement :
	 * @param createThread notification avec ou sans thread
	 */
	public ChangeSessionObservable(boolean createThread){
		this.listObservers = new ArrayList<IChangeSessionObserver>();
		this.createThread = createThread;		
	}
	
	public void addObserver(IChangeSessionObserver o) {
		listObservers.add(o);
	}

	public void notifyObservers(IAnswerChangeSession e) {
		if (!createThread){
			for (IChangeSessionObserver o : listObservers){
				o.update(e);
			}
		}
		else{
			/**
			 * A completer
			 */
		}
	}

	public void removeObserver(IChangeSessionObserver o) {
		listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

}
