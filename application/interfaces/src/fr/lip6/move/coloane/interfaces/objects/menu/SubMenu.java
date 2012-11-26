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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Define a sub-menu.<br>
 *
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class SubMenu extends ItemMenu implements ISubMenu {
	/** A list of sub-menus */
	private List<ISubMenu> submenus;

	/** A list of service items (associated with this sub-menu) */
	private List<IServiceMenu> services;

	/** A list of option items (associated with this sub-menu) */
	private List<IOptionMenu> options;

	/**
	 * Constructor
	 * @param name The name of the sub-menu
	 * @param visible The visible state of the sub-menu (and all of its children)
	 */
	public SubMenu(String name, boolean visible) {
		super(name, visible, null);
		this.services = new ArrayList<IServiceMenu>();
		this.options = new ArrayList<IOptionMenu>();
		this.submenus = new ArrayList<ISubMenu>();
	}

	/**
	 * Constructor
	 * @param name The name of the sub-menu
	 * @param visible The visible state of the sub-menu (and all of its children)
	 * @param menuIcon The icon for the sub-menu
	 */
	public SubMenu(String name, boolean visible, ImageDescriptor menuIcon) {
		super(name, visible, null, menuIcon);
		this.services = new ArrayList<IServiceMenu>();
		this.options = new ArrayList<IOptionMenu>();
		this.submenus = new ArrayList<ISubMenu>();
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addOptionMenu(IOptionMenu option) {
		this.options.add(option);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addSubMenu(ISubMenu submenu) {
		this.submenus.add(submenu);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addServiceMenu(IServiceMenu service) {
		this.services.add(service);
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<IServiceMenu> getServiceMenus() {
		return this.services;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<IOptionMenu> getOptions() {
		return this.options;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<ISubMenu> getSubMenus() {
		return this.submenus;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ISubMenu getSubMenu(String name) {
		for (ISubMenu submenu: submenus) {
			if (submenu.getName().equals(name)) {
				return submenu;
			}
		}
		return null;
	}
	
	@Override
	public final String toString() {
		String result = getName() + " {";
		for (IItemMenu submenu: getSubMenus()) {
			result = result + submenu.toString();
		}
		for (IItemMenu submenu: getServiceMenus()) {
			result = result + submenu.toString() + "\n";
		}
		result = result + "}";
		return result;
	}
	
}
