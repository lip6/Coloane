package fr.lip6.move.coloane.apiws.session;

import fr.lip6.move.coloane.apiws.ApiConnection;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

import java.util.Map;

/**
 * Cette classe représent une fabrique.
 *
 * @author Monir CHAOUKI
 */
public final class SessionFactory {

	/**
	 * Constructeur
	 */
	private SessionFactory() { }

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
	 * @param connection la connexion
	 * @return un nouveau gestionnaire de sessions
	 */
	public static ISessionController getNewSessionController(Map<Integer, Object> listObservables, ApiConnection connection) {
		return (ISessionController) new SessionController(listObservables, connection);
	}

	/**
	 * Crée un automate représentant une session
	 * @return un automate représentant une session
	 */
	public static ISessionStateMachine getNewSessionStateMachine() {
		return (ISessionStateMachine) new SessionStateMachine();
	}
}
