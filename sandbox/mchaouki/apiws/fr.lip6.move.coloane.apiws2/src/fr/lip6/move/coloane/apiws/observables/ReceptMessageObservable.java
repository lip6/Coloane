package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptMessageObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;

public class ReceptMessageObservable implements IReceptMessageObservable {
	
	private ArrayList<IReceptMessageObserver> listObservers;
	
	private boolean createThread;
	
	public ReceptMessageObservable(){
		this.listObservers = new ArrayList<IReceptMessageObserver>();
		this.createThread = false;
	}
	
	public ReceptMessageObservable(boolean createThread){
		this.listObservers = new ArrayList<IReceptMessageObserver>();
		this.createThread = createThread;		
	}

	public void addObserver(IReceptMessageObserver o) {
		this.listObservers.add(o);
	}

	public void notifyObservers(IReceptMessage e) {
		if (!createThread){
			for (IReceptMessageObserver o : listObservers)
				o.update(e);
		}
		else{
			// TODO Creer la notification dans un thread
		}
	}

	public void removeObserver(IReceptMessageObserver o) {
		this.listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}
}
