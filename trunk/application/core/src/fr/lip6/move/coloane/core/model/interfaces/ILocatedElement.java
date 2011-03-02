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

import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;

/**
 * Interface that describes the way to manipulate objects located on the editor.<br>
 * These objects must propose :
 * <ul>
 * 	<li>A way to fetch their location (coordinates) {@link ILocationInfo}</li>
 * 	<li>A way to handle vertical and horizontal guides {@link EditorGuide}</li>
 * </ul>
 * @see {@link NodeModel}, {@link AttributeModel}
 *
 * @author Jean-Baptiste Voron
 */
public interface ILocatedElement {
	/**
	 * Fetch the current position of the object.
	 * @return ILocationInfo
	 */
	ILocationInfo getLocationInfo();

	/**
	 * Return the objects's vertical/horizontal guide.
	 * @see EditorGuide.#HORIZONTAL_ORIENTATION
	 * @see EditorGuide.#VERTICAL_ORIENTATION
	 * @param orientation vertical or horizontal
	 * @return the guide to which this object is attached
	 */
	EditorGuide getGuide(int orientation);

	/**
	 * Set a new guide (horizontal or vertical) to this object
	 * @param guide The new guide
	 */
	void setGuide(EditorGuide guide);

	/**
	 * Remove (detach) a guide from this object.
	 * 
	 * @param orientation The guide orientation to remove
	 */
	void removeGuide(int orientation);
}
