package fr.lip6.move.coloane.apiws.evenements;


import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerSuspendSession;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IMMenu;
import fr.lip6.move.coloane.apiws.interfaces.session.IApiSession;
import fr.lip6.move.coloane.apiws.objects.menu.MMenuImpl;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;

public class AnswerSuspendSession implements IAnswerSuspendSession{
	private String formalism;

	private String idSession;

	private IMMenu menus;

	public AnswerSuspendSession(Session s){
		this.formalism = s.getFormalism();
		this.idSession = s.getSessionId();
		this.menus = new MMenuImpl(s.getMenu());
	}

	public AnswerSuspendSession(IApiSession s){
		this.formalism = s.getSessionFormalism();
		this.idSession = s.getIdSession();
		this.menus = s.getMenus();

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
}
