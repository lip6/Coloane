package fr.lip6.move.coloane.apiws.session;

import fr.lip6.move.coloane.apiws.evenements.ReceptMenu;
import fr.lip6.move.coloane.apiws.evenements.ReceptResult;
import fr.lip6.move.coloane.apiws.interfaces.observables.IObservables;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptMenuObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IReceptResultObservable;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.wrapper.ws.WrapperStub.MMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.RService;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Cette classe représent le gestionnaire de sessions.
 */
public class SessionController implements ISessionController {

	/**
	 * Represent la session active
	 */
	private IApiSession activeSession;

	/**
	 * Represent l'ensemle des sessions
	 */
	private Map<String, IApiSession> listSessions;

	/**
	 * Represent la liste des observables
	 */
	private Map<Integer, Object> listObservables;

	/**
	 * Constructeur
	 * @param listObservables la liste des observables à notifier
	 */
	public SessionController(Map<Integer, Object> listObservables) {
		this.activeSession = null;
		this.listSessions = new HashMap<String, IApiSession>();
		this.listObservables = listObservables;
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
			throw new ApiException("Impossible d'ouvrire une session -> " + e.getMessage());
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
		throw new ApiException("Impossible de suspendre la session: idSession=" + s.getId() + " etat=" + s.getSessionStateMachine().getState() + " activeSession=" + isActivateSession(s));
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean resumeSession(IApiSession session) throws ApiException {
		ApiSession s = (ApiSession) session;
		if (s.getSessionStateMachine().getState() == ISessionStateMachine.SUSPEND_SESSION_STATE) {
			return true;
		}
		throw new ApiException("Impossible de reprondre la session: idSession=" + s.getId() + " etat=" + s.getSessionStateMachine().getState() + " activeSession=" + isActivateSession(s));
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean closeSession(IApiSession session) throws ApiException {
		ApiSession s = (ApiSession) session;

		if (s.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE || s.getSessionStateMachine().getState() == ISessionStateMachine.SUSPEND_SESSION_STATE) {
			return true;
		}
		throw new ApiException("Impossible de fermer la session: idSession=" + s.getId() + " etat=" + s.getSessionStateMachine().getState() + " activeSession=" + isActivateSession(s));
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean askForService(IApiSession session) throws ApiException {
		ApiSession s = (ApiSession) session;

		if (isActivateSession(s) && s.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE) {
			return true;
		}
		throw new ApiException("Impossible de demander un service sur la session: idSession=" + s.getId() + " etat=" + s.getSessionStateMachine().getState() +  " activeSession=" + isActivateSession(s));
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyEndOpenSession(IApiSession opened, MMenu menu) throws ApiException {
		if (activeSession != null) {
			notifyEndSuspendSession(activeSession);
		}
		this.activeSession = opened;
		this.addSession(opened);

		if (!((ApiSession) activeSession).getSessionStateMachine().goToIdleState()) {
			throw new ApiException("Impossible d'aller vers a l'etat IDLE_STATE");
		}

		ReceptMenu m = new ReceptMenu(menu);
		((IReceptMenuObservable) listObservables.get(IObservables.RECEPT_MENU)).notifyObservers(m);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyEndSuspendSession(IApiSession suspended) throws ApiException  {
		if (!((ApiSession) suspended).getSessionStateMachine().goToSuspendSessionState()) {
			throw new ApiException("Impossible d'aller vers a l'etat SUSPEND_SESSION_STATE");
		}
		activeSession = null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyEndResumeSession(IApiSession resumed) throws ApiException  {

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
		if (!((ApiSession) closed).getSessionStateMachine().goToCloseSessionState()) {
			throw new ApiException("Impossible d'aller vers a l'etat CLOSE_SESSION_STATE");
		}

		this.removeSession(closed);

		//  S'il n'y a plus de sessions: activeSession est null
		if (closed.getId().equals(idSessionToResume) && listSessions.size() == 0) {
			this.activeSession = null;
		} /*else { //  Sinon: activeSession est la session renvoyée par le wrapper
			//listSessions.get(idSessionToResume).resumeSession();
			this.activeSession = listSessions.get(idSessionToResume);
		}*/

	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyEndResult(IApiSession sessionExecuted, RService result, IGraph newGraph) throws ApiException {
		if (!((ApiSession) sessionExecuted).getSessionStateMachine().goToIdleState()) {
			throw new ApiException("Impossible d'aller vers a l'etat IDLE_STATE");
		}

		ReceptResult receptResult = new ReceptResult(result);
		((IReceptResultObservable) listObservables.get(IObservables.RECEPT_RESULT)).notifyObservers(receptResult);

	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyWaitingForModel() {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyWaitingForResult() {
		// TODO Auto-generated method stub

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

}
