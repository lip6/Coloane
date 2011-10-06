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

/**
 * Definition of formatting rules for an attribute
 */
public interface IAttributeFormatter {

	/**
	 * Apply formatting rules
	 * @param value The current attribute value. Usually sufficient
	 * @param parentElement Attribute environment. From this element, almost all graph model objects can be accessed
	 * @return the string to be displayed as the attribute value
	 */
	String applyFormat(String value, IElement parentElement);

}
