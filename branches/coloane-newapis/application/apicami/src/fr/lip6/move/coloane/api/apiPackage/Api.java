package fr.lip6.move.coloane.api.apiPackage;

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

	/** Le Logger à initialiser */
	private static Logger LOGGER;

	/** Constructeur */
	public Api() {
		initializeLogger();
	}

	/**
	 * Initialisation du logger pour l'API entière
	 */
	private static void initializeLogger() {
		LOGGER = Logger.getLogger("fr.lip6.move.coloane.api");
		LOGGER.setLevel(Level.FINEST); // On loggue tout !
		try {
			LOGGER.addHandler(new ApiHandler());
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
		return new ApiConnection();
	}
}
