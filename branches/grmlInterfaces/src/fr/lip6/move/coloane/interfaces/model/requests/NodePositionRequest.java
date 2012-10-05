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

import fr.lip6.move.coloane.interfaces.model.INode;

import org.eclipse.draw2d.geometry.Point;

/**
 * Ask the core project to change the position of an object ({@link IElement}).
 *
 * @author Jean-Baptiste Voron
 */
public class NodePositionRequest implements IRequest {
	/** Id of the object to move */
	private INode element;

	/** the new position for the object*/
	private Point newPosition;

	/**
	 * Constructor
	 * @param element The element to move
	 * @param newPosition The new position for this object
	 */
	public NodePositionRequest(INode element, Point newPosition) {
		this.element = element;
		this.newPosition = newPosition;
	}

	/**
	 * @return The element to move
	 */
	public final INode getElement() {
		return element;
	}

	/**
	 * @return The new position for the considered object
	 */
	public final Point getNewPosition() {
		return newPosition;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getRequestType() {
		return IRequest.NODE_POSITION_REQUEST;
	}
}
