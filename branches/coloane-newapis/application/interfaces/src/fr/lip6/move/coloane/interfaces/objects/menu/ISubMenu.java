package fr.lip6.move.coloane.interfaces.objects.menu;

import java.util.List;

/**
 * Cette interface définie un sous-menu.
 */
public interface ISubMenu {

	/**
	 * Récupére la liste des items qui compose le sous-menu
	 * @return la liste des items qui compose le sous-menu
	 */
	List<ItemMenu> getSubMenu();

}
