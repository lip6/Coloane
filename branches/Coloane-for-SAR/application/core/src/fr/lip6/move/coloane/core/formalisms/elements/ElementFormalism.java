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

import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IComputedAttributeFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphicalDescription;

import java.util.ArrayList;
import java.util.List;

/**
 * This class describes a element of a formalism.<br>
 * A formalism is entirely built from those elements.<br>
 * An element contains all required information:
 * <ul>
 * 	<li>Name</li>
 * 	<li>A list of attributes</li>
 * 	<li>A set of graphical descriptions</li>
 * </ul>
 *
 * @author Jean-Baptiste Voron
 */

public class ElementFormalism implements IElementFormalism {
	/** Name */
	private String name;

	/** Formalism that contains and uses this element */
	private IFormalism formalism;

	/** Attributes list */
	private List<IAttributeFormalism> attributes = new ArrayList<IAttributeFormalism>();

	/** Computed Attributes list */
	private List<IComputedAttributeFormalism> computedAttributes = new ArrayList<IComputedAttributeFormalism>();

	/** Graphical description list */
	private List<IGraphicalDescription> graphicalDescriptions = new ArrayList<IGraphicalDescription>();

	/**
	 * Constructor
	 * @param name Name of this element (eg. place, transition, event...)
	 * @param formalism Formalism that contains and uses this element
	 */
	public ElementFormalism(String name, IFormalism formalism) {
		this.name = name;
		this.formalism = formalism;
	}

	/**
	 * Add a graphical description to the element
	 * @param graphicalDescription The additional graphical description to be considered
	 */
	public final void addGraphicalDescription(IGraphicalDescription graphicalDescription) {
		this.graphicalDescriptions.add(graphicalDescription);
	}

	/**
	 * Add a new attribute to this element
	 * @param attribute The additional attribute to be considered for this element
	 */
	public final void addAttribute(AttributeFormalism attribute) {
		this.attributes.add(attribute);
	}

	/**
	 * Add a new computed attribute to this element
	 * @param attribute The additional attribute to be considered for this element
	 */
	public final void addComputedAttribute(ComputedAttributeFormalism attribute) {
		this.computedAttributes.add(attribute);
	}

	/** {@inheritDoc} */
	@Override
	public final String getName() {	return name; }

	/** {@inheritDoc} */
	@Override
	public final List<IAttributeFormalism> getAttributes() { return this.attributes; }

	/** {@inheritDoc} */
	@Override
	public final List<IComputedAttributeFormalism> getComputedAttributes() { return this.computedAttributes; }

	/** {@inheritDoc} */
	@Override
	public final List<IGraphicalDescription> getAllGraphicalDescription() { return graphicalDescriptions; }

	/** {@inheritDoc} */
	@Override
	public final IGraphicalDescription getGraphicalDescription() { return graphicalDescriptions.get(0); }

	/** {@inheritDoc} */
	@Override
	public final IFormalism getFormalism() { return this.formalism;	}
}
