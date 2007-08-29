package fr.lip6.move.coloane.interfaces.utils;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Classe en charge de la gestion des messages de log<br>
 * Ici, elle permet l'affichage de tous les message sur la console.
 */
public class ColoaneLogHandler extends Handler {

	@Override
	public void close() throws SecurityException { }

	@Override
	public void flush() { }

	@Override
	public final void publish(LogRecord log) {
		System.out.println("[" + log.getLevel() + "] " + log.getMessage());
	}

}
