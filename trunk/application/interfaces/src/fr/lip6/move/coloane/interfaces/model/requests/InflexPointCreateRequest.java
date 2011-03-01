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
package fr.lip6.move.coloane.interfaces.model.requests;

import fr.lip6.move.coloane.interfaces.model.IArc;

import org.eclipse.draw2d.geometry.Point;

/**
 * Ask the core project to create a new inflex point on an arc
 *
 * @author Jean-Baptiste Voron
 */
public class InflexPointCreateRequest implements IRequest {
	/** The arc to which the new inflex point will be added */
	private IArc arc;

	/** The position of the new inflex point */
	private Point position;

	/** The index of the new inflex point */
	private int index;

	/**
	 * Constructor
	 * @param arc The arc where to put this new inflex point
	 * @param position The position of the point
	 * @param index The index of this inflex point into the list of existing inflex points
	 */
	public InflexPointCreateRequest(IArc arc, Point position, int index) {
		this.arc = arc;
		this.position = position;
		this.index = index;
	}

	/**
	 * Constructor
	 * @param arc The arc where to put this new inflex point
	 * @param position The position of the point
	 */
	public InflexPointCreateRequest(IArc arc, Point position) {
		this(arc, position, arc.getInflexPoints().size() - 1);
	}

	/**
	 * The arc to which the new inflex point will be added
	 * @return the arc
	 */
	public final IArc getArc() {
		return arc;
	}

	/**
	 * @return the index of the new inflex point into the list of already existing point for this arc
	 */
	public final int getIndex() {
		return index;
	}

	/**
	 * @return the position of the new inflex point
	 */
	public final Point getPosition() {
		return position;
	}

	/** {@inheritDoc} */
	public final int getRequestType() {
		return IRequest.INFLEXPOINT_CREATE_REQUEST;
	}
}
