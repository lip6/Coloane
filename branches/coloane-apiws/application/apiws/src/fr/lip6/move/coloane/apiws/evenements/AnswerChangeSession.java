package fr.lip6.move.coloane.apiws.evenements;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerChangeSession;
import fr.lip6.move.coloane.apiws.interfaces.objects.IMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;

public class AnswerChangeSession implements IAnswerChangeSession {
	
	private String formalism;
	
	private String idSession;
	
	private ArrayList<IMenu> menus;
	
	public AnswerChangeSession(Session s){
		this.formalism = s.getFormalism();
		this.idSession = s.getSessionId();
		// TODO Voir avec J-B et Silien comment recuperer ces elements.
		this.menus = new ArrayList<IMenu>();		
	}

	public String getFormalism() {
		return formalism;
	}

	public String getIdSession() {
		return idSession;
	}

	public ArrayList<IMenu> getMenus() {
		return menus;
	}
}
