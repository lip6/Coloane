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
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphicalDescription;
import fr.lip6.move.coloane.interfaces.formalism.IReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	/** Graphical description list */
	private List<IGraphicalDescription> graphicalDescriptions = new ArrayList<IGraphicalDescription>();
	
	/** Reference list */
	private List<IReference> references = new ArrayList<IReference>();

	/**
	 * Constructor
	 * @param name Name of this element (eg. place, transition, event...)
	 * @param formalism Formalism that contains and uses this element
	 */
	public ElementFormalism(String name, IFormalism formalism) {
		this.name = name;
		this.formalism = formalism;
	}

	/** {@inheritDoc} */
	public final void addGraphicalDescription(IGraphicalDescription graphicalDescription) {
		this.graphicalDescriptions.add(graphicalDescription);
	}

	/** {@inheritDoc} */
	public final String getName() {	return name; }

	/** {@inheritDoc} */
	public final List<IAttributeFormalism> getAttributes() {
		Map<String, IAttributeFormalism> map = formalism.getAllAttributeFormalism(name);
		if (map == null) {
			return new ArrayList<IAttributeFormalism>();
		}
		return new ArrayList<IAttributeFormalism>(map.values());
	}

	/** {@inheritDoc} */
	public final List<IGraphicalDescription> getAllGraphicalDescription() { return graphicalDescriptions; }

	/** {@inheritDoc} */
	public final IGraphicalDescription getGraphicalDescription() {
		//because of the way graphical descriptions are separate from the formalism definition
		//we use a default, in case the user did not choose to define a graphic description
		if (graphicalDescriptions.isEmpty()) {
			graphicalDescriptions.add(GraphicalDescription.getDefault(name, this instanceof NodeFormalism));
		}
		return graphicalDescriptions.get(0);
	}

	/** {@inheritDoc} */
	public final IFormalism getFormalism() { return this.formalism;	}

	/** {@inheritDoc} */
	public final void addReference(String href, int minOccurs, int maxOccurs) {
		references.add(new Reference(href, minOccurs, maxOccurs));
	}

	/** {@inheritDoc} */
	public final List<IReference> getReferences() {
		return references;
	}

}
