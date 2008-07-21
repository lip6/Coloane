package fr.lip6.move.coloane.api.observables;


import fr.lip6.move.coloane.api.interfaces.observables.IBrutalInterruptObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ICloseConnectionObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ICloseSessionObservable;
import fr.lip6.move.coloane.api.interfaces.observables.IConnectionObservable;

import fr.lip6.move.coloane.api.interfaces.observables.IReceptDialogObservable;
import fr.lip6.move.coloane.api.interfaces.observables.IReceptResultObservable;

import fr.lip6.move.coloane.api.interfaces.observables.ISessionObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ISpecialMessageObservable;


public class ObservableFactory {

	public static IConnectionObservable getNewConnectionObservable(){
		return new ConnectionObservable();
	}

	public static ISessionObservable getNewSessionObservable(){
		return (ISessionObservable) new SessionObservable();
	}

	

	public static IBrutalInterruptObservable getNewBrutalInterruptObservable() {
		return (IBrutalInterruptObservable) new BrutalInterruptObservable();
	}

	public static IReceptDialogObservable getNewreceptDialogObservable() {
		return (IReceptDialogObservable) new ReceptDialogObservable();
	}

	public static ICloseConnectionObservable getNewCloseConnectionObservable() {
		return (ICloseConnectionObservable) new CloseConnectionObservable();
	}

	
	

	public static ICloseSessionObservable getNewCloseSessionObservable() {
		return (ICloseSessionObservable) new CloseSessionObservable();
	}

	public static ISpecialMessageObservable getNewSpecialMessageObservable() {
	
		return (ISpecialMessageObservable) new SpecialMessageObservable(); 
	}


		public static IReceptResultObservable getNewReceptResultObservable() {
			
			return (IReceptResultObservable) new ReceptResultObservable(); 
		}

		public static IReceptDialogObservable getNewReceptDialogObservable() {
			
			return (IReceptDialogObservable) new ReceptDialogObservable(); 
		}
	
}
