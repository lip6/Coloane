package fr.lip6.move.coloane.interfaces.objects.menu;

import java.util.List;

/**
 * Cette interface représent un menu principal
 */
public interface IRootMenu {

	/**
	 * Récupére le nom du menu principal
	 * @return le nom du menu principal
	 */
	String getName();

	/**
	 * Modifie le nom du menu principal
	 * @param name le nouveau nom du menu principal
	 */
	void setName(String name);

	/**
	 * Récupére le sous-menu qui compose le menu principal
	 * @return le sous-menu qui compose le menu principal
	 */
	List<ItemMenu> getSubMenu();

}
