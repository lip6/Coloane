package fr.lip6.move.coloane.interfaces.objects.menu;

import java.util.List;

/**
 * Define a sub-menu.<br>
 * A sub-menu contains :
 * <ul>
 * 	<li>Other sub-menus</li>
 * 	<li>Some options</li>
 * 	<li>Some services</li>
 * </ul>
 */
public interface ISubMenu extends IItemMenu {

	/**
	 * @return The list of all service menu items
	 */
	List<IServiceMenu> getServiceMenus();

	/**
	 * @return The list of all option menu item
	 */
	List<IOptionMenu> getOptions();

	/**
	 * @return The list of all sub-menus
	 */
	List<ISubMenu> getSubMenus();
	
	/**
	 * Add an option to the sub-menu
	 * @param option The option to add
	 */
	void addOptionMenu(IOptionMenu option);

	/**
	 * Add a new sub-menu to the sub-menu
	 * @param submenu The sub-menu to add
	 */
	void addSubMenu(ISubMenu submenu);
	
	/**
	 * Add a new service menu to the sub-menu
	 * @param service The service to add
	 */
	void addServiceMenu(IServiceMenu service);
}
