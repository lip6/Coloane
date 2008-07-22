package fr.lip6.move.coloane.apiws.session;

import java.util.Map;

import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

/**
 * Cette classe représent une fabrique.
 */
public class SessionFactory {

	/**
	 * Crée une nouvelle session
	 * @param sessionController le gestionnaire de sessions à utiliser
	 * @param speaker le speaker à utiliser par la session
	 * @return une session
	 */
	public static IApiSession getNewApiSession(ISessionController sessionController, ISpeaker speaker) {
		return (IApiSession) new ApiSession(sessionController, speaker);
	}

	/**
	 * Crée un nouveau gestionnaire de sessions
	 * @param listObservables la liste des observables à notifier
	 * @return un nouveau gestionnaire de sessions
	 */
	public static ISessionController getNewSessionController(Map<Integer, Object> listObservables) {
		return (ISessionController) new SessionController(listObservables);
	}

	/**
	 * Crée un automate représentant une session
	 * @return un automate représentant une session
	 */
	public static ISessionStateMachine getNewSessionStateMachine() {
		return (ISessionStateMachine) new SessionStateMachine();
	}
}
