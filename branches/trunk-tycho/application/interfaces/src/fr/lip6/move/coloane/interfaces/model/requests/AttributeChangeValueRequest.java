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

/**
 * Ask the core project to change the value of an attribute. 
 *
 * @author Jean-Baptiste Voron
 */
public class AttributeChangeValueRequest implements IRequest {

	/** The attribute */
	private IAttribute attribute;

	/** The new value */
	private String newValue;

	/**
	 * Constructor
	 * @param attribute The attribute
	 * @param newValue The new value that should be used for this attribute
	 */
	public AttributeChangeValueRequest(IAttribute attribute, String newValue) {
		this.attribute = attribute;
		this.newValue = newValue;
	}
	
	/**
	 * @return The attribute concerned by this request
	 */
	public IAttribute getAttribute() {
		return attribute;
	}

	/**
	 * @return The new (desired) value for the attribute
	 */
	public String getNewValue() {
		return newValue;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getRequestType() {
		return IRequest.ATTRIBUTE_CHANGEVALUE_REQUEST;
	}
}