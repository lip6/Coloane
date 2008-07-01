package fr.lip6.move.coloane.apiws.interfaces.evenements;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.objects.IMenu;

public interface IAnswerSuspendSession {
	
	public String getFormalism();
	
	public String getIdSession();
	
	public ArrayList<IMenu> getMenus();
}
