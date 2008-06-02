package fr.lip6.move.coloane.api.apiPackage;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.lip6.move.coloane.api.interfaces.IApiConnection;
import fr.lip6.move.coloane.api.log.ApiHandler;


/**
 * Cette classe constitue Le point d'accès à la bibliothèque
 * Son rôle essentiel est de construire des connexions.
 */



public class Api {

	//TODO set pour l'uiName et la version
	static String uiName = "Coloane";
	static String uiVersion = "X.x";
	static Logger logger;


	// sert à initialiser l'api
	public static void initialize(){

		/* initialiser le logger*/
		logger = Logger.getLogger("fr.lip6.move.coloane.api");
		logger.setLevel(Level.FINEST); // On loggue tout !
		try {
			logger.addHandler(new ApiHandler());
		} catch (SecurityException e) {
			System.err.println("Impossible d'initialiser le gestionnaire de logs sur fichier");
		} catch (FileNotFoundException e) {
			System.err.println("Impossible d'initialiser le gestionnaire de logs sur fichier");
		} catch (IOException e) {
			System.err.println("Impossible d'initialiser le gestionnaire de logs sur fichier");
		}
	}


	/**
	 *
	 * @return
	 * @throws IOException
	 */
	public static IApiConnection getApiConnection() throws IOException{
		return new ApiConnection();
	}





}
