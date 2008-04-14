package fr.lip6.move.coloane.api.apiPackage;


import teststub.BrutalInterruptObserver;
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
import fr.lip6.move.coloane.api.interfaces.observers.ITraceMessageObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IWarningObserver;

import fr.lip6.move.coloane.api.FkCommunication.FkComFactory;
import fr.lip6.move.coloane.api.interfaces.IListener;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;

public class ApiConnection implements IApiConnection {

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

	/**  IBrutalInterruptObserver */
	private IBrutalInterruptObserver bio;

	/** IConnectionObserver */
	private IConnectionObserver ico;

	/** IDialogObserver */
	private IDialogObserver ido;

	/** IFKCloseConnectionObserver*/
	private IFKCloseConnectionObserver ifko;

	/** IServiceObserver*/
	private IServiceObserver iso;

	/** IServiceStateObserver */
	private IServiceStateObserver isso;

	/** ISessionObserver  */
	private ISessionObserver iseo;

	/** ITraceMessageObserver */
	private ITraceMessageObserver itmo;

	/** IWarningObserver*/
	private IWarningObserver iwo;

	/** Constructeur
	 * Initialise la connexion en créant :
	 *  - le thread listener.
	 *  - le speaker.
	 * La connexion n'est pas ouverte ici, elle est faite sur l'appel
	 * de la méthode openConnection() après avoir configuré la connexion (méthodes setxxx())
	 */
	public ApiConnection(){

		this.state = false; // connexion initialement non ouverte

		/** Demander un objet Listner */
		listener = FkComFactory.getFkComListener();

		/** Demander un Objet speaker */
		speaker = FkComFactory.getFkComSpeaker();


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
		// TODO Vérifier que la connexion est configuré

	}

	/** set du IBrutalInterruptObserver*/
	public boolean setBrutalInterruptObserver(IBrutalInterruptObserver o) {
		this.bio = o;
		return true;
	}

	/** set du IConnectionObserver*/
	public boolean setConnectionObserver(IConnectionObserver o) {
		this.ico = o;
		return true;
	}

	/** set du IDialogObserver*/
	public boolean setDialogObserver(IDialogObserver o) {
		this.ido = o;
		return true;
	}

	/** set du IFKCloseConnectionObserver*/
	public boolean setFKCloseConnectionObserver(IFKCloseConnectionObserver o) {
		this.ifko = o;
		return true;
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

	/** set du IServiceObserver */
	public boolean setServiceObserver(IServiceObserver o) {
		this.iso = o;
		return true;
	}

	/** set du  IServiceStateObserver*/
	public boolean setServiceStateObserver(IServiceStateObserver o) {
		this.isso = o;
		return true;
	}

	/** set du ISessionObserver */
	public boolean setSessionObserver(ISessionObserver o) {
		this.iseo = o;
		return true;
	}

	/** set du ITraceMessageObserver  */
	public boolean setTraceMessageObserver(ITraceMessageObserver o) {
		this.itmo = o;
		return true;
	}

	/** set du IWarningObserver*/
	public boolean setWarningObserver(IWarningObserver o) {
		this.iwo = o;
		return true;
	}



}
