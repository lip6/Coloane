package fr.lip6.move.coloane.api.session;

import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.ISessionStateMachine;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

/**
 * Cette classe est une factory pour le package session.
 *
 * @authorKahina Bouarab
 * @author Youcef Belattaf
 */
public class SessionFactory {

	/**
	 * @param sessionCont
	 * @param speaker
	 * @return interface IApiSession
	 */
	public static IApiSession getNewApiSession(ISessionController sessionCont, ISpeaker speaker) {
		return (IApiSession) new ApiSession(sessionCont, speaker);
	}

	/**
	 * @return interface ISessionController
	 */
	public static ISessionController getNewSessionController() {
		return new SessionController();
	}

	/**
	 * 
	 * @return
	 */
	public static ISessionStateMachine getNewSessionStateMachine() {
		return new SessionStateMachine();
	}
}
