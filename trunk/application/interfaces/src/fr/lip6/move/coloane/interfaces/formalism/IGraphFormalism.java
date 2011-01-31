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

import java.util.Collection;

/**
 * Describe all properties for a graph.<br>
 * Basically, it contains nodes, arcs and attributes.<br>
 * Every formalism instances have, at least, one instance of this element.
 */
public interface IGraphFormalism extends IElementFormalism {

	/**
	 * @return all elements that are declared as members of this graph
	 */
	Collection<IElementFormalism> getAllElementFormalism();

	/**
	 * Get the description (formalism) of an object thanks to its name
	 * @param name The name of the object
	 * @return The formalism associated to this object
	 */
	IElementFormalism getElementFormalism(String name);
}
