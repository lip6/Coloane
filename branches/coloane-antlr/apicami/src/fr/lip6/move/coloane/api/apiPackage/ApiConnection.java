package fr.lip6.move.coloane.api.apiPackage;


import teststub.TraceMessageObserver;
import fr.lip6.move.coloane.api.interfaces.IAPISession;
import fr.lip6.move.coloane.api.interfaces.IApiConnection;
import fr.lip6.move.coloane.api.interfaces.observers.IBrutalInterruptObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IConnectionObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IDialogObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IFKCloseConnectionObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IServiceObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IServiceStateObserver;
import fr.lip6.move.coloane.api.interfaces.observers.ISessionObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IWarningObserver;

import fr.lip6.move.coloane.api.FkCommunication.FkComFactory;
import fr.lip6.move.coloane.api.interfaces.IListener;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;

public class ApiConnection implements IApiConnection {
	
	/** Constructeur 
	 * Initialise la connexion en créant 
	 *  - le session manager.
	 *  - le thread listner.
	 *  - le speaker
	 * La conexion n'est pas ouverte ici, elle est faite sur l'appel
	 * de la méthode openConnection() après avoir configuré la connexion (méthodes setxxx())
	 */
	public ApiConnection(){
		
		this.state = false; // connexion initialement non ouverte
		// TODO Vérifier que la connexion est configuré
		
		/** Demander un objet Listner */
		listener = FkComFactory.getFkComListener();
		
		/** Demander un Objet speaker */
		speaker = FkComFactory.getFkComSpeaker();
		
		// TODO Demander un objet SessionController
		
		
		
	}

	public boolean closeConnection() {
		// TODO Auto-generated method stub
		return false;
	}

	public IAPISession getAPISession() {
		// TODO Auto-generated method stub
		return null;
	}

	public void openConnection() {
		// TODO Auto-generated method stub

	}

	public boolean setBrutalInterruptObserver(IBrutalInterruptObserver o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setConnectionObserver(IConnectionObserver o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setDialogObserver(IDialogObserver o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setFKCloseConnectionObserver(IFKCloseConnectionObserver o) {
		// TODO Auto-generated method stub
		return false;
	}

	/** set de l'adress IP*/
	public boolean setIPAdress(String ip) {
		this.ipServer = ip;
		return true;
	}

	/** set du login */
	public boolean setLogin(String login) {
		this.login = login;
		return true;
	}

	/** set du password */
	public boolean setPassWord(String pw) {
		this.password = pw;
		return true;
	}

	/** set du port */
	public boolean setPort(int p) {
		this.portServer = p;
		return true;
	}

	public boolean setServiceObserver(IServiceObserver o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setServiceStateObserver(IServiceStateObserver o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setSessionObserver(ISessionObserver o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setTraceMessageObserver(TraceMessageObserver observer) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setWarningObserver(IWarningObserver o) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 *  Etat de la connexion 
	 */
	private boolean state;
	
	/** L'adresse IP du serveur */
	private String ipServer;
	
	/** Port de FK sur le serveur */
	private int portServer;
	
	/** Login */
	private String login;
	
	/** password*/
	private String password;
	
	/** Listener */
	private IListener listener;
	
	/** Speaker */
	private ISpeaker speaker;
}
