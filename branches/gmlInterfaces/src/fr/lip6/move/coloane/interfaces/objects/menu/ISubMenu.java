/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
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
