package fr.lip6.move.coloane.apiws.connection;

import fr.lip6.move.coloane.interfaces.api.connection.IApiConnection;

public class Api {
	
	private static final String uiName = "Coloane";
	
	private static final String uiVersion ="0.0.1-alpha";

	public static String getUiName() {
		return uiName;
	}

	public static String getUiVersion() {
		return uiVersion;
	}
	
	public static IApiConnection getApiConnection() {
		return new ApiConnection();
	}

}