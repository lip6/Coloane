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
package fr.lip6.move.coloane.core.ui.menus;

import fr.lip6.move.coloane.core.extensions.ApiDescription;
import fr.lip6.move.coloane.core.ui.actions.OptionAction;
import fr.lip6.move.coloane.core.ui.actions.ServiceAction;
import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;

import java.util.logging.Logger;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Set of methods that handle Coloane menu.<br>
 *
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public final class MenuManipulation {
	/**
	 * Utility class
	 */
	private MenuManipulation() { }

	/**
	 * Build a root menu
	 * @param rootMenuName The name of the menu to build
	 * @param rootMenuDescription the description of the menu to build
	 * @param rootMenuImage the icon associated with the menu (or <code>null</code> if not)
	 * @return A root menu that corresponds to the menu description
	 */
	public static MenuManager buildRootMenu(String rootMenuName, String rootMenuDescription, ImageDescriptor rootMenuImage) {
		String menuId = rootMenuName.toLowerCase();
		// If the menu has no icon associated with
		if (rootMenuImage == null) {
			return new MenuManager(rootMenuName, menuId);
		}
		return new MenuManager(rootMenuName, rootMenuImage, menuId);
	}

	/**
	 * Build a new sub-menu
	 * @param itemDescription Description of the new sub-menu to build
	 * @param rootMenu The parent of this sub-menu
	 * @return A menu manager that corresponds to the menu description
	 */
	public static MenuManager buildSubMenu(MenuManager rootMenu, IItemMenu itemDescription) {
		String menuId = itemDescription.getName().toLowerCase();

		// Deal with SubMenu
		if (itemDescription instanceof ISubMenu) {
			ISubMenu menuDescription = (ISubMenu) itemDescription;
			MenuManager item = new MenuManager(menuDescription.getName(), menuDescription.getIcon(), menuId);

			for (ISubMenu subMenu : menuDescription.getSubMenus()) {
				item.add(buildSubMenu(rootMenu, subMenu));
			}
			for (IServiceMenu service : menuDescription.getServiceMenus()) {
				item.add(buildServiceMenu(service, itemDescription.isVisible()));
			}
			for (IOptionMenu option : menuDescription.getOptions()) {
				item.add(buildOptionMenu(option, itemDescription.isVisible()));
			}
			return item;
		}

		// Deal with ServiceMenu
		if (itemDescription instanceof IServiceMenu) {
			rootMenu.add(buildServiceMenu((IServiceMenu) itemDescription, true));
			return null;
		}

		// Deal with OptionMenu
		if (itemDescription instanceof IOptionMenu) {
			rootMenu.add(buildOptionMenu((IOptionMenu) itemDescription, true));
			return null;
		}

		return null;
	}

	/**
	 * Build a service menu item
	 * @param service The description of the service to create
	 * @param parentState Is the parent enabled ?
	 * @return The action that corresponds to the service description
	 */
	private static IAction buildServiceMenu(IServiceMenu service, boolean parentState) {
		IAction item = new ServiceAction(service);
		item.setEnabled(parentState && service.isVisible());
		item.setImageDescriptor(service.getIcon());
		return item;
	}

	/**
	 * Build an option menu item
	 * @param option The description of the option to create
	 * @param active Is the parent enabled ?
	 * @return The action that corresponds to the option description
	 */
	private static IAction buildOptionMenu(IOptionMenu option, boolean active) {
		IAction item = new OptionAction(option);
		item.setEnabled(active && option.isVisible());
		return item;
	}
	
	/**
	 * Build a root menu from an item tree and an API description
	 * @param menu The menu
	 * @return A root menu that corresponds to the menu description
	 */
	public static MenuManager fromItemMenu(IItemMenu menu) {
		if (menu instanceof ISubMenu) {
			String id = menu.getName().toLowerCase().replaceAll("\\W", "-").replaceAll("[-]+", "-").trim();  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$//$NON-NLS-4$
			MenuManager root = new MenuManager(menu.getName(), menu.getIcon(), id);
			for (IItemMenu sub: ((ISubMenu) menu).getSubMenus()) {
				if (sub.isVisible()) {
					root.add(fromItemMenu(sub));
				}
			}
			for (IServiceMenu service: ((ISubMenu) menu).getServiceMenus()) {
				if (service.isVisible()) {
					IAction action = new ServiceAction(service);
					action.setEnabled(service.isVisible());
					action.setImageDescriptor(service.getIcon());
					root.add(action);
				}
			}
			for (IOptionMenu option: ((ISubMenu) menu).getOptions()) {
				if (option.isVisible()) {
					IAction action = new OptionAction(option);
					action.setEnabled(option.isVisible());
				}
			}
			return root;
		}
		throw new AssertionError();
	}
	
}
