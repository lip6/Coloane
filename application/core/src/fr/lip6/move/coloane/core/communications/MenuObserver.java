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
package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.core.ui.menus.ColoaneAPIRootMenu;
import fr.lip6.move.coloane.core.ui.menus.ColoaneMenuManager;
import fr.lip6.move.coloane.core.ui.menus.MenuManipulation;
import fr.lip6.move.coloane.interfaces.api.IApi;
import fr.lip6.move.coloane.interfaces.api.IApiObserver;
import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;

import java.util.List;
import java.util.logging.Logger;

/**
 * Handle notifications coming from an API and dedicated to menu updates.
 *
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class MenuObserver implements IApiObserver {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	/** Session handled by this observer */
	private ColoaneAPIRootMenu rootMenu;
	
	public MenuObserver(ColoaneAPIRootMenu rootMenu) {
		this.rootMenu = rootMenu;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void update(IApi api, Object newMenus) {
		LOGGER.warning("Menu should be updated"); //$NON-NLS-1$
		this.rootMenu.removeAll();

		// Build sub-menus
		LOGGER.finer("Fetching new sub-menus"); //$NON-NLS-1$
		List<IItemMenu> submenus = (List<IItemMenu>) newMenus;
		for (IItemMenu submenu : submenus) {
			ColoaneMenuManager newMenu = MenuManipulation.buildSubMenu(this.rootMenu, submenu);
			if (newMenu != null) {
				this.rootMenu.add(newMenu);
			}
		}
	}
}
