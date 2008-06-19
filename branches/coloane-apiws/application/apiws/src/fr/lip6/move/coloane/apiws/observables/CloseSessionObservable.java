package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerCloseSession;
import fr.lip6.move.coloane.apiws.interfaces.observables.ICloseSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observers.ICloseSessionObserver;

public class CloseSessionObservable implements ICloseSessionObservable{

	/**
	 * Liste des observateurs pour l'evenement : 
	 */
	private ArrayList<ICloseSessionObserver> listObservers;
	
	/**
	 * Determine s'il faut creer un thread
	 */
	private boolean createThread;
	
	/**
	 * Constructeur de l'observable de l'evenement :
	 */
	public CloseSessionObservable(){
		this.listObservers = new ArrayList<ICloseSessionObserver>();
		this.createThread = false;
	}

	/**
	 * Constructeur de l'observable de l'evenement :
	 * @param createThread notification avec ou sans thread
	 */
	public CloseSessionObservable(boolean createThread){
		this.listObservers = new ArrayList<ICloseSessionObserver>();
		this.createThread = createThread;
	}
	
	public void addObserver(ICloseSessionObserver o) {
		listObservers.add(o);
	}

	public void notifyObservers(IAnswerCloseSession e) {
		if (!createThread){
			for (ICloseSessionObserver o : listObservers){
				o.update(e);
			}
		}
		else{
			/**
			 * A completer
			 */
		}
	}

	public void removeObserver(ICloseSessionObserver o) {
		listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

}
