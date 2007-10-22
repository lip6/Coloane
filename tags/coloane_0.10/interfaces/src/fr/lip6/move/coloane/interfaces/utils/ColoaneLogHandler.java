package fr.lip6.move.coloane.interfaces.utils;

import java.io.IOException;
import java.util.logging.FileHandler;

/**
 * Classe en charge de la gestion des messages de log<br>
 * Ici, elle permet l'affichage de tous les message sur la console.
 */
public final class ColoaneLogHandler extends FileHandler {

	private static ColoaneLogHandler instance = null;

	private ColoaneLogHandler() throws IOException, SecurityException {
		super("%t/coloane.log", true);
	}

	public static ColoaneLogHandler getInstance() throws IOException, SecurityException {
		if (instance == null) { instance = new ColoaneLogHandler(); }
		return instance;
	}
}
