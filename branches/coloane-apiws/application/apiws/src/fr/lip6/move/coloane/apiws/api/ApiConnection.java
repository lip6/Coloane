package fr.lip6.move.coloane.apiws.api;

import java.util.HashMap;

import fr.lip6.move.coloane.apiws.evenements.AnswerOpenConnection;
import fr.lip6.move.coloane.apiws.interfaces.api.IApiConnection;
import fr.lip6.move.coloane.apiws.interfaces.observables.IAskDialogObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IChangeSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.ICloseConnectionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.ICloseSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IErrorMessageObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IExecutServiceObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IObservables;
import fr.lip6.move.coloane.apiws.interfaces.observables.IOpenConnectionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IOpenSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.ITraceMessageObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IWarningMessageObservable;
import fr.lip6.move.coloane.apiws.interfaces.observers.IAskDialogObserver;
import fr.lip6.move.coloane.apiws.interfaces.observers.IChangeSessionObserver;
import fr.lip6.move.coloane.apiws.interfaces.observers.ICloseConnectionObserver;
import fr.lip6.move.coloane.apiws.interfaces.observers.ICloseSessionObserver;
import fr.lip6.move.coloane.apiws.interfaces.observers.IErrorMessagerObserver;
import fr.lip6.move.coloane.apiws.interfaces.observers.IExecutServiceObserver;
import fr.lip6.move.coloane.apiws.interfaces.observers.IOpenConnectionObserver;
import fr.lip6.move.coloane.apiws.interfaces.observers.IOpenSessionObserver;
import fr.lip6.move.coloane.apiws.interfaces.observers.ITraceMessageObserver;
import fr.lip6.move.coloane.apiws.interfaces.observers.IWarningMessageObserver;
import fr.lip6.move.coloane.apiws.interfaces.session.IApiSession;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.IListener;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;
import fr.lip6.move.coloane.apiws.observables.ObservableFactory;
import fr.lip6.move.coloane.apiws.session.SessionFactory;
import fr.lip6.move.coloane.apiws.wrapperCommunication.Listener;
import fr.lip6.move.coloane.apiws.wrapperCommunication.Speaker;
import fr.lip6.move.wrapper.ws.CException;
import fr.lip6.move.wrapper.ws.WrapperStub.Authentification;

public class ApiConnection implements IApiConnection {
	
	private boolean connectionOpened;
	
	private String ipServer;
	
	private String login;
	
	private String password;
	
	private int portServer;
	
	private ISessionController sessionController;
	
	private ISpeaker speaker;
	
	private IListener listener;
	
	private HashMap<Integer, Object> listObservables;
	
	public ApiConnection(){
		this.connectionOpened = false;
		
		this.listObservables = new HashMap<Integer, Object>();
		this.listObservables.put(IObservables.ASK_DIALOG, ObservableFactory.getNewAskDialogObservable());
		this.listObservables.put(IObservables.CHANGE_SESSION, ObservableFactory.getNewChangeSessionObservable());
		this.listObservables.put(IObservables.CLOSE_CONNECTION, ObservableFactory.getNewCloseConnectionObservable());
		this.listObservables.put(IObservables.CLOSE_SESSION, ObservableFactory.getNewCloseSessionObservable());
		this.listObservables.put(IObservables.ERROR_MESSAGE, ObservableFactory.getNewErrorMessageObservable());
		this.listObservables.put(IObservables.EXECUT_SERVICE, ObservableFactory.getNewExecutServiceObservable());
		this.listObservables.put(IObservables.OPEN_CONNECTION, ObservableFactory.getNewOpenConnectionObservable());
		this.listObservables.put(IObservables.OPEN_SESSION, ObservableFactory.getNewOpenSessionObservable());
		this.listObservables.put(IObservables.TRACE_MESSAGE, ObservableFactory.getNewTraceMessageObservable());
		this.listObservables.put(IObservables.WARNING_MESSAGE, ObservableFactory.getNewWarningMessageObservable());
		
		this.sessionController = SessionFactory.getNewSessionController();
		
	}
	
	public boolean setIpServer(String ipServer) {
		this.ipServer = ipServer;
		return true;
	}

	public boolean setLogin(String login) {
		this.login = login;
		return true;
	}

	public boolean setPassword(String password) {
		this.password = password;
		return true;
	}

	public boolean setPortServer(int portServer) {
		this.portServer = portServer;
		return true;
	}
	
	public IApiSession getApiSession() {
		if (!connectionOpened){
			//throw Exception
		}
		return SessionFactory.getNewApiSession(sessionController, speaker, listObservables);
	}

	public boolean openConnection() throws CException {
		if (connectionOpened)
			return false;
		
		this.speaker = new Speaker();
		Authentification auth = speaker.openConnection(login, password);

		this.listener = new Listener(speaker.getAuthentification(),speaker.getStub(),listObservables);
		listener.start();

		AnswerOpenConnection answerOpenConnection = new AnswerOpenConnection(auth);
		((IOpenConnectionObservable) listObservables.get(IObservables.OPEN_CONNECTION)).notifyObservers(answerOpenConnection);

		return true;
	}

	
	public boolean closeConnection() throws CException {
		if (!connectionOpened)
			return false;
		
		speaker.closeConnection();
		listener.stopper();
		
		return true;
	}


	public boolean setOpenConnectionObserver(IOpenConnectionObserver o, boolean createThread) {
		IOpenConnectionObservable obs = (IOpenConnectionObservable) listObservables.get(IObservables.OPEN_CONNECTION);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		return true;
	}

	public boolean setOpenSessionObsever(IOpenSessionObserver o, boolean createThread) {
		IOpenSessionObservable obs = (IOpenSessionObservable) listObservables.get(IObservables.OPEN_SESSION);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		return true;
	}
	
	public boolean setExecutServiceObserver(IExecutServiceObserver o, boolean createThread) {
		IExecutServiceObservable obs = (IExecutServiceObservable) listObservables.get(IObservables.EXECUT_SERVICE);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		return true;
	}

	public boolean setAskDialogObserver(IAskDialogObserver o, boolean createThread) {
		IAskDialogObservable obs = (IAskDialogObservable) listObservables.get(IObservables.ASK_DIALOG);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		return true;
	}

	public boolean setChangeSessionObserver(IChangeSessionObserver o, boolean createThread) {
		IChangeSessionObservable obs = (IChangeSessionObservable) listObservables.get(IObservables.CHANGE_SESSION);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		return true;
	}
	
	public boolean setCloseSessionObserver(ICloseSessionObserver o, boolean createThread) {
		ICloseSessionObservable obs = (ICloseSessionObservable) listObservables.get(IObservables.CLOSE_SESSION);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		return true;
	}

	public boolean setCloseConnectionObserver(ICloseConnectionObserver o, boolean createThread) {
		ICloseConnectionObservable obs = (ICloseConnectionObservable) listObservables.get(IObservables.CLOSE_CONNECTION);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		return true;
	}

	public boolean setErrorMessageObserver(IErrorMessagerObserver o, boolean createThread) {
		IErrorMessageObservable obs = (IErrorMessageObservable) listObservables.get(IObservables.ERROR_MESSAGE);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		return true;
	}

	public boolean setTraceMessageObserver(ITraceMessageObserver o, boolean createThread) {
		ITraceMessageObservable obs = (ITraceMessageObservable) listObservables.get(IObservables.TRACE_MESSAGE);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		return true;
	}

	public boolean setWarningMessageObserver(IWarningMessageObserver o, boolean createThread) {
		IWarningMessageObservable obs = (IWarningMessageObservable) listObservables.get(IObservables.WARNING_MESSAGE);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		return true;
	}	

}
