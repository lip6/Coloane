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
package fr.lip6.move.coloane.interfaces.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Color;

/**
 * Graphical properties and behaviors of an attribute
 * @see IAttribute
 * 
 * @author Jean-Baptiste Voron
 */
public interface IAttributeGraphicInfo extends ILocationInfo {

	/**
	 * @return background color
	 */
	Color getBackground();

	/**
	 * @param background color
	 */
	void setBackground(Color background);

	/**
	 * @return foreground color
	 */
	Color getForeground();

	/**
	 * @param foreground color
	 */
	void setForeground(Color foreground);

	/**
	 * @return <code>true</code> if the attribute has to be displayed
	 */
	boolean isVisible();

	/**
	 * @return size of the attribute
	 */
	Dimension getSize();
}
