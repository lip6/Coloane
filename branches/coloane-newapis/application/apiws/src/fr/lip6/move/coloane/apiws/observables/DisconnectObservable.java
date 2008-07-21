package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.api.observables.IDisconnectObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IDisconnectObserver;

public class DisconnectObservable implements IDisconnectObservable {
	
	private ArrayList<IDisconnectObserver> listObservers;
	
	private boolean createThread;
	
	public DisconnectObservable(){
		this.listObservers = new ArrayList<IDisconnectObserver>();
		this.createThread = false;
	}
	
	public DisconnectObservable(boolean createThread){
		this.listObservers = new ArrayList<IDisconnectObserver>();
		this.createThread = createThread;		
	}

	public void addObserver(IDisconnectObserver o) {
		this.listObservers.add(o);
	}

	public void notifyObservers() {
		if (!createThread){
			for (IDisconnectObserver o : listObservers)
				o.update();
		}
		else{
			ThreadNotifier threadNotifier = new ThreadNotifier(listObservers);
			threadNotifier.start();
		}
	}

	public void removeObserver(IDisconnectObserver o) {
		this.listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

	private class ThreadNotifier extends Thread {
		
		private ArrayList<IDisconnectObserver> listObservers;
		
		public ThreadNotifier(ArrayList<IDisconnectObserver> listObservers){
			this.listObservers = listObservers;
		}
		
		public void run(){
			for (IDisconnectObserver o : listObservers)
				o.update();
		}
	}
}