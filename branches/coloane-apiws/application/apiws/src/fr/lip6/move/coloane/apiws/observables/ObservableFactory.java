package fr.lip6.move.coloane.apiws.observables;

public class ObservableFactory {
	
	public static AskDialogObservable getNewAskDialogObservable(){
		return (AskDialogObservable) new AskDialogObservable();
	}
	
	public static ChangeSessionObservable getNewChangeSessionObservable(){
		return (ChangeSessionObservable) new ChangeSessionObservable();
	}
	
	public static CloseConnectionObservable getNewCloseConnectionObservable(){
		return (CloseConnectionObservable) new CloseConnectionObservable();
	}
	
	public static CloseSessionObservable getNewCloseSessionObservable(){
		return (CloseSessionObservable) new CloseSessionObservable();
	}
	
	public static ErrorMessageObservable getNewErrorMessageObservable(){
		return (ErrorMessageObservable) new ErrorMessageObservable();
	}
	
	public static ExecutServiceObservable getNewExecutServiceObservable(){
		return (ExecutServiceObservable) new ExecutServiceObservable();
	}
	
	public static OpenConnectionObservable getNewOpenConnectionObservable(){
		return (OpenConnectionObservable) new OpenConnectionObservable();
	}
	
	public static OpenSessionObservable getNewOpenSessionObservable(){
		return (OpenSessionObservable) new OpenSessionObservable();
	}
	
	public static TraceMessageObservable getNewTraceMessageObservable(){
		return (TraceMessageObservable) new TraceMessageObservable();
	}

	public static WarningMessageObservable getNewWarningMessageObservable(){
		return (WarningMessageObservable) new WarningMessageObservable();
	}
	
	public static SendDialogObservable getNewSendDialogObservable(){
		return (SendDialogObservable) new SendDialogObservable();
	}
	
}
