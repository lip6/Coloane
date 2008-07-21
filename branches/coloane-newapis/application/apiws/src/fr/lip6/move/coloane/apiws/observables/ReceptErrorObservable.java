package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptError;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptErrorObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptErrorObserver;

public class ReceptErrorObservable implements IReceptErrorObservable {
	
	private ArrayList<IReceptErrorObserver> listObservers;
	
	private boolean createThread;
	
	public ReceptErrorObservable(){
		this.listObservers = new ArrayList<IReceptErrorObserver>();
		this.createThread = false;
	}
	
	public ReceptErrorObservable(boolean createThread){
		this.listObservers = new ArrayList<IReceptErrorObserver>();
		this.createThread = createThread;		
	}

	public void addObserver(IReceptErrorObserver o) {
		this.listObservers.add(o);
	}

	public void notifyObservers(IReceptError e) {
		if (!createThread){
			for (IReceptErrorObserver o : listObservers)
				o.update(e);
		}
		else{
			ThreadNotifier threadNotifier = new ThreadNotifier(listObservers,e);
			threadNotifier.start();
		}
	}

	public void removeObserver(IReceptErrorObserver o) {
		this.listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}
	
	private class ThreadNotifier extends Thread {
		
		private ArrayList<IReceptErrorObserver> listObservers;
		
		private IReceptError error;
		
		public ThreadNotifier(ArrayList<IReceptErrorObserver> listObservers, IReceptError error){
			this.listObservers = listObservers;
			this.error = error;
		}
		
		public void run(){
			for (IReceptErrorObserver o : listObservers)
				o.update(error);
		}
	}

}