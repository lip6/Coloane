package fr.lip6.move.coloane.interfaces.objects.menu;

import java.util.List;

/**
 * Définition d'un sous-menu.
 */
public interface ISubMenu extends IItemMenu {

	/**
	 * @return la liste des items qui composent le sous-menu
	 */
	List<IServiceMenu> getServiceMenus();

	/**
	 * @return la liste des options qui composent le sous-menu
	 */
	List<IOptionMenu> getOptions();

	/**
	 * @return la liste des sous-menus qui composent le sous-menu
	 */
	List<ISubMenu> getSubMenus();
}
