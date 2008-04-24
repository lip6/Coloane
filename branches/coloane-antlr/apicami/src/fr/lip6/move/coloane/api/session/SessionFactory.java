package fr.lip6.move.coloane.api.session;

import fr.lip6.move.coloane.api.interfaces.IApiSession;

/**
 * Cette classe est une factory pour le package session.
 * @author uu & kahoo
 *
 */

public class SessionFactory {

	/**
	 *
	 * @return interface IApiSession
	 */
	public static IApiSession getNewApiSession(){
		return new ApiSession();
	}
}
