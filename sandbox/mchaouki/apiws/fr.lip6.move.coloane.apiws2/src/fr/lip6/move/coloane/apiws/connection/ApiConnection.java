package fr.lip6.move.coloane.apiws.connection;

import java.util.HashMap;

import fr.lip6.move.coloane.apiws.interfaces.observables.IObservables;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.IListener;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;
import fr.lip6.move.coloane.apiws.objects.api.ConnectionInfo;
import fr.lip6.move.coloane.apiws.observables.ObservableFactory;
import fr.lip6.move.coloane.apiws.session.SessionFactory;
import fr.lip6.move.coloane.apiws.wrapperCommunication.Listener;
import fr.lip6.move.coloane.apiws.wrapperCommunication.Speaker;
import fr.lip6.move.coloane.interfaces.api.connection.IApiConnection;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.observables.IDisconnectObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptDialogObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptErrorObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptMenuObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptMessageObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptResultObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IDisconnectObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptErrorObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
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
		
		this.listObservables.put(IObservables.RECEPT_DIALOG, ObservableFactory.getNewReceptDialogObservable());
		this.listObservables.put(IObservables.RECEPT_MENU, ObservableFactory.getNewReceptMenuObservable());
		this.listObservables.put(IObservables.RECEPT_MESSAGE, ObservableFactory.getNewReceptMessageObservable());
		this.listObservables.put(IObservables.RECEPT_RESULT, ObservableFactory.getNewReceptResultObservable());
		
		this.sessionController = SessionFactory.getNewSessionController(listObservables);
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

	public boolean setReceptDialogObserver(IReceptDialogObserver o, boolean createThread) {
		IReceptDialogObservable obs = (IReceptDialogObservable) listObservables.get(IObservables.RECEPT_DIALOG);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		return true;
	}

	public boolean setReceptMenuObserver(IReceptMenuObserver o, boolean createThread) {
		IReceptMenuObservable obs = (IReceptMenuObservable) listObservables.get(IObservables.RECEPT_MENU);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		return true;
	}

	public boolean setReceptMessageObserver(IReceptMessageObserver o, boolean createThread) {
		IReceptMessageObservable obs = (IReceptMessageObservable) listObservables.get(IObservables.RECEPT_MESSAGE);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		return true;
	}

	public boolean setReceptResultObserver(IReceptResultObserver o, boolean createThread) {
		IReceptResultObservable obs = (IReceptResultObservable) listObservables.get(IObservables.RECEPT_RESULT);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		return true;
	}
	
	public boolean setReceptErrorObserver(IReceptErrorObserver o,boolean createThread){
		IReceptErrorObservable obs = (IReceptErrorObservable) listObservables.get(IObservables.RECEPT_ERROR);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		return true;
	}
	
	public boolean setDisconnectObserver(IDisconnectObserver o,boolean createThread){
		IDisconnectObservable obs = (IDisconnectObservable) listObservables.get(IObservables.DISCONNECT);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		return true;
	}

	public IConnectionInfo openConnection() throws ApiException {
		if (connectionOpened)
			throw new ApiException("Une connexion est deja ouverte");
		
		this.speaker = new Speaker();
		Authentification auth = speaker.openConnection(login, password);

		this.listener = new Listener(speaker.getAuthentification(),speaker.getStub(),listObservables);
		listener.start();

		connectionOpened = true;
		
		return new ConnectionInfo(auth);
	}
	
	public boolean closeConnection() throws ApiException {
		if (!connectionOpened)
			throw new ApiException("Aucune connexion n'est ouverte");

		((Thread)listener).stop();
		speaker.closeConnection();
		
		connectionOpened = false;
		
		return true;
	}
	
	public IApiSession getApiSession() throws ApiException {
		if (!connectionOpened){
			throw new ApiException("Aucune connexion n'est ouverte");
		}
		return SessionFactory.getNewApiSession(sessionController, speaker);
	}

}
