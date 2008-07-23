package fr.lip6.move.coloane.api.apiPackage;

import fr.lip6.move.coloane.api.FkCommunication.FkInitCom;
import fr.lip6.move.coloane.api.FkCommunication.Pair;
import fr.lip6.move.coloane.api.cami.ThreadParser;
import fr.lip6.move.coloane.api.interfaces.IListener;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;
import fr.lip6.move.coloane.api.interfaces.observables.ISessionObservable;
import fr.lip6.move.coloane.api.interfaces.observers.ISessionObserver;
import fr.lip6.move.coloane.api.observables.ObservableFactory;
import fr.lip6.move.coloane.api.session.SessionFactory;
import fr.lip6.move.coloane.interfaces.api.connection.IApiConnection;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.observables.IBrutalInterruptObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IDisconnectObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptDialogObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptMessageObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptResultObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IBrutalInterruptObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IDisconnectObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

/**
 * Définit une isntance de connexion à la plate-forme FrameKit
 */
public class ApiConnection implements IApiConnection {
	/** Le logger */
	private static Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

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
	private Map< String, Object> hashObservable;

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
	 * On crée aussi une instance de sessionController (qui gére les sessions).
	 * La connexion n'est pas ouverte ici, elle est faite sur l'appel
	 * de la méthode openConnection() après avoir configuré la connexion (méthodes setxxx())
	 *
	 * @param name Le nom de l'API
	 * @param version La version de l'API
	 */
	public ApiConnection(String name, String version) {
		this.hashObservable = new HashMap< String, Object>();
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
		// TODO Vérifier que la connexion est configurée

		Pair<ISpeaker, IListener> p;
		this.state = false; // Connexion initialement non ouverte

		// Créer la file Queue entre le parser et le thread Listener
		LinkedBlockingQueue<InputStream> fifo = new LinkedBlockingQueue<InputStream>();

		// Création du parseur
		ThreadParser parser = new ThreadParser(this.sessionCont, fifo, this.hashObservable);

		// Initialisation de la connexion
		try {
			// Création du thread listener et le speaker
			p = FkInitCom.initCom(this.ipServer, this.portServer, fifo);
			this.listener = p.getListener();
			this.speaker = p.getSpeaker();

		} catch (IOException e) {
			LOGGER.warning("Echec lors de la connexion a la plate-forme");
			e.printStackTrace();
			return null;
		}

		LOGGER.fine("Initialisation OK");

		// Lancer le parser
		parser.start();
		LOGGER.fine("Parser ANTLR en cours d'execution");

		// Demander l'ouverture de la communication Commande SC et attendre l'aquittement de FK
		LOGGER.fine("Demande d'ouverture de connexion");
		synchronized (this.hashObservable) {
			try {
				this.speaker.startCommunication(this.login, this.password);
				this.hashObservable.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Reveillé par un notify : arrivée d'un SC
		LOGGER.finer("Réveil OK par arrivée de SC");

		// Demander Ouverture d'une connexion : OC
		synchronized (this.hashObservable) {
			try {
				this.speaker.openConnection(this.uiName, this.uiVersion, this.login);
				this.hashObservable.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		this.state = true; // La connexion est désormais ouverte
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean closeConnection() {
		try {
			speaker.closeConnection();
		} catch (IOException e) {
			LOGGER.warning("Echec lors de la fermeture de la connexion");
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final IApiSession createApiSession() throws ApiException {
		return SessionFactory.getNewApiSession(this.sessionCont, this.speaker);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setBrutalInterruptObserver(IBrutalInterruptObserver o, boolean createThread) {
		IBrutalInterruptObservable ico =  (IBrutalInterruptObservable) this.hashObservable.get("IBrutalInterrupt");
		ico.addObserver(o);
		ico.setCreateThread(createThread);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptDialogObserver(IReceptDialogObserver o, boolean createThread) {
		IReceptDialogObservable idl =  (IReceptDialogObservable) this.hashObservable.get("IReceptDialog");
		idl.addObserver(o);
		idl.setCreateThread(createThread);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setDisconnectObserver(IDisconnectObserver o, boolean createThread) {
		IDisconnectObservable idl =  (IDisconnectObservable) this.hashObservable.get("IDisconnect");
		idl.addObserver(o);
		idl.setCreateThread(createThread);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptResultObserver(IReceptResultObserver o, boolean createThread) {
		IReceptResultObservable ise =  (IReceptResultObservable) this.hashObservable.get("IReceptResult");
		ise.addObserver(o);
		ise.setCreateThread(createThread);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptMenuObserver(IReceptMenuObserver o, boolean createThread) {
		// TODO ???
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptMessageObserver(IReceptMessageObserver o, boolean createThread) {
		// TODO ???
	}

	/**
	 * TODO Cette méthode est-elle utile ?
	 * @param o
	 * @param createThread
	 */
	public final void setSpecialMessageObserver(IReceptMessageObserver o, boolean createThread) {
		IReceptMessageObservable idl =  (IReceptMessageObservable) this.hashObservable.get("ISpecialMessage");
		idl.addObserver(o);
		idl.setCreateThread(createThread);
	}

	/**
	 * TODO Cette méthode est-elle utile ?
	 * @param o
	 * @param createThread
	 */
	public final void setSessionObserver(ISessionObserver o, boolean createThread) {
		ISessionObservable ise =  (ISessionObservable) this.hashObservable.get("ISession");
		ise.addObserver(o);
		ise.setCreateThread(createThread);
	}
}
