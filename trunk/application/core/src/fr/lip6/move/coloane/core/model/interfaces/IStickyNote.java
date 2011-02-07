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
package fr.lip6.move.coloane.core.model.interfaces;

import fr.lip6.move.coloane.interfaces.model.ILocationInfo;

import org.eclipse.draw2d.geometry.Dimension;

/**
 * Sticky note interface
 *
 * @author Clément Démoulins
 * @author Jean-Baptiste Voron
 */
public interface IStickyNote extends ILocationInfo, ILinkableElement {

	/** Event raised when the sticky note value is updated */
	String VALUE_PROP = "Sticky.ValueUpdate"; //$NON-NLS-1$

	/** Event raised when the sticky note is resized */
	String RESIZE_PROP = "Sticky.Resize"; //$NON-NLS-1$

	/**
	 * @return The sticky note value
	 */
	String getLabelContents();

	/**
	 * Set a new value for the sticky note
	 * @param newText The new value
	 */
	void setLabelContents(String newText);

	/**
	 * @return The sticky note size
	 */
	Dimension getSize();

	/**
	 * Set a new size for the sticky note
	 * @param size The new size
	 */
	void setSize(Dimension size);
}
