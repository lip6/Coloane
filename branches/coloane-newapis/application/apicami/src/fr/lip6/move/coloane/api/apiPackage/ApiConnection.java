package fr.lip6.move.coloane.api.apiPackage;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

import teststub.CloseSessionObserver;
import fr.lip6.move.coloane.api.FkCommunication.FkInitCom;
import fr.lip6.move.coloane.api.FkCommunication.Pair;
import fr.lip6.move.coloane.api.cami.ThreadParser;
import fr.lip6.move.coloane.api.interfaces.IApiSession;
import fr.lip6.move.coloane.api.interfaces.IConnectionVersion;
import fr.lip6.move.coloane.api.interfaces.IListener;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;
import fr.lip6.move.coloane.api.interfaces.observables.IBrutalInterruptObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ICloseConnectionObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ICloseSessionObservable;
import fr.lip6.move.coloane.api.interfaces.observables.IConnectionObservable;
import fr.lip6.move.coloane.api.interfaces.observables.IReceptDialogObservable;
import fr.lip6.move.coloane.api.interfaces.observables.IReceptResultObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ISessionObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ISpecialMessageObservable;
import fr.lip6.move.coloane.api.interfaces.observers.IBrutalInterruptObserver;
import fr.lip6.move.coloane.api.interfaces.observers.ICloseConnectionObserver;
import fr.lip6.move.coloane.api.interfaces.observers.ICloseSessionObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IConnectionObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.api.interfaces.observers.IReceptResultObserver;
import fr.lip6.move.coloane.api.interfaces.observers.ISessionObserver;
import fr.lip6.move.coloane.api.interfaces.observers.ISpecialMessageObserver;
import fr.lip6.move.coloane.api.observables.ObservableFactory;
import fr.lip6.move.coloane.api.session.SessionFactory;
import fr.lip6.move.coloane.interfaces.api.connection.IApiConnection;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.observers.IDisconnectObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;

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

	/** une table de hash qui stocke les observables */
	private HashMap< String, Object> hashObservable;

   private IConnectionVersion fkVersion;

    /** le sessionController*/
	private ISessionController sessionCont;

	/** Constructeur
	 * Initialise la connexion en créant :
	 *  - le thread listener.
	 *  - le speaker.
	 *  on crée aussi une instance de sessionController ( qui gére les sessions).
	 * La connexion n'est pas ouverte ici, elle est faite sur l'appel
	 * de la méthode openConnection() après avoir configuré la connexion (méthodes setxxx())
	 * @throws IOException
	 */
	public ApiConnection() {

		// TODO

		this.hashObservable = new HashMap< String, Object>();
		this.hashObservable.put("IConnection", ObservableFactory.getNewConnectionObservable());
		this.hashObservable.put("ISession", ObservableFactory.getNewSessionObservable());
		this.hashObservable.put("IReceptResult", ObservableFactory.getNewReceptResultObservable());
		this.hashObservable.put("IBrutalInterrupt", ObservableFactory.getNewBrutalInterruptObservable());
		this.hashObservable.put("IReceptDialog", ObservableFactory.getNewReceptDialogObservable());
		this.hashObservable.put("ICloseConnection", ObservableFactory.getNewCloseConnectionObservable());
		
		this.hashObservable.put("ISpecialMessage", ObservableFactory.getNewSpecialMessageObservable());
		this.hashObservable.put("ICloseSession", ObservableFactory.getNewCloseSessionObservable());
		this.sessionCont = SessionFactory.getNewSessionController();
		this.fkVersion= null;
	}

	public boolean closeConnection() {
		speaker.closeConnection();
		return true;
	}

	/**
	 * retourne une IApiSession
	 */
	public IApiSession getAPISession() {
		return SessionFactory.getNewApiSession(this.sessionCont,this.speaker);
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
		ThreadParser parser = new ThreadParser(this.sessionCont,fifo, this.hashObservable);

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
				e.printStackTrace();
			} catch (IOException e) {
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

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	    this.state = true; // connexion maintenant ouverte
	}

	/** set du IBrutalInterruptObserver*/
	public boolean setBrutalInterruptObserver(IBrutalInterruptObserver o, boolean createThread) {
		IBrutalInterruptObservable ico =  (IBrutalInterruptObservable)this.hashObservable.get("IBrutalInterrupt");
		ico.addObserver(o);
		ico.setCreateThread(createThread);

		return true;
	}

	/** set du IConnectionObserver
	 * positionne l'observable aproprié par rapport à l'observeur dans notre hashmap,
	 */
	public boolean setConnectionObserver(IConnectionObserver o, boolean createThread) {
		// TODO Voir si on laisse la hashmap ou les observers

		IConnectionObservable ico1 =  (IConnectionObservable)this.hashObservable.get("IConnection");
		ico1.addObserver(o);
		ico1.setCreateThread(createThread);

		return true;
	}

	
	/** set du IDialogObserver*/
	public boolean setReceptDialogObserver(IReceptDialogObserver o, boolean createThread) {

		 IReceptDialogObservable idl =  (IReceptDialogObservable)this.hashObservable.get("IReceptDialog");
		idl.addObserver(o);
		idl.setCreateThread(createThread);

		return true;
	}

	/** set du IFKCloseConnectionObserver*/
	public boolean setCloseConnectionObserver(ICloseConnectionObserver o, boolean createThread) {

		ICloseConnectionObservable idl =  (ICloseConnectionObservable)this.hashObservable.get("ICloseConnection");
		idl.addObserver(o);
		idl.setCreateThread(createThread);

		return true;
	}

	public boolean setSpecialMessageObserver(ISpecialMessageObserver o, boolean createThread) {

		ISpecialMessageObservable idl =  (ISpecialMessageObservable)this.hashObservable.get("ISpecialMessage");
		idl.addObserver(o);
		idl.setCreateThread(createThread);

		return true;
	}




	
	/** set du ISessionObserver */
	public boolean setSessionObserver(ISessionObserver o, boolean createThread) {
		ISessionObservable ise =  (ISessionObservable)this.hashObservable.get("ISession");
		ise.addObserver(o);
		ise.setCreateThread(createThread);

		return true;
	}

	
	public boolean setCloseSessionObserver(
			ICloseSessionObserver o, boolean b) {
		ICloseSessionObservable iss =  (ICloseSessionObservable)this.hashObservable.get("ICloseSession");
		iss.addObserver(o);
		iss.setCreateThread(b);

		return true;
	}

	

	
	/** set du IServiceObserver */
	public boolean setReceptResultObserver(IReceptResultObserver o, boolean createThread) {
		IReceptResultObservable ise =  (IReceptResultObservable)this.hashObservable.get("IReceptResult");
		ise.addObserver(o);
		ise.setCreateThread(createThread);
		return true;
	}

	public boolean setCloseSessionObserver(
			CloseSessionObserver closeSessionObserver, boolean b) {
		// TODO Auto-generated method stub
		return false;
	}

	public fr.lip6.move.coloane.interfaces.api.session.IApiSession getApiSession()
			throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setBrutalInterruptObserver(
			fr.lip6.move.coloane.interfaces.api.observers.IBrutalInterruptObserver o,
			boolean createThread) {
		// TODO Auto-generated method stub
		
	}

	public void setDisconnectObserver(IDisconnectObserver o,
			boolean createThread) {
		// TODO Auto-generated method stub
		
	}

	public void setIpServer(String ipServer) {
		// TODO Auto-generated method stub
		
	}

	public void setPassword(String password) {
		// TODO Auto-generated method stub
		
	}

	public void setPortServer(int portServer) {
		// TODO Auto-generated method stub
		
	}

	public void setReceptDialogObserver(
			fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver o,
			boolean createThread) {
		// TODO Auto-generated method stub
		
	}

	public void setReceptMenuObserver(IReceptMenuObserver o,
			boolean createThread) {
		// TODO Auto-generated method stub
		
	}

	public void setReceptMessageObserver(IReceptMessageObserver o,
			boolean createThread) {
		// TODO Auto-generated method stub
		
	}

	public void setReceptResultObserver(
			fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver o,
			boolean createThread) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
