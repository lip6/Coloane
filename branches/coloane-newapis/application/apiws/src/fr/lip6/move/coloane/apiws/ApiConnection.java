package fr.lip6.move.coloane.apiws;

import fr.lip6.move.coloane.apiws.interfaces.observables.IBrutalInterruptObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IDisconnectObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IMyReceptErrorObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IObservables;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptDialogObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptMenuObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptMessageObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptResultObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptServiceStateObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IRequestNewGraphObservable;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.IListener;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;
import fr.lip6.move.coloane.apiws.objects.api.ConnectionInfo;
import fr.lip6.move.coloane.apiws.observables.MyReceptErrorObservable;
import fr.lip6.move.coloane.apiws.observables.ObservableFactory;
import fr.lip6.move.coloane.apiws.observer.MyReceptErrorObserver;
import fr.lip6.move.coloane.apiws.session.SessionFactory;
import fr.lip6.move.coloane.apiws.wrapperCommunication.Listener;
import fr.lip6.move.coloane.apiws.wrapperCommunication.Speaker;
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
import fr.lip6.move.coloane.interfaces.api.observers.IRequestNewGraphObserver;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.wrapper.ws.WrapperStub.Authentification;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Définit une instance de connexion à la plate-forme FrameKit
 */
public class ApiConnection implements IApiConnection {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private boolean connectionOpened;

	/**
	 * TODO : Ajouter plus tard un setteur sur le chemin du serveur.
	 */
	private String cheminServer = "/axis2/services/Wrapper";


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
		this.listObservables.put(IObservables.RECEPT_SERVICE_STATE, ObservableFactory.getNewReceptServiceStateObservable());
		this.listObservables.put(IObservables.REQUEST_NEW_GRAPH, ObservableFactory.getNewRequestNewGraphObservable());

		IMyReceptErrorObservable obs = new MyReceptErrorObservable();
		obs.addObserver(new MyReceptErrorObserver(this));
		obs.setCreateThread(false);
		this.listObservables.put(IObservables.RECEPT_ERROR, obs);


		LOGGER.finer("Création d'une IApiConnection");
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptDialogObserver(IReceptDialogObserver o, boolean createThread) {
		LOGGER.finer("Initialisation de l'observateur d'événement: réception d'une boîte de dialogue");
		IReceptDialogObservable obs = (IReceptDialogObservable) listObservables.get(IObservables.RECEPT_DIALOG);
		obs.addObserver(o);
		obs.setCreateThread(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptMenuObserver(IReceptMenuObserver o, boolean createThread) {
		LOGGER.finer("Initialisation de l'observateur d'événement: réception d'un menu");
		IReceptMenuObservable obs = (IReceptMenuObservable) listObservables.get(IObservables.RECEPT_MENU);
		obs.addObserver(o);
		obs.setCreateThread(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptMessageObserver(IReceptMessageObserver o, boolean createThread) {
		LOGGER.finer("Initialisation de l'observateur d'événement: réception d'un message");
		IReceptMessageObservable obs = (IReceptMessageObservable) listObservables.get(IObservables.RECEPT_MESSAGE);
		obs.addObserver(o);
		obs.setCreateThread(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setReceptResultObserver(IReceptResultObserver o, boolean createThread) {
		LOGGER.finer("Initialisation de l'observateur d'événement: réception d'un résultat");
		IReceptResultObservable obs = (IReceptResultObservable) listObservables.get(IObservables.RECEPT_RESULT);
		obs.addObserver(o);
		obs.setCreateThread(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setDisconnectObserver(IDisconnectObserver o, boolean createThread) {
		LOGGER.finer("Initialisation de l'observateur d'événement: déconnexion ordonnée");
		IDisconnectObservable obs = (IDisconnectObservable) listObservables.get(IObservables.DISCONNECT);
		obs.addObserver(o);
		obs.setCreateThread(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setBrutalInterruptObserver(IBrutalInterruptObserver o, boolean createThread) {
		LOGGER.finer("Initialisation de l'observateur d'événement: réception d'une erreur");
		IBrutalInterruptObservable obs = (IBrutalInterruptObservable) listObservables.get(IObservables.BRUTAL_INTERRUPT);
		obs.addObserver(o);
		obs.setCreateThread(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addReceptServiceStateObserver(IReceptServiceStateObserver o, boolean createThread) {
		LOGGER.finer("Ajout d'un observateur d'événement: récéption d'une information le service en cours d'exécution");
		IReceptServiceStateObservable obs = (IReceptServiceStateObservable) listObservables.get(IObservables.RECEPT_SERVICE_STATE);
		obs.addObserver(o);
		obs.setCreateThread(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void removeReceptServiceStateObserver(IReceptServiceStateObserver o) {
		LOGGER.finer("Supression de l'observateur d'événement: récéption d'une information le service en cours d'exécution");
		IReceptServiceStateObservable obs = (IReceptServiceStateObservable) listObservables.get(IObservables.RECEPT_SERVICE_STATE);
		obs.removeObserver(o);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setRequestNewGraphObserver(IRequestNewGraphObserver o, boolean createThread) {
		LOGGER.fine("Initialisation de l'observateur d'événement: demande un nouveau graph");
		IRequestNewGraphObservable obs = (IRequestNewGraphObservable) listObservables.get(IObservables.REQUEST_NEW_GRAPH);
		obs.addObserver(o);
		obs.setCreateThread(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public final IConnectionInfo openConnection(String login, String pass, String ip, int port) throws ApiException {

		LOGGER.finer("Demande l'ouverture d'une connexion");

		if (connectionOpened) {
			LOGGER.warning("Impossible d'ouvrir une connexion: une connexion est deja ouverte");
			throw new ApiException("Une connexion est deja ouverte");
		}

		LOGGER.finer("Demande la création du Speaker");
		this.speaker = new Speaker(ip, port, cheminServer, listObservables, this);
		Authentification auth = speaker.openConnection(login, pass);

		LOGGER.finer("Demande la création du Listener");
		this.listener = new Listener(speaker.getAuthentification(), speaker.getStub(), listObservables);

		LOGGER.finer("Création du gestionnaire de session");
		this.sessionController = SessionFactory.getNewSessionController(listObservables);
		this.sessionController.setConnectionOpened(true);

		LOGGER.finer("Demande le demmarage du Listener");
		listener.start();

		connectionOpened = true;

		LOGGER.fine("Ouverture d'une connexion");

		return new ConnectionInfo(auth);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void closeConnection(boolean softMode) {

		LOGGER.finer("Demande la fermeture de la connexion");

		if (!connectionOpened) {
			LOGGER.warning("Impossible de fermer la connexion: aucune connexion n'est ouverte");
			return;
		}

		try {
			if (softMode) {
				LOGGER.finer("Mode softMode: fermeture de toutes les sessions avant la fermeture de la connexion");
				sessionController.closeAllSessions();
			}

			LOGGER.finer("Demande l'arrêt du Listener");
			listener.stopper();

			// Attend l'arrêt du listener
			((Thread) listener).join();

			LOGGER.finer("Demande l'arrêt de la communication");
			speaker.closeConnection();

		} catch (ApiException e) {
			LOGGER.warning("Erreur lors de la fermeture de la connexion: " + e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			LOGGER.warning("Erreur lors de la fermeture de la connexion: " + e.getMessage());
			e.printStackTrace();
		} finally {
			listener = null;
			speaker = null;
			sessionController.setConnectionOpened(false);
			connectionOpened = false;
			LOGGER.fine("Fermeture de la connexion");
		}
	}

	/**
	 * Force la fermeture de la connexion aprés la récéption d'une erreur grave
	 */
	public final void closeConnectionError() {
		LOGGER.fine("Fermeture forcé de la connexion après la récéption d'une erreur grave");

		listener.stopper();
		// Attend l'arrêt du listener
		try {
			((Thread) listener).join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		listener = null;
		speaker = null;
		sessionController.setConnectionOpened(false);
		connectionOpened = false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final IApiSession createApiSession() throws ApiException {
		if (!connectionOpened) {
			LOGGER.warning("Impossible de créer une session: aucune connexion n'est ouverte");
			throw new ApiException("Aucune connexion n'est ouverte");
		}

		LOGGER.finer("Demande la creation d'une IApiSession");
		return SessionFactory.getNewApiSession(sessionController, speaker, (IRequestNewGraphObservable) listObservables.get(IObservables.REQUEST_NEW_GRAPH));
	}
}
