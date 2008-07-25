package fr.lip6.move.coloane.interfaces.objects.menu;

import java.util.List;

/**
 * Cette interface définie un sous-menu.
 */
public interface ISubMenu extends IItemMenu {

	/**
	 * Récupére la liste des items qui compose le sous-menu
	 * @return la liste des items qui compose le sous-menu
	 */
	List<IItemMenu> getItems();

	/**
	 * Récupére la liste des options qui compose le sous-menu
	 * @return la liste des options qui compose le sous-menu
	 */
	List<IOptionMenu> getOptions();

	/**
	 * Récupére la liste des sous-menu qui compose le sous-menu
	 * @return la liste des sous-menu qui compose le sous-menu
	 */
	List<ISubMenu> getSubMenus();

}
