package fr.lip6.move.coloane.api.session;

import fr.lip6.move.coloane.api.interfaces.ISpeaker;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

/**
 * Cette classe est une factory pour le package session.
 *
 * @authorKahina Bouarab
 * @author Youcef Belattaf
 */
public final class SessionFactory {

	/**
	 * Constructeur
	 */
	private SessionFactory() { }

	/**
	 * @param speaker
	 * @return interface IApiSession
	 */
	public static IApiSession getNewApiSession(ISpeaker speaker) {
		return (IApiSession) new ApiSession(speaker);
	}
}
