package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IReceptWarningMessage;
import fr.lip6.move.coloane.apiws.interfaces.observables.IWarningMessageObservable;
import fr.lip6.move.coloane.apiws.interfaces.observers.IWarningMessageObserver;

public class WarningMessageObservable implements IWarningMessageObservable {

	/**
	 * Liste des observateurs pour l'evenement : 
	 */
	private ArrayList<IWarningMessageObserver> listObservers;
	
	/**
	 * Determine s'il faut creer un thread
	 */
	private boolean createThread;
	
	/**
	 * Constructeur de l'observable de l'evenement :
	 */
	public WarningMessageObservable(){
		this.listObservers = new ArrayList<IWarningMessageObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur de l'observable de l'evenement :
	 * @param createThread notification avec ou sans thread
	 */
	public WarningMessageObservable(boolean createThread){
		this.listObservers = new ArrayList<IWarningMessageObserver>();
		this.createThread = createThread;
	}
	
	public void addObserver(IWarningMessageObserver o) {
		listObservers.add(o);
	}

	public void notifyObservers(IReceptWarningMessage e) {
		if (!createThread){
			for(IWarningMessageObserver o : listObservers){
				o.update(e);
			}
		}
		else{
			/**
			 * A completer
			 */
		}
	}

	public void removeObserver(IWarningMessageObserver o) {
		listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

}
