package fr.lip6.move.coloane.apiws.interfaces.objects.menu;

import java.util.ArrayList;

public interface ISubMenu extends IElementMenu {
	
	/**
	 * Recupere la liste des options
	 * @return la liste des options
	 */
	public ArrayList<IOptionMenu> getOption();
	
	/**
	 * Recupere la liste des services
	 * @return la liste des services
	 */
	public ArrayList<IItemMenu> getItems();
	
	/**
	 * Recupere la liste des sous-menus
	 * @return la liste des sous-menus
	 */
	public ArrayList<ISubMenu> getSubMenus();
	
}
