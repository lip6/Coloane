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

/**
 * Ask the core project to remove an object.<br>
 *
 * @author Jean-Baptiste Voron
 */
public class NodeDeleteRequest implements IRequest {
	/** The element to remove from the model */
	private INode element;

	/**
	 * Constructor
	 * @param element The element to remove from the model
	 */
	public NodeDeleteRequest(INode element) {
		this.element = element;
	}

	/**
	 * @return the element to remove from the model
	 */
	public INode getElement() {
		return element;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getRequestType() {
		return IRequest.NODE_DELETE_REQUEST;
	}
}
