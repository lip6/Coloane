package fr.lip6.move.coloane.api.apiPackage;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;


import fr.lip6.move.coloane.api.FkCommunication.FkInitCom;
import fr.lip6.move.coloane.api.FkCommunication.Pair;
import fr.lip6.move.coloane.api.cami.ThreadParser;
import fr.lip6.move.coloane.api.interfaces.IListener;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;


import fr.lip6.move.coloane.api.interfaces.observables.IReceptResultObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ISessionObservable;




import fr.lip6.move.coloane.api.interfaces.observers.IReceptResultObserver;
import fr.lip6.move.coloane.api.interfaces.observers.ISessionObserver;

import fr.lip6.move.coloane.api.observables.ObservableFactory;
import fr.lip6.move.coloane.api.session.SessionFactory;
import fr.lip6.move.coloane.interfaces.api.connection.IApiConnection;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.observables.IBrutalInterruptObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IConnectionObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IDisconnectObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptDialogObservable;
import fr.lip6.move.coloane.interfaces.api.observables.ISpecialMessageObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IBrutalInterruptObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IConnectionObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IDisconnectObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;
import fr.lip6.move.coloane.interfaces.api.observers.ISpecialMessageObserver;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.api.interfaces.*;

/**
 * Définit une isntance de connexion à la plate-forme FrameKit
 */
public class ApiConnection implements IApiConnection {

	/** Etat de la connexion */
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

	private IConnectionInfo fkVersion;

	/** le sessionController*/
	private ISessionController sessionCont;
	
    private String uiName;
	
	private String uiVersion;


	/**
	 * Constructeur
	 * Initialise la connexion en créant :
	 *  - le thread listener.
	 *  - le speaker.
	 * On crée aussi une instance de sessionController ( qui gére les sessions).
	 * La connexion n'est pas ouverte ici, elle est faite sur l'appel
	 * de la méthode openConnection() après avoir configuré la connexion (méthodes setxxx())
	 * @throws IOException
	 */
	public ApiConnection(String name, String version) {
		this.hashObservable = new HashMap< String, Object>();
		this.hashObservable.put("IConnection", ObservableFactory.getNewConnectionObservable());
		this.hashObservable.put("ISession", ObservableFactory.getNewSessionObservable());
		this.hashObservable.put("IReceptResult", ObservableFactory.getNewReceptResultObservable());
		this.hashObservable.put("IBrutalInterrupt", ObservableFactory.getNewBrutalInterruptObservable());
		this.hashObservable.put("IReceptDialog", ObservableFactory.getNewReceptDialogObservable());
		this.hashObservable.put("IDisconnect", ObservableFactory.getNewCloseConnectionObservable());

		this.hashObservable.put("ISpecialMessage", ObservableFactory.getNewSpecialMessageObservable());
		//this.hashObservable.put("ICloseSession", ObservableFactory.getNewCloseSessionObservable());
		this.sessionCont = SessionFactory.getNewSessionController();
		this.fkVersion = null;
		this.uiName = name;
		this.uiVersion = version;
	}

	/** {@inheritDoc} */
	public final void setIpServer(String ip) {
		this.ipServer = ip;
	}

	/** {@inheritDoc} */
	public final void setLogin(String login) {
		this.login = login;
	}

	/** {@inheritDoc} */
	public final void setPassword(String pass) {
		this.password = pass;
	}

	/** {@inheritDoc} */
	public final void setPortServer(int port) {
		this.portServer = port;
	}

	/** {@inheritDoc} */
	public final IConnectionInfo openConnection() throws ApiException {
	
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
		       		this.speaker.openConnection(this.uiName, this.uiVersion, this.login);
		     		this.hashObservable.wait();
				} catch (InterruptedException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		    this.state = true; // connexion maintenant ouverte
		    return null;
		}


	/** set du IBrutalInterruptObserver*/
	public void setBrutalInterruptObserver(IBrutalInterruptObserver o, boolean createThread) {
		IBrutalInterruptObservable ico =  (IBrutalInterruptObservable)this.hashObservable.get("IBrutalInterrupt");
		ico.addObserver(o);
		ico.setCreateThread(createThread);

	
	}

	/** set du IConnectionObserver
	 * positionne l'observable aproprié par rapport à l'observeur dans notre hashmap,
	 */
	public void setConnectionObserver(IConnectionObserver o, boolean createThread) {
		// TODO Voir si on laisse la hashmap ou les observers

		IConnectionObservable ico1 =  (IConnectionObservable)this.hashObservable.get("IConnection");
		ico1.addObserver(o);
		ico1.setCreateThread(createThread);

		
	}


	/** set du IDialogObserver*/
	public void setReceptDialogObserver(IReceptDialogObserver o, boolean createThread) {

		IReceptDialogObservable idl =  (IReceptDialogObservable)this.hashObservable.get("IReceptDialog");
		idl.addObserver(o);
		idl.setCreateThread(createThread);

		
	}

	/** set du IFKCloseConnectionObserver*/
	public void setDisconnectObserver(IDisconnectObserver o, boolean createThread) {

		IDisconnectObservable idl =  (IDisconnectObservable)this.hashObservable.get("IDisconnect");
		idl.addObserver(o);
		idl.setCreateThread(createThread);

	
	}

	public void setSpecialMessageObserver(ISpecialMessageObserver o, boolean createThread) {

		ISpecialMessageObservable idl =  (ISpecialMessageObservable)this.hashObservable.get("ISpecialMessage");
		idl.addObserver(o);
		idl.setCreateThread(createThread);


	}





	/** set du ISessionObserver */
	public void setSessionObserver(ISessionObserver o, boolean createThread) {
		ISessionObservable ise =  (ISessionObservable)this.hashObservable.get("ISession");
		ise.addObserver(o);
		ise.setCreateThread(createThread);

	
	}


	
	/** set du IServiceObserver */
	public void setReceptResultObserver(IReceptResultObserver o, boolean createThread) {
		IReceptResultObservable ise =  (IReceptResultObservable)this.hashObservable.get("IReceptResult");
		ise.addObserver(o);
		ise.setCreateThread(createThread);
	
	}

	

	
	public boolean closeConnection() {
		try {
			speaker.closeConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	

	public IApiSession getApiSession() throws ApiException {
		return SessionFactory.getNewApiSession(this.sessionCont, this.speaker);
	}

	

	public void setReceptResultObserver(
			fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver o,
			boolean createThread) {
		// TODO Auto-generated method stub
		
	}

	

	
	

}
