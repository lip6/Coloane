package fr.lip6.move.coloane.apiws.session;

import fr.lip6.move.coloane.apiws.ApiConnection;
import fr.lip6.move.coloane.apiws.evenements.ReceptMenu;
import fr.lip6.move.coloane.apiws.interfaces.observables.IObservables;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptMenuObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptResultObservable;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;
import fr.lip6.move.coloane.apiws.objects.result.ResultImpl;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.MMenu;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.RService;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.service.IService;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

/**
 * Cette classe représent le gestionnaire de sessions.
 *
 * @author Monir CHAOUKI
 */
public class SessionController implements ISessionController {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	/** Représent la session active */
	private IApiSession activeSession;

	/** Représent l'ensemle des sessions */
	private Map<String, IApiSession> listSessions;

	/** Représent la liste des observables */
	private Map<Integer, Object> listObservables;

	/** Représent la connexion */
	private ApiConnection connection;

	/**
	 * Constructeur
	 * @param listObservables la liste des observables à notifier
	 * @param connection la connexion
	 */
	public SessionController(Map<Integer, Object> listObservables, ApiConnection connection) {
		this.activeSession = null;
		this.listSessions = new HashMap<String, IApiSession>();
		this.listObservables = listObservables;
		this.connection = connection;
	}

	/**
	 * {@inheritDoc}
	 */
	public final ApiConnection getConnection() {
		return connection;
	}

	/**
	 * {@inheritDoc}
	 */
	public final IApiSession getActiveSession() {
		return activeSession;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isActivateSession(IApiSession s) {
		if (activeSession == null) {
			return false;
		}
		return activeSession.getId().equals(s.getId());
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean addSession(IApiSession s) {
		listSessions.put(s.getId(), s);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean removeSession(IApiSession s) {
		listSessions.remove(s.getId());
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean openSession(IApiSession s) throws ApiException {
		try {
			return  this.suspendSession(activeSession);
		} catch (ApiException e) {
			throw new ApiException("Impossible d'ouvrir la session '" + s.getName() + "' car " + e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean suspendSession(IApiSession session) throws ApiException {
		ApiSession s = (ApiSession) session;

		if (s == null) {
			return true;
		}
		if (s.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE) {
			return true;
		}
		throw new ApiException("Impossible de suspendre la session '" + session.getName() + "': " + " etat=" + s.getSessionStateMachine().getState() + " activeSession=" + isActivateSession(s));
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean resumeSession(IApiSession session) throws ApiException {
		ApiSession s = (ApiSession) session;
		if (s.getSessionStateMachine().getState() == ISessionStateMachine.SUSPEND_SESSION_STATE) {
			return true;
		}
		throw new ApiException("Impossible de reprondre la session '" + session.getName() + "': " + " etat=" + s.getSessionStateMachine().getState() + " activeSession=" + isActivateSession(s));
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean closeSession(IApiSession session) throws ApiException {
		ApiSession s = (ApiSession) session;

		if (s.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE || s.getSessionStateMachine().getState() == ISessionStateMachine.SUSPEND_SESSION_STATE) {
			return true;
		}
		throw new ApiException("Impossible de fermer la session '" + session.getName() + "': " + " etat=" + s.getSessionStateMachine().getState() + " activeSession=" + isActivateSession(s));
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean askForService(IApiSession session) throws ApiException {
		ApiSession s = (ApiSession) session;

		if (isActivateSession(s) && s.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE) {
			return true;
		}
		throw new ApiException("Impossible de demander un service sur la session '" + session.getName() + "': " + " etat=" + s.getSessionStateMachine().getState() +  " activeSession=" + isActivateSession(s));
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyEndOpenSession(IApiSession opened, MMenu menu) throws ApiException {
		LOGGER.finer("Notification au Gestionnaire de Sessions de la fin de l'ouverture de la session: " + opened.getName());

		if (activeSession != null) {
			notifyEndSuspendSession(activeSession);
		}
		this.activeSession = opened;
		this.addSession(opened);

		if (!((ApiSession) activeSession).getSessionStateMachine().goToIdleState()) {
			throw new ApiException("Impossible d'aller vers a l'etat IDLE_STATE");
		}

		LOGGER.finest("Demande la notification de la récéption d'un menu pour l'ouverture de la session: " + opened.getName());
		ReceptMenu m = new ReceptMenu(menu);
		((IReceptMenuObservable) listObservables.get(IObservables.RECEPT_MENU)).notifyObservers(m);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyEndSuspendSession(IApiSession suspended) throws ApiException  {
		LOGGER.finer("Notification au Gestionnaire de Sessions de la fin de la suspension de la session: " + suspended.getName());

		if (!((ApiSession) suspended).getSessionStateMachine().goToSuspendSessionState()) {
			throw new ApiException("Impossible d'aller vers a l'etat SUSPEND_SESSION_STATE");
		}
		activeSession = null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyEndResumeSession(IApiSession resumed) throws ApiException  {
		LOGGER.finer("Notification au Gestionnaire de Sessions de la fin de la restauration de la session: " + resumed.getName());

		if (activeSession != null) {
			((ApiSession) activeSession).suspend();
		}
		activeSession = listSessions.get(resumed.getId());
		if (!((ApiSession) activeSession).getSessionStateMachine().goToIdleState()) {
			throw new ApiException("Impossible d'aller vers a l'etat IDLE_STATE");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyEndCloseSession(IApiSession closed, String idSessionToResume) throws ApiException  {
		LOGGER.finer("Notification au Gestionnaire de Sessions de la fin de la fermeture de la session: " + closed.getName());

		if (!((ApiSession) closed).getSessionStateMachine().goToCloseSessionState()) {
			throw new ApiException("Impossible d'aller vers a l'etat CLOSE_SESSION_STATE");
		}

		this.removeSession(closed);

		//  S'il n'y a plus de sessions: activeSession est null
		if (isActivateSession(closed)) {
			this.activeSession = null;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyEndResult(IApiSession sessionExecuted, RService result, IService service, IGraph outputGraph) throws ApiException {
		LOGGER.finer("Notification au Gestionnaire de Sessions de la fin de l'execution d'un service: " + sessionExecuted.getName());

		if (!((ApiSession) sessionExecuted).getSessionStateMachine().goToIdleState()) {
			throw new ApiException("Impossible d'aller vers a l'etat IDLE_STATE");
		}

		LOGGER.finest("Demande la notification de la récéption d'un résultat pour la session: " + sessionExecuted.getName());
		ResultImpl receptResult = new ResultImpl(result, service, outputGraph);
		((IReceptResultObservable) listObservables.get(IObservables.RECEPT_RESULT)).notifyObservers(receptResult);

		LOGGER.finest("Demande la notification de la récéption d'un menu suite à l'execution d'un service pour la session: " + sessionExecuted.getName());
		ReceptMenu receptMenu = new ReceptMenu(result.getMenu());
		((IReceptMenuObservable) listObservables.get(IObservables.RECEPT_MENU)).notifyObservers(receptMenu);

	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean onlyOneSession() throws ApiException {
		if (listSessions.size() == 0) {
			throw new ApiException("Il n'y a pas de sessions");
		}
		if (listSessions.size() == 1) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void closeAllSessions() throws ApiException {
		LOGGER.finer("Fermeture de toutes les sessions par le Gestionnaire de Sessions");

		/** Détérmine s'il y a eu une erreur lors de la fermeture d'une session */
		boolean isError = false;
		/** Messages d'erreur s'il y a lieu */
		String msgError = "";

		/** Copie de la liste des sessions pour pouvoir supprimer les sessions */
		Map<String, IApiSession> copyListSessions = new HashMap<String, IApiSession>(listSessions);

		for (Entry<String, IApiSession> entry : copyListSessions.entrySet()) {
			try {
				listSessions.get(entry.getKey()).close();
			} catch (ApiException e) {
				isError = true;
				msgError +=
					"Erreur lors de la fermeture de la session: " + entry.getValue().getName() + ".\n"
					+ "Cause: " + e.getMessage() + "\n";
			}
		}

		if (isError) {
			throw new ApiException(msgError);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyEndInvalidate(IApiSession session, MMenu menu) {
		LOGGER.finer("Notification au Gestionnaire de Sessions de la fin de l'invalidation de la session: " + session.getName());

		LOGGER.finest("Demande la notification de la récéption du menu par défaut suite à l'invalidation de la session: " + session.getName());
		ReceptMenu receptMenu = new ReceptMenu(menu);
		((IReceptMenuObservable) listObservables.get(IObservables.RECEPT_MENU)).notifyObservers(receptMenu);
	}

}
