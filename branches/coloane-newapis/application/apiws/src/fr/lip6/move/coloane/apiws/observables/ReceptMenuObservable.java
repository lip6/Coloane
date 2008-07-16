package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMenu;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptMenuObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;

public class ReceptMenuObservable implements IReceptMenuObservable {
	
	private ArrayList<IReceptMenuObserver> listObservers;
	
	private boolean createThread;
	
	public ReceptMenuObservable(){
		this.listObservers = new ArrayList<IReceptMenuObserver>();
		this.createThread = false;
	}
	
	public ReceptMenuObservable(boolean createThread){
		this.listObservers = new ArrayList<IReceptMenuObserver>();
		this.createThread = createThread;		
	}

	public void addObserver(IReceptMenuObserver o) {
		this.listObservers.add(o);
	}

	public void notifyObservers(IReceptMenu e) {
		if (!createThread){
			for (IReceptMenuObserver o : listObservers)
				o.update(e);
		}
		else{
			// TODO Creer la notification dans un thread
		}
	}

	public void removeObserver(IReceptMenuObserver o) {
		this.listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

}