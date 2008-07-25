package fr.lip6.move.coloane.interfaces.objects.menu;

import java.util.List;

/**
 * Cette interface représent un menu principal
 */
public interface IRootMenu {

	/**
	 * @return le nom du menu principal
	 */
	String getName();

	/**
	 * @return les sous-menus qui composent le menu principal
	 */
	List<ISubMenu> getAllSubMenus();
}
