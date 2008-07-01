package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerResumeSession;
import fr.lip6.move.coloane.apiws.interfaces.observables.IResumeSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observers.IResumeSessionObserver;

public class ResumeSessionObservable implements IResumeSessionObservable {
	/**
	 * Liste des observateurs pour l'evenement : reception d'une boite de dialogue
	 */
	private ArrayList<IResumeSessionObserver> listObservers;
	
	/**
	 * Determine s'il faut creer un thread pour la notification
	 */
	private boolean createThread;
	
	/**
	 * Constructeur de l'observable de l'evenement : reception d'une boite de dialogue
	 */
	public ResumeSessionObservable(){
		this.listObservers = new ArrayList<IResumeSessionObserver>();
		this.createThread = false;
	}
	
	/**
	 * Constructeur de l'observable de l'evenement : reception d'une boite de dialogue
	 * @param createThread notification avec ou sans thread
	 */
	public ResumeSessionObservable(boolean createThread){
		this.listObservers = new ArrayList<IResumeSessionObserver>();
		this.createThread = createThread;		
	}
	
	public void addObserver(IResumeSessionObserver o) {
		listObservers.add(o);
	}

	public void notifyObservers(IAnswerResumeSession e) {
		if (!createThread){
			for(IResumeSessionObserver o: listObservers){
				o.update(e);
			}
		}
		else{
			/**
			 * A completer
			 */
		}
	}

	public void removeObserver(IResumeSessionObserver o) {
		listObservers.remove(o);
		
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

}
