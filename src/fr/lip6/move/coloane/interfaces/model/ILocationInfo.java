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

import org.eclipse.draw2d.geometry.Point;

/**
 * Define properties and behaviors of objects that can be moved and located on an editor.
 */
public interface ILocationInfo {

	/** Event raised when the element is moved */
	String LOCATION_PROP = "Location.info"; //$NON-NLS-1$

	/**
	 * @return location information of the current object
	 * @see ILocationInfo
	 */
	Point getLocation();

	/**
	 * Set a new position for this object
	 * @param newLocation a single point that designates the new location
	 */
	void setLocation(Point newLocation);

	/**
	 * Reset the location of the current element.<br>
	 * In fact, the location is set to (-1, -1). <br>
	 * The element EditPart is in charge of the new location computation.
	 */
	void resetLocation();
}
