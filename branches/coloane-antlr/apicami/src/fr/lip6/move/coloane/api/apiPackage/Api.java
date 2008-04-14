package fr.lip6.move.coloane.api.apiPackage;

import fr.lip6.move.coloane.api.interfaces.IApiConnection;


/**
 * Cette classe constitue Le poit d'accès à la bibliothèque
 * Son rôle essentiel est de construire des connections.
 */


public class Api {
	
	public static IApiConnection getApiConnection(){
		return new ApiConnection();
	}

}
