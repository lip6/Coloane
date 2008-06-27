package fr.lip6.move.coloane.apiws.api;

import fr.lip6.move.coloane.apiws.interfaces.api.IApi;
import fr.lip6.move.coloane.apiws.interfaces.api.IApiConnection;

public class Api implements IApi{
	
	private static final String uiName = "Coloane";
	
	private static final String uiVersion ="0.0.1-alpha";
	

	public static IApiConnection getApiConnection() {
		return new ApiConnection();
	}

	public String getUiName() {
		return uiName;
	}

	public String getUiVersion() {
		return uiVersion;
	}

}
