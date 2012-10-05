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

import fr.lip6.move.coloane.interfaces.model.IElement;

/**
 * Ask to reset all attribute positions of an {@link IElement} to their default positions
 * (i.e. relative to the node or arc they describe)
 *
 * @author Yann Thierry-Mieg, based on Jean-Baptiste Voron
 */
public class AttributesResetPositionRequest implements IRequest {

	/** The object that holds the attributes to reset */
	private IElement parentObject;

	/**
	 * Constructor
	 * @param parentObject The object that holds the attributes to reset
	 */
	public AttributesResetPositionRequest(IElement parentObject) {
		this.parentObject = parentObject;
	}

	/**
	 * @return The parent object that holds the attributes asked to be reseted
	 */
	public final IElement getParentObject() {
		return parentObject;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getRequestType() {
		return IRequest.ATTRIBUTE_RESET_POSITION_REQUEST;
	}
}
