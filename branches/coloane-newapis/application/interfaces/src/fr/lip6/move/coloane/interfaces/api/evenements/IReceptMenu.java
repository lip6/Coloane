package fr.lip6.move.coloane.interfaces.api.evenements;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.objects.menu.IMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;

public interface IReceptMenu {

	/**
	 * Recupere les menus
	 * @return les menus
	 */
	public ArrayList<IMenu> getMenus();
	
	/**
	 * Recupere les mises a jour sur les menus
	 * @return les mises a jour sur les menus
	 */
	public ArrayList<IUpdateMenu> getUpdateMenus();
}
