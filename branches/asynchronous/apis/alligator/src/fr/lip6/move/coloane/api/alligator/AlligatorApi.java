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
package fr.lip6.move.coloane.api.alligator;

import fr.lip6.move.coloane.interfaces.api.AbstractApi;
import fr.lip6.move.coloane.interfaces.api.IApi;
import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;

import java.util.List;
import java.util.logging.Logger;

/**
 * Api extension to manage invocation of service from Alligator platform.
 * 
 * @author Clément Démoulins
 */
public class AlligatorApi extends AbstractApi implements IApi {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

	/**
	 * {@inheritDoc}
	 * 
	 * @see fr.lip6.move.coloane.interfaces.api.IApi#getInitialApiMenus()
	 */
	public final List<IItemMenu> getInitialApiMenus() {
		LOGGER.fine("Loading alligator menu");
		return Activator.getDefault().getMenus();
	}

}
