package fr.lip6.move.coloane.apiws.interfaces.evenements;

import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IMMenu;

public interface IAnswerResumeSession {
	
	public String getFormalism();

	public String getIdSession();

	public IMMenu getMenus();
}
