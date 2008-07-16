package fr.lip6.move.coloane.apiws.observables;

import fr.lip6.move.coloane.interfaces.api.observables.IDisconnectObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptDialogObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptErrorObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptMenuObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptMessageObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptResultObservable;

public class ObservableFactory {
	
	public static IReceptDialogObservable getNewReceptDialogObservable(){
		return (IReceptDialogObservable) new ReceptDialogObservable();
	}
	
	public static IReceptMenuObservable getNewReceptMenuObservable(){
		return (IReceptMenuObservable) new ReceptMenuObservable();
	}
	
	public static IReceptMessageObservable getNewReceptMessageObservable(){
		return (IReceptMessageObservable) new ReceptMessageObservable();
	}
	
	public static IReceptResultObservable getNewReceptResultObservable(){
		return (IReceptResultObservable) new ReceptResultObservable();
	}
	
	public static IReceptErrorObservable getNewReceptErrorObservable(){
		return (IReceptErrorObservable) new ReceptErrorObservable();
	}
	
	public static IDisconnectObservable getNewDisconnectObservable(){
		return (IDisconnectObservable) new DisconnectObservable();
	}
}