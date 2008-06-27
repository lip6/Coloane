package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerSendDialog;
import fr.lip6.move.coloane.apiws.interfaces.observables.ISendDialogObservable;
import fr.lip6.move.coloane.apiws.interfaces.observers.ISendDialogObserver;

public class SendDialogObservable implements ISendDialogObservable {
	
	/**
	 * Liste des observateurs pour l'evenement : reception d'une boite de dialogue
	 */
	private ArrayList<ISendDialogObserver> listObservers;
	
	/**
	 * Determine s'il faut creer un thread pour la notification
	 */
	private boolean createThread;
	
	/**
	 * Constructeur de l'observable de l'evenement : reception d'une boite de dialogue
	 */
	public SendDialogObservable(){
		this.listObservers = new ArrayList<ISendDialogObserver>();
		this.createThread = false;
	}
	
	/**
	 * Constructeur de l'observable de l'evenement : reception d'une boite de dialogue
	 * @param createThread notification avec ou sans thread
	 */
	public SendDialogObservable(boolean createThread){
		this.listObservers = new ArrayList<ISendDialogObserver>();
		this.createThread = createThread;		
	}
	
	public void addObserver(ISendDialogObserver o) {
		listObservers.add(o);
	}

	public void notifyObservers(IAnswerSendDialog e) {
		if (!createThread){
			for(ISendDialogObserver o: listObservers){
				o.update(e);
			}
		}
		else{
			/**
			 * A completer
			 */
		}
	}

	public void removeObserver(ISendDialogObserver o) {
		listObservers.remove(o);
		
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

}
