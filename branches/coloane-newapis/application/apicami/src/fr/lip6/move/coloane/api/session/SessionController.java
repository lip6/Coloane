package fr.lip6.move.coloane.api.session;

import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.ISessionInfo;
import fr.lip6.move.coloane.api.interfaces.ISessionStateMachine;
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
public class SessionController implements ISessionController {

	/** l'ensemble de nos session */
	private List<IApiSession> list;

	/** la session active */
	private IApiSession activeSession;

	/**
	 * Constructeur
	 */
	public SessionController() {
		this.activeSession = null;
		this.list = new ArrayList<IApiSession>();
	}

	/**
	 * {@inheritDoc}
	 */
	public final IApiSession getActiveSession() {
		return this.activeSession;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean addSession(IApiSession s) {
		this.list.add(s);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean removeSession(IApiSession session) {
		return (this.list.remove(session));
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isSessionActive(IApiSession session) {
		return this.activeSession.equals(session);
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean suspendSession(IApiSession session) {
		if (this.activeSession.equals(session)) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean resumeSession(IApiSession s) throws InterruptedException, IOException {
		if (this.activeSession.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE) {
			this.activeSession.suspendSession();
			this.activeSession = s;
			return true;
		} else {
			throw new IllegalStateException("on peut pas reprendre cette session!!!");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean openSession(IApiSession s) throws InterruptedException, IOException {
		if (this.activeSession == null) {
			this.activeSession = s;
			addSession(s);
			return true;
		} else {
			if (this.activeSession.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE){
				this.activeSession.suspendSession();
				this.activeSession = s;
				addSession(s);
				return true;
			} else {
				/// lever lexception attendre la notification de endOpenSession
				throw new IllegalStateException("une autre session est en cours d'ouverture!!!");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean askForService(ApiSession apiSession) {
		if (this.activeSession.equals(apiSession)){
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean closeSession(ApiSession apiSession) {
		if (this.activeSession.equals(apiSession)) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyEndOpenSession() {
		this.activeSession.notifyEndOpenSession();
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyEndSuspendSession() {
		this.activeSession.notifyEndSuspendSession();
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyEndCloseSession() {
		this.activeSession.notifyEndCloseSession();
		this.activeSession = null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyEndResumeSession(String nameSession) {
		//	for(IApiSession session : liste ) {
		//		System.out.println("dans la liste ya" +session.getSessionName());
		//	}
		//	System.out.println(this.activeSession.getSessionName() + this.activeSession.getSessionStateMachine().getState() );
		for (IApiSession session : liste) {

			if (session.getSessionName().equals(nameSession)){
				session.notifyEndResumeSession(nameSession);
				this.activeSession = session;
				break;
			}
		}
		// System.out.println(this.activeSession.getSessionName() + this.activeSession.getSessionStateMachine().getState() );
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyWaitingForModel() throws IOException {
		this.activeSession.notifyWaitingForModel();
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyWaitingForResult() {
		this.activeSession.notifyWaitingForResult();
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyEndResult() {
		this.activeSession.notifyEndResult();
	}

	/**
	 * {@inheritDoc}
	 */
	public final void notifyReceptSessionInfo(ISessionInfo o) {
		this.activeSession.notifyReceptSessionInfo(o);
	}
}
