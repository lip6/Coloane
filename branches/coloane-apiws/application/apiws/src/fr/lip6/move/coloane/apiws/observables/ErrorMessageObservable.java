package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IReceptErrorMessage;
import fr.lip6.move.coloane.apiws.interfaces.observables.IErrorMessageObservable;
import fr.lip6.move.coloane.apiws.interfaces.observers.IErrorMessagerObserver;

public class ErrorMessageObservable implements IErrorMessageObservable{

	/**
	 * Liste des observateurs pour l'evenement : 
	 */
	private ArrayList<IErrorMessagerObserver> listObservers;
	
	/**
	 * Determine s'il faut creer un thread
	 */
	private boolean createThread;
	
	/**
	 * Constructeur de l'observable de l'evenement :
	 */
	public ErrorMessageObservable(){
		this.listObservers = new ArrayList<IErrorMessagerObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur de l'observable de l'evenement :
	 * @param createThread notification avec ou sans thread
	 */
	public ErrorMessageObservable(boolean createThread){
		this.listObservers = new ArrayList<IErrorMessagerObserver>();
		this.createThread = createThread;
	}
	
	public void addObserver(IErrorMessagerObserver o) {
		listObservers.add(o);
	}

	public void notifyObservers(IReceptErrorMessage e) {
		if (!createThread){
			for(IErrorMessagerObserver o : listObservers){
				o.update(e);
			}
		}
		else{
			/**
			 * A completer
			 */
		}
	}

	public void removeObserver(IErrorMessagerObserver o) {
		listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

}
