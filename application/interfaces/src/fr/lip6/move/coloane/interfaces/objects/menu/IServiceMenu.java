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

import fr.lip6.move.coloane.interfaces.api.services.IApiService;

/**
 * Define a menu that provides access to a service.
 *
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public interface IServiceMenu extends IItemMenu {

    /**
     * @return The service associated with the menu item
     */
	IApiService getAssociatedService();

	/**
	 * @return true if this service is global
	 */
	boolean isGlobal();
}
