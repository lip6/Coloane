package fr.lip6.move.coloane.apiws.objects.api;

import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;

public class SessionInfo implements ISessionInfo {

	private String aboutService;
	
	private String incremental;
	
	private String nameService;
	
	private int resultatCalcule;
	
	public SessionInfo(Session s){
		// TODO Voir avec J-B et Silien comment recuperer ces elements.
		this.aboutService = null;
		this.incremental = null;
		this.nameService = null;
		this.resultatCalcule = 0;		
	}
	
	public String getAboutService() {
		return aboutService;
	}

	public String getIncremental() {
		return incremental;
	}

	public String getNameService() {
		return nameService;
	}

	public int getResultatCalcule() {
		return resultatCalcule;
	}

}
