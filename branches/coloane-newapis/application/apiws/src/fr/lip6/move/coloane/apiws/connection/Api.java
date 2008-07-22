package fr.lip6.move.coloane.apiws.connection;

import fr.lip6.move.coloane.apiws.log.ApiLogFormatter;
import fr.lip6.move.coloane.apiws.log.ApiLogHandler;
import fr.lip6.move.coloane.interfaces.api.connection.IApi;
import fr.lip6.move.coloane.interfaces.api.connection.IApiConnection;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Cette classe constitue le point d'accès à la bibliothèque de connexion<br>
 * Son rôle essentiel est de construire des connexions.
 */
public class Api implements IApi {

	private static Logger LOGGER;

	/**
	 * Constructeur
	 */
	public Api() {
		initializeLogger();
	}
	/**
	 * {@inheritDoc}
	 */
	public final IApiConnection getApiConnection() {
		return new ApiConnection();
	}

	/**
	 * Initialisation du logger d'evenements
	 */
	private void initializeLogger() {
		LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws"); //$NON-NLS-1$
		LOGGER.setLevel(Level.ALL); // On loggue tout !
		LOGGER.addHandler(new Handler() {
			@Override
			public void close() throws SecurityException { }
			@Override
			public void flush() { }
			@Override
			public void publish(LogRecord record) {
				System.out.println("[" + record.getLevel() + "] " + record.getMessage() + " - " + record.getSourceClassName() + "." + record.getSourceMethodName());   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
			}
		});

		try {
		ApiLogHandler handler = ApiLogHandler.getInstance();
		ApiLogFormatter format = new ApiLogFormatter();
		handler.setFormatter(format);
		LOGGER.addHandler(handler);
		} catch (IOException ioe) {
			System.err.println("Logger cannot be instanciated... Please contact the dev team"); //$NON-NLS-1$
		} catch (SecurityException se) {
			System.err.println("Logger cannot be instanciated... Please contact the dev team"); //$NON-NLS-1$
		}
	}
}
