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

import fr.lip6.move.coloane.core.ui.menus.MenuManipulation;
import fr.lip6.move.coloane.interfaces.api.IApi;
import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Display;

/**
 * Handle notifications coming from an API and dedicated to menu updates.
 *
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class MenuObserver implements PropertyChangeListener {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Session handled by this observer */
	private final MenuManager rootMenu;

	/**
	 * @param rootMenu Coloane root menu, the apis menus will be added as child of this menu.
	 */
	public MenuObserver(MenuManager rootMenu) {
		this.rootMenu = rootMenu;
	}

	/** {@inheritDoc} */
	@Override
	public final void propertyChange(PropertyChangeEvent evt) {
		Object newValue = evt.getNewValue();
		if (IApi.API_MENU.equals(evt.getPropertyName())) {
			LOGGER.info("Menu should be updated"); //$NON-NLS-1$
			this.rootMenu.removeAll();

			// Build sub-menus
			LOGGER.finer("Fetching new sub-menus"); //$NON-NLS-1$
			@SuppressWarnings("unchecked") // I can't check a type with generic
			List<IItemMenu> submenus = (List<IItemMenu>) newValue;
			for (IItemMenu submenu : submenus) {
				MenuManager newMenu = MenuManipulation.buildSubMenu(this.rootMenu, submenu);
				if (newMenu != null) {
					this.rootMenu.add(newMenu);
				}
			}
			this.rootMenu.markDirty();
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					rootMenu.updateAll(true);
				}
			});
		}
	}
}
