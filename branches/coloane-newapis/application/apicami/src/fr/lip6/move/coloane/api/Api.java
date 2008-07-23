package fr.lip6.move.coloane.api;

import fr.lip6.move.coloane.api.log.ApiHandler;
import fr.lip6.move.coloane.interfaces.api.connection.IApi;
import fr.lip6.move.coloane.interfaces.api.connection.IApiConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cette classe constitue Le point d'accès à la bibliothèque de connexion<br>
 * Son rôle essentiel est de construire des connexions.
 */
public class Api implements IApi {

	private static String uiName = "Coloane";
	private static String uiVersion = "3.0";

	// Initialisation statique du logger
	static { initializeLogger(); }

	/**
	 * Initialisation du logger pour l'API entière
	 */
	private static void initializeLogger() {
		Logger log = Logger.getLogger("fr.lip6.move.coloane.apicami");
		log.setLevel(Level.FINEST); // On loggue tout !
		try {
			log.addHandler(new ApiHandler());
		} catch (SecurityException e) {
			System.err.println("Impossible d'initialiser le gestionnaire de logs sur fichier");
		} catch (FileNotFoundException e) {
			System.err.println("Impossible d'initialiser le gestionnaire de logs sur fichier");
		} catch (IOException e) {
			System.err.println("Impossible d'initialiser le gestionnaire de logs sur fichier");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final IApiConnection getApiConnection() {
		return new ApiConnection(uiName, uiVersion);
	}
}
