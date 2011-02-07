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

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Define a menu item.<br>
 * An item describes something (almost every elements) in a menu
 *
 * @author Jean-Baptiste Voron
 */
public interface IItemMenu {

	/**
	 * @return The item name
	 */
	String getName();

	/**
     * @return The visible state of the item
     */
	boolean isVisible();

	/**
	 * @return An icon associated with the menu item
	 */
	ImageDescriptor getIcon();

	/**
     * @return An help message (a tip) associated with the item
     */
	String getHelps();
}
