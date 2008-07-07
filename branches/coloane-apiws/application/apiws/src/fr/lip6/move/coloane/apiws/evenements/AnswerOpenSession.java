package fr.lip6.move.coloane.apiws.evenements;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerOpenSession;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IMMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.service.IServicesAvailable;
import fr.lip6.move.coloane.apiws.interfaces.session.IApiSession;
import fr.lip6.move.coloane.apiws.objects.menu.MMenuImpl;
import fr.lip6.move.coloane.apiws.objects.service.ServicesAvailableImpl;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;

public class AnswerOpenSession implements IAnswerOpenSession{
	
	private String formalism;
	
	private String idSession;
	
	private String aboutService;
	
	private String incremental;
	
	private String nameService;
	
	private int resultatCalcule;
	
	private String sessionName;
	
	private IMMenu menus;
	
	private IServicesAvailable services;
	
	public AnswerOpenSession(Session s){
		this.formalism = s.getFormalism();
		this.idSession = s.getSessionId();
		this.menus = new MMenuImpl(s.getMenu());
		this.services = new ServicesAvailableImpl(s.getMenu());
		// TODO Voir avec J-B et Silien comment recuperer ces elements.
		this.aboutService = null;
		this.incremental = null;
		this.nameService = null;
		this.resultatCalcule = 0;
		this.sessionName = null;
		
	}
	
	public AnswerOpenSession(IApiSession s){
		this.formalism = s.getSessionFormalism();
		this.idSession = s.getIdSession();
		this.menus = s.getMenus();
		this.services = s.getServicesAvailable();
		// TODO Voir avec J-B et Silien comment recuperer ces elements.
		this.aboutService = null;
		this.incremental = null;
		this.nameService = null;
		this.resultatCalcule = 0;
		this.sessionName = null;
		
	}
	
	public String getFormalism(){
		return formalism;
	}
	
	public String getIdSession(){
		return idSession;
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

	public String getSessionName() {
		return sessionName;
	}

	public IMMenu getMenus() {
		return menus;
	}
	
	public IServicesAvailable getServicesAvailable(){
		return services;
	}

}
