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
package fr.lip6.move.coloane.interfaces.formalism;

import org.eclipse.draw2d.geometry.Point;

public interface IGlobalAttributeFormalism {

	/** @return Attribute name */
	String getName();

	/**
	 * @return the default value
	 */
	String getDefaultValue();
	
	/**
	 * @return Drawable status (should the attribute be displayed ?)
	 * */
	boolean isDrawable();

	/**
	 * @return <code>true</code> if the attribute has to be displayed even if its valued is the default one
	 */
	boolean isDefaultValueDrawable();

	/**
	 * @return <code>true</code> if the attribute has to be displayed with a bold font
	 */
	boolean isBold();

	/**
	 * @return <code>true</code> if the attribute has to be displayed with an italic font
	 */
	boolean isItalic();

	/**
	 * @return the font size
	 */
	Integer getFontSize();

	/** 
	 * <b>Delta Location</b> is used to specify the relative position of the attribute according to the parent element.<br>
	 * @return the delta location
	 */
	Point getDeltaLocation();
}
