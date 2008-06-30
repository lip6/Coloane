package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAskDialog;
import fr.lip6.move.coloane.apiws.interfaces.observables.IAskDialogObservable;
import fr.lip6.move.coloane.apiws.interfaces.observers.IAskDialogObserver;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.IListener;

public class AskDialogObservable implements IAskDialogObservable{

	/**
	 * Liste des observateurs pour l'evenement : reception d'une boite de dialogue
	 */
	private ArrayList<IAskDialogObserver> listObservers;
	
	/**
	 * Determine s'il faut creer un thread pour la notification
	 */
	private boolean createThread;
	
	/**
	 * Constructeur de l'observable de l'evenement : reception d'une boite de dialogue
	 */
	public AskDialogObservable(){
		this.listObservers = new ArrayList<IAskDialogObserver>();
		this.createThread = false;
	}
	
	/**
	 * Constructeur de l'observable de l'evenement : reception d'une boite de dialogue
	 * @param createThread notification avec ou sans thread
	 */
	public AskDialogObservable(boolean createThread){
		this.listObservers = new ArrayList<IAskDialogObserver>();
		this.createThread = createThread;		
	}
	
	public void addObserver(IAskDialogObserver o) {
		listObservers.add(o);
	}

	public void notifyObservers(IAskDialog e, IListener asynchronousSpeaker) {
		if (!createThread){
			for(IAskDialogObserver o: listObservers){
				o.update(e, asynchronousSpeaker);
			}
		}
		else{
			/**
			 * A completer
			 */
		}
	}

	public void removeObserver(IAskDialogObserver o) {
		listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

}
