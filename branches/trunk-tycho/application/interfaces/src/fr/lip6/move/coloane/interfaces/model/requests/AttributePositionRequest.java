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

import fr.lip6.move.coloane.interfaces.model.IAttribute;

import org.eclipse.draw2d.geometry.Point;

/**
 * Users who want to move an attribute should specify the move using this request.<br>
 * This request will be executed by the core plugin.
 *
 * @author Jean-Baptiste Voron
 */
public class AttributePositionRequest implements IRequest {

	/** The considered attribute */
	private IAttribute attribute;
	
	/** New position */
	private Point newPosition;

	/**
	 * Constructor.<br>
	 * @param attribute The considered attribute
	 * @param newPosition The new position
	 */
	public AttributePositionRequest(IAttribute attribute, Point newPosition) {
		this.attribute = attribute;
		this.newPosition = newPosition;
	}

	/**
	 * @return The considered attribute
	 */
	public IAttribute getAttribute() {
		return attribute;
	}
	
	/**
	 * @return The new (<i>desired</i>) position
	 */
	public Point getNewPosition() {
		return newPosition;
	}

	public int getRequestType() {
		return IRequest.ATTRIBUTE_POSITION_REQUEST;
	}
}
