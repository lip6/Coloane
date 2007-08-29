package fr.lip6.move.coloane.interfaces.utils;

import java.io.IOException;
import java.util.logging.FileHandler;

/**
 * Classe en charge de la gestion des messages de log<br>
 * Ici, elle permet l'affichage de tous les message sur la console.
 */
public class ColoaneLogHandler extends FileHandler {

	public ColoaneLogHandler() throws IOException, SecurityException {
		super("%t/coloane.log", true);
	}


}
