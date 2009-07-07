package fr.lip6.move.coloane.apicami;

import fr.lip6.move.coloane.interfaces.api.IApi;
import fr.lip6.move.coloane.interfaces.api.IApiConnection;
import fr.lip6.move.coloane.interfaces.utils.ColoaneLogFormatter;
import fr.lip6.move.coloane.interfaces.utils.ColoaneLogHandler;
import fr.lip6.move.coloane.interfaces.utils.ConsoleHandler;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cette classe constitue Le point d'accès à la bibliothèque de connexion<br>
 * Son rôle essentiel est de construire des connexions.
 */
public class Api implements IApi {

	private static final String UINAME = "Coloane";
	private static final String UIVERSION = "3.0";

	// Initialisation statique du logger
	static { initializeLogger(); }

	/**
	 * Initialisation du logger d'evenements
	 */
	private static void initializeLogger() {
		Logger log = Logger.getLogger("fr.lip6.move.coloane.apicami"); //$NON-NLS-1$
		log.setLevel(Level.ALL); // On loggue tout !
		log.addHandler(new ConsoleHandler());

		try {
			ColoaneLogHandler handler = ColoaneLogHandler.getInstance();
			ColoaneLogFormatter format = new ColoaneLogFormatter();
			handler.setFormatter(format);
			log.addHandler(handler);
		} catch (IOException ioe) {
			System.err.println("Logger cannot be instanciated... Please contact the dev team"); //$NON-NLS-1$
		} catch (SecurityException se) {
			System.err.println("Logger cannot be instanciated... Please contact the dev team"); //$NON-NLS-1$
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final IApiConnection createApiConnection() {
		return new ApiConnection(UINAME, UIVERSION);
	}
}
