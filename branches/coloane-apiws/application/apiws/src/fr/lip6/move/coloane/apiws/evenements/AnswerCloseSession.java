package fr.lip6.move.coloane.apiws.evenements;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerCloseSession;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IMMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.service.IServicesAvailable;
import fr.lip6.move.coloane.apiws.interfaces.session.IApiSession;
import fr.lip6.move.coloane.apiws.objects.menu.MMenuImpl;
import fr.lip6.move.coloane.apiws.objects.service.ServicesAvailableImpl;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;

public class AnswerCloseSession implements IAnswerCloseSession{

	private String formalism;
	
	private String idSession;
	
	private IMMenu menus;
	
	private IServicesAvailable services;
	
	public AnswerCloseSession(Session s){
		this.formalism = s.getFormalism();
		this.idSession = s.getSessionId();
		this.menus = new MMenuImpl(s.getMenu());
		this.services = new ServicesAvailableImpl(s.getMenu());
	}
	
	public AnswerCloseSession(IApiSession s){
		this.formalism = s.getSessionFormalism();
		this.idSession = s.getIdSession();
		this.menus = s.getMenus();
		this.services = s.getServicesAvailable();
		
	}

	public String getFormalism() {
		return formalism;
	}

	public String getIdSession() {
		return idSession;
	}

	public IMMenu getMenus() {
		return menus;
	}
	
	public IServicesAvailable getServicesAvailable(){
		return services;
	}
}
