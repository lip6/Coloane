package fr.lip6.move.coloane.api.session;

import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.ISessionStateMachine;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe est chargée de la gestion des sessions
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 */
public final class SessionController implements ISessionController {

	/** L'instance du session contrôleur */
	private static ISessionController instance = null;

	/** L'ensemble de nos session */
	private List<ApiSession> list;

	/** La session active */
	private ApiSession activeSession;

	/**
	 * Constructeur masqué pour éviter les doublons
	 */
	private SessionController() {
		this.activeSession = null;
		this.list = new ArrayList<ApiSession>();
	}

	/**
	 * @return l'instance unique du session contrôleur
	 */
	public static ISessionController getInstance() {
		if (instance == null) {
			instance = new SessionController();
		}
		return instance;
	}

	/**
	 * {@inheritDoc}
	 */
	public ApiSession getActiveSession() {
		return this.activeSession;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean openSession(ApiSession s) throws ApiException {
		// Pas de session active donc celle ci est la bonne !
		if (this.activeSession == null) {
			this.activeSession = s;
			addSession(this.activeSession);
			return true;

		// Sinon d'autres sessions existent
		} else {

			// Si la session active dort... alors on la suspend et on prend sa place
			if (this.activeSession.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE) {
				this.activeSession.suspendSession();
				this.activeSession = s;
				addSession(this.activeSession);
				return true;

			// Sinon l'ouverture n'est pas autorisée
			} else {
				throw new ApiException("Another session is processed by the platform... Please wait...");
			}
		}
	}

	/**
	 * Ajoute une session dans ma liste de sessions.
	 * @param s La session à rajouter.
	 */
	private void addSession(ApiSession s) {
		this.list.add(s);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean closeSession(ApiSession apiSession) {
		if (this.activeSession.equals(apiSession)) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean resumeSession(ApiSession s) throws ApiException {
		if (this.activeSession.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE) {
			this.activeSession.suspendSession();
			this.activeSession = s;
			return true;
		} else {
			throw new ApiException("Since this session is not active, it cannot be resumed");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean suspendSession(IApiSession session) {
		if (this.activeSession.equals(session)) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean askForService(ApiSession apiSession) {
		if (this.activeSession.equals(apiSession)) {
			// TODO : A faire !
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyEndOpenSession() {
		this.activeSession.notifyEndOpenSession();
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyEndSuspendSession() {
		this.activeSession.notifyEndSuspendSession();
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyEndCloseSession() {
		this.activeSession.notifyEndCloseSession();
		this.activeSession = null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyEndResumeSession(String nameSession) {
		// Je recherche la session qui vient de reprendre et je la notifie
		for (ApiSession session : this.list) {
			if (session.getSessionName().equals(nameSession)) {
				session.notifyEndResumeSession();
				// Mise a jour de la nouvelle session active
				this.activeSession = session;
				break;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyWaitingForModel() throws IOException {
		this.activeSession.notifyWaitingForModel();
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyWaitingForResult() {
		this.activeSession.notifyWaitingForResult();
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyEndResult() {
		this.activeSession.notifyEndResult();
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyReceptSessionInfo(ISessionInfo o) {
		this.activeSession.notifyReceptSessionInfo(o);
	}
}
