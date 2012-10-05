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

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * 
 */
public class ColoaneAPIRootMenu extends MenuManager {

	/**
	 * Constructor
	 * @param rootMenuName The name of the menu
	 * @param rootMenuId The Id of the menu
	 */
	ColoaneAPIRootMenu(String rootMenuName, String rootMenuId) {
		super(rootMenuName, rootMenuId);
	}

	/**
	 * Constructor
	 * @param rootMenuName The name of the menu
	 * @param rootMenuIcon An icon associated with the menu
	 * @param rootMenuId The Id of the menu
	 */
	ColoaneAPIRootMenu(String rootMenuName, ImageDescriptor rootMenuIcon, String rootMenuId) {
		super(rootMenuName, rootMenuIcon, rootMenuId);
	}
}
