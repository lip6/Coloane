package fr.lip6.move.coloane.api.session;

import fr.lip6.move.coloane.api.interfaces.*;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
/**
 * Cette classe est une factory pour le package session.
 * @author uu & kahoo
 *
 */

public class SessionFactory {

	/**
	 * @return interface IApiSession
	 */
	public static IApiSession getNewApiSession(ISessionController sessionCont,ISpeaker speaker){
		return new ApiSession(sessionCont,speaker);
	}

	/**
	 * @return interface ISessionController
	 */
	public static ISessionController getNewSessionController(){
		return new SessionController();
	}
}
