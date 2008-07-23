package fr.lip6.move.coloane.api.observables;

import fr.lip6.move.coloane.api.interfaces.observables.IReceptResultObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ISessionObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IBrutalInterruptObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IDisconnectObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptDialogObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptMessageObservable;

public class ObservableFactory {

	public static ISessionObservable getNewSessionObservable(){
		return (ISessionObservable) new SessionObservable();
	}

	public static IBrutalInterruptObservable getNewBrutalInterruptObservable() {
		return (IBrutalInterruptObservable) new BrutalInterruptObservable();
	}

	public static IReceptDialogObservable getNewreceptDialogObservable() {
		return (IReceptDialogObservable) new ReceptDialogObservable();
	}

	public static IDisconnectObservable getNewCloseConnectionObservable() {
		return (IDisconnectObservable) new DisconnectObservable();
	}

	public static IReceptMessageObservable getNewSpecialMessageObservable() {
		return (IReceptMessageObservable) new SpecialMessageObservable();
	}

	public static IReceptResultObservable getNewReceptResultObservable() {
		return (IReceptResultObservable) new ReceptResultObservable();
	}

	public static IReceptDialogObservable getNewReceptDialogObservable() {
		return (IReceptDialogObservable) new ReceptDialogObservable();
	}
}
