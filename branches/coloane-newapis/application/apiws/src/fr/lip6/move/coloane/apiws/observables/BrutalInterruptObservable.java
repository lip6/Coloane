package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.api.observables.IBrutalInterruptObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IBrutalInterruptObserver;

public class BrutalInterruptObservable implements IBrutalInterruptObservable {
	private ArrayList<IBrutalInterruptObserver> listObservers;
	
	private boolean createThread;
	
	public BrutalInterruptObservable(){
		this.listObservers = new ArrayList<IBrutalInterruptObserver>();
		this.createThread = false;
	}
	
	public BrutalInterruptObservable(boolean createThread){
		this.listObservers = new ArrayList<IBrutalInterruptObserver>();
		this.createThread = createThread;		
	}

	public void addObserver(IBrutalInterruptObserver o) {
		this.listObservers.add(o);
	}

	public void notifyObservers(String e) {
		if (!createThread){
			for (IBrutalInterruptObserver o : listObservers)
				o.update(e);
		}
		else{
			ThreadNotifier threadNotifier = new ThreadNotifier(listObservers,e);
			threadNotifier.start();
		}
	}

	public void removeObserver(IBrutalInterruptObserver o) {
		this.listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}
	
	private class ThreadNotifier extends Thread {
		
		private ArrayList<IBrutalInterruptObserver> listObservers;
		
		private String error;
		
		public ThreadNotifier(ArrayList<IBrutalInterruptObserver> listObservers, String error){
			this.listObservers = listObservers;
			this.error = error;
		}
		
		public void run(){
			for (IBrutalInterruptObserver o : listObservers)
				o.update(error);
		}
	}
}
