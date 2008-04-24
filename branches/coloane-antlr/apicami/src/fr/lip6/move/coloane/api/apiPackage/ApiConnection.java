package fr.lip6.move.coloane.api.apiPackage;



import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

import fr.lip6.move.coloane.api.FkCommunication.FkInitCom;
import fr.lip6.move.coloane.api.FkCommunication.Pair;
import fr.lip6.move.coloane.api.cami.ThreadParser;
import fr.lip6.move.coloane.api.session.SessionFactory;
import fr.lip6.move.coloane.api.interfaces.IApiSession;
import fr.lip6.move.coloane.api.interfaces.IApiConnection;
import fr.lip6.move.coloane.api.interfaces.observables.IConnectionObservable;
import fr.lip6.move.coloane.api.interfaces.observers.IBrutalInterruptObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IConnectionObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IDialogObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IFKCloseConnectionObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IServiceObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IServiceStateObserver;
import fr.lip6.move.coloane.api.interfaces.observers.ISessionObserver;
import fr.lip6.move.coloane.api.interfaces.observers.ITraceMessageObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IWarningObserver;

import fr.lip6.move.coloane.api.interfaces.IListener;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;
import fr.lip6.move.coloane.api.observables.ObservableFactory;

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

	private HashMap< String, Object> hashObservable;

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
	 * @throws IOException
	 */
	public ApiConnection() throws IOException{

		// TODO

		this.hashObservable = new HashMap< String, Object>();
		this.hashObservable.put("IConnection", ObservableFactory.getNewConnectionObservable());
	}

	public boolean closeConnection() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * retourne une IApiSession
	 */
	public IApiSession getAPISession() {
		return SessionFactory.getNewApiSession();
	}

	/**
	 * initie la connexion avec FrameKit (SC, OC)
	 */
	public void openConnection() {
		// TODO Auto-generated method stub
		// TODO Vérifier que la connexion est configuré

		Pair<ISpeaker, IListener> p;
		this.state = false; // connexion initialement non ouverte

		/* Créer la file Queue entre le parser et le
		 * thread Listener */

		LinkedBlockingQueue<InputStream> fifo = new LinkedBlockingQueue();

		/* créer le parser */
		ThreadParser parser = new ThreadParser(fifo, this.hashObservable);

		try { /** initialiser la connexion */

			/* Créer le Thread Listener et le speaker */
			p = FkInitCom.initCom(this.ipServer, this.portServer, fifo);
			this.listener = p.getListener();
		    this.speaker = p.getSpeaker();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    /* Lancer le parser */
	    parser.start();

	    /* Demander l'ouverture de la communication Commande SC et attendre
	     * l'aquittement de FK */

	    synchronized(this.hashObservable){
	       	try {
	       		this.speaker.startCommunication(this.login, this.password);
	     		this.hashObservable.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	    /* Reveillé par un notify : arrivée d'un SC */

	    /* Demander Ouverture d'une connexion : OC */

	    synchronized(this.hashObservable){
	       	try {
	       		this.speaker.openConnection(Api.uiName, Api.uiVersion, this.login);
	     		this.hashObservable.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/** set du IBrutalInterruptObserver*/
	public boolean setBrutalInterruptObserver(IBrutalInterruptObserver o, boolean createThread) {
		this.bio = o;
		return true;
	}

	/** set du IConnectionObserver*/
	public boolean setConnectionObserver(IConnectionObserver o, boolean createThread) {
		// TODO Voir si on laisse la hashmap ou les observers
		IConnectionObservable ico1 =  (IConnectionObservable)this.hashObservable.get("IConnection");
		ico1.addObserver(o);
		ico1.setCreateThread(createThread);

//		this.ico = o;
		return true;
	}

	/** set du IDialogObserver*/
	public boolean setDialogObserver(IDialogObserver o, boolean createThread) {
		this.ido = o;
		return true;
	}

	/** set du IFKCloseConnectionObserver*/
	public boolean setFKCloseConnectionObserver(IFKCloseConnectionObserver o, boolean createThread) {
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
	public boolean setServiceObserver(IServiceObserver o, boolean createThread) {
		this.iso = o;
		return true;
	}

	/** set du  IServiceStateObserver*/
	public boolean setServiceStateObserver(IServiceStateObserver o, boolean createThread) {
		this.isso = o;
		return true;
	}

	/** set du ISessionObserver */
	public boolean setSessionObserver(ISessionObserver o, boolean createThread) {
		this.iseo = o;
		return true;
	}

	/** set du ITraceMessageObserver  */
	public boolean setTraceMessageObserver(ITraceMessageObserver o, boolean createThread) {
		this.itmo = o;
		return true;
	}
	/** set du IWarningObserver*/

	public boolean setWarningObserver(IWarningObserver o, boolean createThread) {
		this.iwo = o;
		return true;
	}



}
