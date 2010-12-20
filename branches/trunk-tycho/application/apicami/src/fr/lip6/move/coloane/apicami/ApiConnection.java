package fr.lip6.move.coloane.apicami;

import fr.lip6.move.coloane.apicami.communications.ComObjects;
import fr.lip6.move.coloane.apicami.observables.BrutalInterruptObservable;
import fr.lip6.move.coloane.apicami.observables.ConnectionObservable;
import fr.lip6.move.coloane.apicami.observables.DisconnectObservable;
import fr.lip6.move.coloane.apicami.observables.ObservableFactory;
import fr.lip6.move.coloane.apicami.observables.ReceptDialogObservable;
import fr.lip6.move.coloane.apicami.observables.ReceptMenuObservable;
import fr.lip6.move.coloane.apicami.observables.ReceptMessageObservable;
import fr.lip6.move.coloane.apicami.observables.ReceptResultObservable;
import fr.lip6.move.coloane.apicami.observables.ReceptServiceStateObservable;
import fr.lip6.move.coloane.apicami.session.SessionController;
import fr.lip6.move.coloane.apicami.session.SessionFactory;
import fr.lip6.move.coloane.interfaces.api.IApiConnection;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.observers.IBrutalInterruptObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IDisconnectObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptServiceStateObserver;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Définit une instance de connexion à la plate-forme FrameKit
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 */
public class ApiConnection implements IApiConnection {
	/** Le logger */
	private static Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

	/** Speaker */
	private ComObjects comObjects;

	/** Une table de hash qui stocke tous les observeurs */
	private Map<String, Object> hashObservable;

	/** Le nom du client */
	private String uiName;

	/** La version du client */
	private String uiVersion;

	/** Les informations de connexion */
	private IConnectionInfo connectionInfo;

	/**
	 * Constructeur<br>
	 * Initialise la connexion en créant :
	 *  - le thread listener.
	 *  - le speaker.
	 * On crée aussi une instance de sessionController (qui gére les sessions).<br>
	 * La connexion n'est pas ouverte ici, elle est faite sur l'appel
	 * de la méthode openConnection() après avoir configuré la connexion (méthodes setxxx())
	 *
	 * @param uiName Le nom de l'API
	 * @param uiVersion La version de l'API
	 */
	public ApiConnection(String uiName, String uiVersion) {
		this.hashObservable = new HashMap< String, Object>();
		this.uiName = uiName;
		this.uiVersion = uiVersion;

		ConnectionObservable connectionObservable = ObservableFactory.getNewOpenConnectionObservable(this.hashObservable);
		connectionObservable.registerApiConnection(this);

		this.hashObservable.put("IConnection", connectionObservable);
		this.hashObservable.put("ISession", ObservableFactory.getNewSessionObservable());
		this.hashObservable.put("IReceptResult", ObservableFactory.getNewReceptResultObservable());
		this.hashObservable.put("IBrutalInterrupt", ObservableFactory.getNewBrutalInterruptObservable());
		this.hashObservable.put("IReceptDialog", ObservableFactory.getNewReceptDialogObservable());
		this.hashObservable.put("IDisconnect", ObservableFactory.getNewCloseConnectionObservable());
		this.hashObservable.put("IReceptMessage", ObservableFactory.getNewSpecialMessageObservable());
		//this.hashObservable.put("ICloseSession", ObservableFactory.getNewCloseSessionObservable());
		this.hashObservable.put("IReceptServiceState", ObservableFactory.getNewReceptServiceStateObservable());
	}

	/**
	 * @return la liste des observers
	 */
	public final Map< String, Object> getObservablesList() {
		return this.hashObservable;
	}

	/** {@inheritDoc} */
	public final IConnectionInfo openConnection(String login, String password, String ip, int port) throws ApiException {

		// Création des objets communicants
		try {
			this.comObjects = new ComObjects(ip, port);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ApiException("Cannot initialize a connection with the platform... ");
		}
		LOGGER.fine("Initialisation OK");

		// Lancer le parseur (listener)
		this.comObjects.getListener().setObservers(this.hashObservable);
		new Thread(this.comObjects.getListener()).start();

		LOGGER.fine("Parser ANTLR en cours d'execution");

		// Demander l'ouverture de la communication Commande SC / OC et attendre les acquittements de FK
		LOGGER.fine("Demande d'ouverture de connexion");
		synchronized (this.hashObservable) {
			try {
				this.comObjects.getSpeaker().startCommunication(login, password);
				this.comObjects.getSpeaker().openConnection(uiName, uiVersion, login);
				this.hashObservable.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw new ApiException("Error while connecting to the platform... Connection has been interrupted");
			} catch (IOException e) {
				e.printStackTrace();
				throw new ApiException("Error while connecting to the platform... The connection has been broken");
			}
		}

		if (this.connectionInfo == null) {
			throw new ApiException("Authentication failure...");
		}
		return this.connectionInfo;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void closeConnection(boolean softMode) {
		// Si le softMode est active... Les sessions doivent être déconnecté en premier
		if (softMode) {
			try {
				LOGGER.fine("Demande de deconnexion de tous les modeles");
				SessionController.getInstance().closeAllSessions();
			} catch (ApiException apie) {
				LOGGER.warning("Au moins une session ne s'est pas fini correctement... Déconnexion brutale !");
			}
		}

		try {
			this.comObjects.getSpeaker().closeConnection();
		} catch (IOException e) {
			LOGGER.warning("Echec lors de la fermeture de la connexion");
		}

 	 	//TODO: Détruire le thread listener proprement
		this.comObjects.getListener().stop();
	}

	/**
	 * {@inheritDoc}
	 */
	public final IApiSession createApiSession() throws ApiException {
		return SessionFactory.getNewApiSession(this, this.comObjects.getSpeaker());
	}

	/**
	 * Indique la fin de l'ouverture de connexion
	 * @param infos Les informations de connexion
	 */
	public final void notifyEndOpenConnection(IConnectionInfo infos) {
		this.connectionInfo = infos;
		synchronized (hashObservable) {
			this.hashObservable.notify();
		}
	}

	/**
	 * Indique une déconnexion brutale
	 */
	public final void notifyBrutalDisconnection() {
		this.closeConnection(false);
		synchronized (hashObservable) {
			this.hashObservable.notify();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setBrutalInterruptObserver(IBrutalInterruptObserver o, boolean createThread) {
		LOGGER.fine("Enregistrement d'un observer sur la déconnexion brutale de la plate-forme");
		BrutalInterruptObservable observable = (BrutalInterruptObservable) this.hashObservable.get("IBrutalInterrupt");
		observable.addObserver(o, this);
		observable.setCreateThread(createThread);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptDialogObserver(IReceptDialogObserver o, boolean createThread) {
		LOGGER.fine("Enregistrement d'un observer sur la reception des boites de dialogues");
		ReceptDialogObservable observable = (ReceptDialogObservable) this.hashObservable.get("IReceptDialog");
		observable.addObserver(o);
		observable.setCreateThread(createThread);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setDisconnectObserver(IDisconnectObserver o, boolean createThread) {
		LOGGER.fine("Enregistrement d'un observer sur la déconnexion de la plate-forme");
		DisconnectObservable observable = (DisconnectObservable) this.hashObservable.get("IDisconnect");
		observable.addObserver(o);
		observable.setCreateThread(createThread);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptResultObserver(IReceptResultObserver o, boolean createThread) {
		LOGGER.fine("Enregistrement d'un observer sur la reception des resultats");
		ReceptResultObservable observable = (ReceptResultObservable) this.hashObservable.get("IReceptResult");
		observable.addObserver(o);
		observable.setCreateThread(createThread);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptMenuObserver(IReceptMenuObserver o, boolean createThread) {
		LOGGER.fine("Enregistrement d'un observer sur la reception de menus de la plate-forme");
		ReceptMenuObservable observable = (ReceptMenuObservable) this.hashObservable.get("ISession");
		observable.addObserver(o);
		observable.setCreateThread(createThread);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptMessageObserver(IReceptMessageObserver o, boolean createThread) {
		LOGGER.fine("Enregistrement d'un observer sur la reception de message de la plate-forme");
		ReceptMessageObservable observable = (ReceptMessageObservable) this.hashObservable.get("IReceptMessage");
		observable.addObserver(o);
		observable.setCreateThread(createThread);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptServiceStateObserver(IReceptServiceStateObserver o, boolean createThread) {
		LOGGER.fine("Enregistrement d'un observer sur la reception de message d'etat de la plate-forme");
		ReceptServiceStateObservable observable = (ReceptServiceStateObservable) this.hashObservable.get("IReceptServiceState");
		observable.setObserver(o);
		observable.setCreateThread(createThread);
	}
}
