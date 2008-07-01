package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerSuspendSession;
import fr.lip6.move.coloane.apiws.interfaces.observables.ISuspendSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observers.ISuspendSessionObserver;

public class SuspendSessionObservable implements ISuspendSessionObservable {
	/**
	 * Liste des observateurs pour l'evenement : reception d'une boite de dialogue
	 */
	private ArrayList<ISuspendSessionObserver> listObservers;
	
	/**
	 * Determine s'il faut creer un thread pour la notification
	 */
	private boolean createThread;
	
	/**
	 * Constructeur de l'observable de l'evenement : reception d'une boite de dialogue
	 */
	public SuspendSessionObservable(){
		this.listObservers = new ArrayList<ISuspendSessionObserver>();
		this.createThread = false;
	}
	
	/**
	 * Constructeur de l'observable de l'evenement : reception d'une boite de dialogue
	 * @param createThread notification avec ou sans thread
	 */
	public SuspendSessionObservable(boolean createThread){
		this.listObservers = new ArrayList<ISuspendSessionObserver>();
		this.createThread = createThread;		
	}
	
	public void addObserver(ISuspendSessionObserver o) {
		listObservers.add(o);
	}

	public void notifyObservers(IAnswerSuspendSession e) {
		if (!createThread){
			for(ISuspendSessionObserver o: listObservers){
				o.update(e);
			}
		}
		else{
			/**
			 * A completer
			 */
		}
	}

	public void removeObserver(ISuspendSessionObserver o) {
		listObservers.remove(o);
		
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

}
