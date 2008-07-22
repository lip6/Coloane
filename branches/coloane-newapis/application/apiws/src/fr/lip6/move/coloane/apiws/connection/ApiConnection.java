package fr.lip6.move.coloane.apiws.connection;

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
import fr.lip6.move.coloane.interfaces.api.observables.IBrutalInterruptObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IDisconnectObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptDialogObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptMenuObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptMessageObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptResultObservable;
import fr.lip6.move.coloane.interfaces.api.observers.IBrutalInterruptObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IDisconnectObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMenuObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.wrapper.ws.WrapperStub.Authentification;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Définit une isntance de connexion à la plate-forme FrameKit
 */
public class ApiConnection implements IApiConnection {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private boolean connectionOpened;
	private String ipServer;
	private String login;
	private String password;
	private int portServer;
	private ISessionController sessionController;
	private ISpeaker speaker;
	private IListener listener;
	private Map<Integer, Object> listObservables;

	/**
	 * Constructeur
	 */
	public ApiConnection() {
		this.connectionOpened = false;

		this.listObservables = new HashMap<Integer, Object>();
		this.listObservables.put(IObservables.RECEPT_DIALOG, ObservableFactory.getNewReceptDialogObservable());
		this.listObservables.put(IObservables.RECEPT_MENU, ObservableFactory.getNewReceptMenuObservable());
		this.listObservables.put(IObservables.RECEPT_MESSAGE, ObservableFactory.getNewReceptMessageObservable());
		this.listObservables.put(IObservables.RECEPT_RESULT, ObservableFactory.getNewReceptResultObservable());
		this.listObservables.put(IObservables.DISCONNECT, ObservableFactory.getNewDisconnectObservable());
		this.listObservables.put(IObservables.BRUTAL_INTERRUPT, ObservableFactory.getNewBrutalInterruptObservable());

		this.sessionController = SessionFactory.getNewSessionController(listObservables);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setIpServer(String ipServer) {
		this.ipServer = ipServer;
		LOGGER.finer("Initialisation de l'adresse IP du serveur");
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setLogin(String login) {
		this.login = login;
		LOGGER.finer("Initialisation du login de l'utilisateur");
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setPassword(String password) {
		this.password = password;
		LOGGER.finer("Initialisation du password de l'utilisateur");
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setPortServer(int portServer) {
		this.portServer = portServer;
		LOGGER.finer("Initialisation du port du serveur");
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptDialogObserver(IReceptDialogObserver o, boolean createThread) {
		IReceptDialogObservable obs = (IReceptDialogObservable) listObservables.get(IObservables.RECEPT_DIALOG);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		LOGGER.finer("Initialisation de l'observateur d'événement: réception d'une boîte de dialogue");
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptMenuObserver(IReceptMenuObserver o, boolean createThread) {
		IReceptMenuObservable obs = (IReceptMenuObservable) listObservables.get(IObservables.RECEPT_MENU);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		LOGGER.finer("Initialisation de l'observateur d'événement: réception d'un menu");
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptMessageObserver(IReceptMessageObserver o, boolean createThread) {
		IReceptMessageObservable obs = (IReceptMessageObservable) listObservables.get(IObservables.RECEPT_MESSAGE);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		LOGGER.finer("Initialisation de l'observateur d'événement: réception d'un message");
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptResultObserver(IReceptResultObserver o, boolean createThread) {
		IReceptResultObservable obs = (IReceptResultObservable) listObservables.get(IObservables.RECEPT_RESULT);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		LOGGER.finer("Initialisation de l'observateur d'événement: réception d'un résultat");
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setDisconnectObserver(IDisconnectObserver o, boolean createThread) {
		IDisconnectObservable obs = (IDisconnectObservable) listObservables.get(IObservables.DISCONNECT);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		LOGGER.finer("Initialisation de l'observateur d'événement: déconnexion ordonnée");
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setBrutalInterruptObserver(IBrutalInterruptObserver o, boolean createThread) {
		IBrutalInterruptObservable obs = (IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT);
		obs.addObserver(o);
		obs.setCreateThread(createThread);
		LOGGER.finer("Initialisation de l'observateur d'événement: réception d'une erreur");
	}

	/**
	 * {@inheritDoc}
	 */
	public final IConnectionInfo openConnection() throws ApiException {
		if (connectionOpened) {
			LOGGER.warning("Impossible d'ouvrir une connexion: une connexion est deja ouverte");
			throw new ApiException("Une connexion est deja ouverte");
		}

		LOGGER.finer("Demande la création du Speaker");
		this.speaker = new Speaker();
		Authentification auth = speaker.openConnection(login, password);

		LOGGER.finer("Demande la création du Listener");
		this.listener = new Listener(speaker.getAuthentification(), speaker.getStub(), listObservables);
		listener.start();

		connectionOpened = true;

		LOGGER.fine("Ouverture d'une connexion");
		return new ConnectionInfo(auth);
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean closeConnection() throws ApiException {
		if (!connectionOpened) {
			LOGGER.warning("Impossible de fermer la connexion: aucune connexion n'est ouverte");
			throw new ApiException("Aucune connexion n'est ouverte");
		}

		LOGGER.finer("Demande l'arrêt du Listener");
		listener.stopper();

		LOGGER.finer("Demande la fermeture de la connexion");
		speaker.closeConnection();

		connectionOpened = false;

		LOGGER.fine("Fermeture de la connexion");
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final IApiSession getApiSession() throws ApiException {
		if (!connectionOpened) {
			LOGGER.warning("Impossible de créer une session: aucune connexion n'est ouverte");
			throw new ApiException("Aucune connexion n'est ouverte");
		}
		
		LOGGER.fine("Creation d'une session");
		return SessionFactory.getNewApiSession(sessionController, speaker);
	}
}
