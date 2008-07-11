package fr.lip6.move.coloane.apiws.connection;

import fr.lip6.move.coloane.interfaces.api.connection.IApi;
import fr.lip6.move.coloane.interfaces.api.connection.IApiConnection;

public class Api implements IApi  {
	
	private static final String uiName = "Coloane";
	
	private static final String uiVersion ="0.0.1-alpha";

	public String getUiName() {
		return uiName;
	}

	public String getUiVersion() {
		return uiVersion;
	}
	
	public IApiConnection getApiConnection() {
		// TODO Auto-generated method stub
		return null;
	}

}
