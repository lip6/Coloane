package fr.lip6.move.coloane.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import fr.lip6.move.coloane.api.FkCommunication.FkInitCom;
import fr.lip6.move.coloane.api.FkCommunication.Pair;
import fr.lip6.move.coloane.api.cami.ThreadParser;
import fr.lip6.move.coloane.api.interfaces.IListener;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;
import fr.lip6.move.coloane.api.session.SessionFactory;
import fr.lip6.move.coloane.interfaces.api.connection.IApiConnection;
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

/**
 * Définit une isntance de connexion à la plate-forme FrameKit
 */
public class ApiConnection implements IApiConnection {
	/** Le logger */
	private static Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

	/** Speaker */
	private ISpeaker speaker;

	/** Une table de hash qui stocke tous les observeurs */
	private Map< String, Object> hashObservable;
	
	/** Le nom du client */
	private String uiName;
	
	/** La version du client */
	private String uiVersion;

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
		
		//this.hashObservable.put("ISession", ObservableFactory.getNewSessionObservable());
		//this.hashObservable.put("IReceptResult", ObservableFactory.getNewReceptResultObservable());
		//this.hashObservable.put("IBrutalInterrupt", ObservableFactory.getNewBrutalInterruptObservable());
		//this.hashObservable.put("IReceptDialog", ObservableFactory.getNewReceptDialogObservable());
		//this.hashObservable.put("IDisconnect", ObservableFactory.getNewCloseConnectionObservable());
		//this.hashObservable.put("ISpecialMessage", ObservableFactory.getNewSpecialMessageObservable());
		//this.hashObservable.put("ICloseSession", ObservableFactory.getNewCloseSessionObservable());

	}

	/** {@inheritDoc} */
	public final IConnectionInfo openConnection(String login, String password, String ip, int port) throws ApiException {
		Pair<ISpeaker, IListener> p;

		// Créer la file Queue entre le parser et le thread Listener
		LinkedBlockingQueue<InputStream> fifo = new LinkedBlockingQueue<InputStream>();

		// Création du parseur
		ThreadParser parser = new ThreadParser(fifo, this.hashObservable);

		// Initialisation de la connexion
		try {
			// Création du thread listener et le speaker
			p = FkInitCom.initCom(ip, port, fifo);
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
				this.speaker.startCommunication(login, password);
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
				this.speaker.openConnection(uiName, uiVersion, login);
				this.hashObservable.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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
		return SessionFactory.getNewApiSession(this.speaker);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setBrutalInterruptObserver(IBrutalInterruptObserver o, boolean createThread) {
		// TODO ???
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptDialogObserver(IReceptDialogObserver o, boolean createThread) {
		// TODO ???
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setDisconnectObserver(IDisconnectObserver o, boolean createThread) {
		// TODO ???
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptResultObserver(IReceptResultObserver o, boolean createThread) {
		// TODO ???
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
	 * {@inheritDoc}
	 */
	public void setReceptServiceStateObserver(IReceptServiceStateObserver o, boolean createThread) {
		// TODO ???
	}
}
