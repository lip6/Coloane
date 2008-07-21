package fr.lip6.move.coloane.apiws.connection;

import fr.lip6.move.coloane.interfaces.api.connection.IApi;
import fr.lip6.move.coloane.interfaces.api.connection.IApiConnection;

/**
 * Cette classe constitue le point d'accès à la bibliothèque de connexion<br>
 * Son rôle essentiel est de construire des connexions.
 */
public class Api implements IApi {

	/**
	 * {@inheritDoc}
	 */
	public final IApiConnection getApiConnection() {
		return new ApiConnection();
	}
}
