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

import fr.lip6.move.coloane.interfaces.model.IAttributeFormatter;

/**
 * This class represents all the formalism computed attribute characteristics.<br>
 * An attribute is like a property of a base element.<br>
 * Each base element has to maintain its own list of attributes.
 * <br>
 * Computed Attributes share some properties with Standard Attributes.<br>
 * Those common properties are detailed in Global Attributes definition.
 * 
 * @see IAttributeFormalism
 * @see IGlobalAttributeFormalism
 *
 * @author Jean-Baptiste Voron
 */
public interface IComputedAttributeFormalism extends IGlobalAttributeFormalism {
	
	/** 
	 * @return The class that is supposed to format the attribute value
	 */
	IAttributeFormatter getAttributeFormatter();
}
