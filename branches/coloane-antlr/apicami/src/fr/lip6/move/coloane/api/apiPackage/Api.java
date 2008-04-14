package fr.lip6.move.coloane.api.apiPackage;

import fr.lip6.move.coloane.api.interfaces.IApiConnection;


/**
 * Cette classe constitue Le point d'accès à la bibliothèque
 * Son rôle essentiel est de construire des connexions.
 */


public class Api {

	public static IApiConnection getApiConnection(){
		return new ApiConnection();
	}

}
