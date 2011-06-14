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
package fr.lip6.move.coloane.interfaces.formalism;

import java.util.List;

/**
 * This class represents dedicated formalism (standard) attribute characteristics.<br>
 * An attribute is like a property of a base element.<br>
 * Each base element has to maintain its own list of attributes.<br>
 * <br>
 * Standard Attributes share some properties with Computed Attributes.<br>
 * Those common properties are detailed in Global Attributes definition.
 *
 * @see IComputedAttributeFormalism
 * @see IGlobalAttributeFormalism
 *
 * @author Jean-Baptiste Voron
 */
public interface IAttributeFormalism extends IGlobalAttributeFormalism {
	/** @return Multiline status */
	boolean isMultiLine();

	/**
	 * @return Is the attribute enumerated ?
	 * @see getEnumeration() for fetching authorized values
	 */
	boolean isEnumerated();

	/**
	 * @return the list of authorized values for the given attribute
	 */
	List<String> getEnumeration();
}
