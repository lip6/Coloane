package fr.lip6.move.coloane.api.observables;


import fr.lip6.move.coloane.api.interfaces.observables.IBrutalInterruptObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ICloseConnectionObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ICloseSessionObservable;
import fr.lip6.move.coloane.api.interfaces.observables.IConnectionObservable;
import fr.lip6.move.coloane.api.interfaces.observables.IDialogObservable;
import fr.lip6.move.coloane.api.interfaces.observables.IServiceObservable;
import fr.lip6.move.coloane.api.interfaces.observables.IServiceStateObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ISessionObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ITraceMessageObservable;
import fr.lip6.move.coloane.api.interfaces.observables.IWarningObservable;

public class ObservableFactory {

	public static IConnectionObservable getNewConnectionObservable(){
		return new ConnectionObservable();
	}

	public static ISessionObservable getNewSessionObservable(){
		return (ISessionObservable) new SessionObservable();
	}

	public static IServiceObservable getNewServiceObservable(){
		return (IServiceObservable) new ServiceObservable();
	}

	public static IBrutalInterruptObservable getNewBrutalInterruptObservable() {
		return (IBrutalInterruptObservable) new BrutalInterruptObservable();
	}

	public static IDialogObservable getNewDialogObservable() {
		return (IDialogObservable) new DialogObservable();
	}

	public static ICloseConnectionObservable getNewCloseConnectionObservable() {
		return (ICloseConnectionObservable) new CloseConnectionObservable();
	}

	public static IServiceStateObservable getNewServiceStateObservable() {
		return (IServiceStateObservable) new ServiceStateObservable();
	}

	public static ITraceMessageObservable getNewTraceMessageObservable() {
		return (ITraceMessageObservable) new TraceMessageObservable();
	}

	public static IWarningObservable getNewWarningObservable() {
		return (IWarningObservable) new WarningObservable();
	}

	

	public static Object getNewCloseSessionObservable() {
		return (ICloseSessionObservable) new CloseSessionObservable();
	}
}
