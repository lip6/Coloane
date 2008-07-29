package fr.lip6.move.coloane.api.session;

import fr.lip6.move.coloane.api.ApiConnection;
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
	 * Création d'un nouvel objet session
	 * @param apiConnection Le gestionnaire de connexion
	 * @param speaker L'objet qui parle à la plate-forme
	 * @return l'objet session fraîchement créé
	 */
	public static IApiSession getNewApiSession(ApiConnection apiConnection, ISpeaker speaker) {
		return (IApiSession) new ApiSession(apiConnection, speaker);
	}
}
