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
package fr.lip6.move.coloane.core.formalisms.elements;

import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Describe all properties for a graph.<br>
 * Basically, it contains nodes, arcs and attributes.<br>
 * Every formalism instances have, at least, one instance of this element.
 */
public class GraphFormalism extends ElementFormalism implements IGraphFormalism {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** All elements that can be contained inside such a graph */
	private Map<String,IElementFormalism> children = new HashMap<String,IElementFormalism>();

	/**
	 * Constructor
	 * @param name Graph name
	 * @param formalism The formalism (i.e. constraints and properties)
	 * @see IFormalism
	 */
	public GraphFormalism(String name, IFormalism formalism) {
		super(name, formalism);
	}

	/**
	 * Add an element to the graph definition
	 * @param element The element to add to the graph
	 */
	public final void addElement(ElementFormalism element) {
		if (element == null) { return; }
		LOGGER.finer("Add an element to the graph formalism : " + element.getName()); //$NON-NLS-1$
		this.children.put(element.getName(), element);
	}

	/** {@inheritDoc} */
	public final Collection<IElementFormalism> getAllElementFormalism() {
		return this.children.values();
	}

	/** {@inheritDoc} */
	public final IElementFormalism getElementFormalism(String name) {
		return children.get(name);
	}
}
