package fr.lip6.move.coloane.apiws.interfaces.objects.menu;

import java.util.ArrayList;

public interface IMMenu {
	
	/**
	 * Recupere la liste des menus principales
	 * @return la liste des menus principales
	 */
	public ArrayList<IRootMenu> getRootsMenus();
}
