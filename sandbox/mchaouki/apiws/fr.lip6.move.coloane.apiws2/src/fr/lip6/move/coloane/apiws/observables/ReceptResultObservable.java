package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptResult;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptResultObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;

public class ReceptResultObservable implements IReceptResultObservable {
	
	private ArrayList<IReceptResultObserver> listObservers;
	
	private boolean createThread;
	
	public ReceptResultObservable(){
		this.listObservers = new ArrayList<IReceptResultObserver>();
		this.createThread = false;
	}
	
	public ReceptResultObservable(boolean createThread){
		this.listObservers = new ArrayList<IReceptResultObserver>();
		this.createThread = createThread;		
	}
	
	public void addObserver(IReceptResultObserver o) {
		this.listObservers.add(o);
	}

	public void notifyObservers(IReceptResult e) {
		if (!createThread){
			for (IReceptResultObserver o : listObservers)
				o.update(e);
		}
		else{
			// TODO Creer la notification dans un thread
		}
	}

	public void removeObserver(IReceptResultObserver o) {
		this.listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

}
