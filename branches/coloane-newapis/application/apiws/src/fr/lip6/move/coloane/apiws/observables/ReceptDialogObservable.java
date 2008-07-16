package fr.lip6.move.coloane.apiws.observables;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.api.observables.IReceptDialogObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

public class ReceptDialogObservable implements IReceptDialogObservable {

	private ArrayList<IReceptDialogObserver> listObservers;
	
	private boolean createThread;
	
	public ReceptDialogObservable(){
		this.listObservers = new ArrayList<IReceptDialogObserver>();
		this.createThread = false;
	}
	
	public ReceptDialogObservable(boolean createThread){
		this.listObservers = new ArrayList<IReceptDialogObserver>();
		this.createThread = createThread;		
	}
	
	public void addObserver(IReceptDialogObserver o) {
		this.listObservers.add(o);
	}

	public void notifyObservers(IDialog dialog) {
		if (!createThread){
			for (IReceptDialogObserver o : listObservers)
				o.update(dialog);
		}
		else{
			// TODO Creer la notification dans un thread
		}
	}

	public void removeObserver(IReceptDialogObserver o) {
		this.listObservers.remove(o);
	}

	public void setCreateThread(boolean createThread) {
		this.createThread = createThread;
	}

}