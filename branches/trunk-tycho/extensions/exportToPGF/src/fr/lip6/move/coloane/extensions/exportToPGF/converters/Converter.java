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
package fr.lip6.move.coloane.extensions.exportToPGF.converters;

import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.antlr.stringtemplate.StringTemplate;

/**
 * Interface for converter classes.
 * A converter is an object that reads an attribute and outputs its textual
 * representation adapted for TikZ.
 */
public interface Converter {

	/**
	 * Convert attribute to its textual representation for TikZ.
	 * @param attribute The attribute to convert.
	 * @return A textual representation of attribute, suited for TikZ.
	 */
	String convert(IAttribute attribute);

	/**
	* Insert graphical description data in query.
	 * @param query Query to use.
	 * @param node Node to extract graphical description.
	 */
	void setGraphicalDescription(StringTemplate query, INode node);

	/**
	 * @param attribute the attribute.
	 * @return mangled attribute name.
	 */
	String getName(IAttribute attribute);
}
