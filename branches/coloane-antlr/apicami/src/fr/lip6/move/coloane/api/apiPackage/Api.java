package fr.lip6.move.coloane.api.apiPackage;

import java.io.IOException;

import fr.lip6.move.coloane.api.interfaces.IApiConnection;


/**
 * Cette classe constitue Le point d'accès à la bibliothèque
 * Son rôle essentiel est de construire des connexions.
 */


public class Api {

	//TODO set pour l'uiName et la version
	static String uiName = "Coloane";
	static String uiVersion = "X.x";

	public static IApiConnection getApiConnection() throws IOException{
		return new ApiConnection();
	}

}
