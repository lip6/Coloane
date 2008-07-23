package fr.lip6.move.coloane.apiws.log;

import java.io.IOException;
import java.util.logging.FileHandler;

/**
 * Classe en charge de la gestion des messages de log<br>
 * Ici, elle permet l'affichage de tous les message sur la console.
 */
public class ApiLogHandler extends FileHandler {

	private static ApiLogHandler instance;

	/**
	 * Constructeur
	 * @throws IOException Problème d'écriture sur le fichier de log
	 * @throws SecurityException Problème de droits sur le fichier de logs
	 */
	public ApiLogHandler() throws IOException, SecurityException {
		super("%t/coloane.log", true); //$NON-NLS-1$
	}

	/**
	 * Récupére l'instance du handler
	 * @return l'instance du handler
	 * @throws IOException Problème d'écriture sur le fichier de log
	 * @throws SecurityException Problème de droits sur le fichier de logs
	 */
	public static ApiLogHandler getInstance() throws IOException, SecurityException {
		if (instance == null) { instance = new ApiLogHandler(); }
		return instance;
	}

}
