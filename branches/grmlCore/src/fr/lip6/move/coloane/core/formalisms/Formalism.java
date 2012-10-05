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
package fr.lip6.move.coloane.core.formalisms;

import fr.lip6.move.coloane.core.formalisms.elements.ElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphicalDescription;
import fr.lip6.move.coloane.interfaces.formalism.IReference;
import fr.lip6.move.coloane.interfaces.formalism.constraints.IConstraintLink;
import fr.lip6.move.coloane.interfaces.formalism.constraints.IConstraintNode;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Define a formalism.<br>
 */
public class Formalism implements IFormalism {
	//this does not extend ElementFormalism because that class requires that a
	//formalism be given as constructor argument, which is not possible here.
	
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Formalism ID */
	private String id;

	/** Formalism's url */
	private String href;
	
	/** Formalism Name */
	private String name;

	/** Is abstract or not */
	private boolean isabstract;

	/** Formalism constraints about relations between objects */
	private List<IConstraintLink> linkconstraints;

	/** Formalism constraints about nodes */
	private List<IConstraintNode> nodeconstraints;
	
	/** Formalisms included in this formalism. Order matters. */
	private List<IFormalism> includedFormalisms;

	/** All elements that can be contained inside a formalism */
	private Map<String, IElementFormalism> children = new HashMap<String, IElementFormalism>();

	/** All attributes that can be contained inside a formalism
	 * sorted so that the element referenced gives access to a map
	 * where attributes are sorted by name. attributes with no reference
	 * are referred by an empty string */
	private Map<String, Map<String, IAttributeFormalism>> attributes = new HashMap<String, Map<String, IAttributeFormalism>>();

	/** Image that represents the formalism */
	private String image;

	/** Graphical description list */
	private List<IGraphicalDescription> graphicalDescriptions = new ArrayList<IGraphicalDescription>();
	
	/**
	 * Constructor
	 *
	 * @param id Formalism ID
	 * @param name Formalism Name
	 * @param href Formalism's url
	 * @param image Image that describes the formalism
	 */
	public Formalism(String id, String name, String href, String image) {
		this.id = id;
		this.name = name;
		this.href = href;
		this.image = image;

		this.linkconstraints = new ArrayList<IConstraintLink>();
		this.nodeconstraints = new ArrayList<IConstraintNode>();
		this.includedFormalisms = new ArrayList<IFormalism>();
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isLinkAllowed(INode source, INode target, IArcFormalism arcFormalism) {
		// Try to find a constraint for these two kinds of nodes
		for (IConstraintLink constraint : linkconstraints) {
			if (!constraint.isSatisfied(source, target, arcFormalism)) {
				return false;
			}
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isActionAllowed(INode node) {
		// Try to find a constraint for this kind of node
		for (IConstraintNode constraint : nodeconstraints) {
			if (!constraint.isSatisfied(node)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Add a link constraint to the formalism
	 * @param constraint The constraint between nodes
	 * @see {@link IConstraintLink}
	 * @see {@link IConstraint}
	 */
	public final void addConstraintLink(IConstraintLink constraint) {
		if (constraint == null) { return; }
		this.linkconstraints.add(constraint);
	}

	/**
	 * Add a node constraint to the formalism
	 * @param constraint The constraint for a node
	 * @see {@link IConstraintNode}
	 * @see {@link IConstraint}
	 */
	public final void addConstraintNode(IConstraintNode constraint) {
		if (constraint == null) { return; }
		this.nodeconstraints.add(constraint);
	}

	/** {@inheritDoc} */
	@Override
	public final String getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public final String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public final String getImageName() {
		return "/" + image; //$NON-NLS-1$
	}
	
	/**
	 * Add a formalism to the list of inclusions in final position
	 * @param form The formalism to be added
	 */
	public final void addFormalism(IFormalism form) {
		this.includedFormalisms.add(form);
	}

	/** {@inheritDoc} */
	@Override
	public final String toString() {
		return getName();
	}
	
	/**
	 * Add an element to the formalism definition
	 * @param element The element to add to the formalism
	 */
	public final void addElement(ElementFormalism element) {
		if (element == null) {
			return;
		}
		LOGGER.finer("Add an element to the formalism : " + element.getName()); //$NON-NLS-1$
		this.children.put(element.getName(), element);
	}

	/** {@inheritDoc} */
	@Override
	public final Collection<IElementFormalism> getAllElementFormalism() {
		return this.children.values();
	}

	/** {@inheritDoc} */
	@Override
	public final IElementFormalism getElementFormalism(String name) {
		return children.get(name);
	}
	
	/** {@inheritDoc} */
	@Override
	public final IAttributeFormalism getAttributeFormalism(String name, String reference) {
		Map<String, IAttributeFormalism> map = attributes.get(reference);
		if (map != null) {
			return map.get(name);
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public final Map<String, IAttributeFormalism> getAllAttributeFormalism(String reference) {
		return attributes.get(reference);
	}

	/** {@inheritDoc} */
	@Override
	public final void addAttribute(IAttributeFormalism attribute) {
		Map<String, IAttributeFormalism> map = attributes.get(attribute.getReference());
		if (map == null) {
			map = new HashMap<String, IAttributeFormalism>();
			attributes.put(attribute.getReference(), map);
		}
		map.put(attribute.getName(), attribute);
	}

	/** {@inheritDoc} */
	@Override
	public final List<IAttributeFormalism> getAttributes() {
		Map<String, IAttributeFormalism> map = attributes.get(name);
		if (map == null) {
			return new ArrayList<IAttributeFormalism>();
		}
		return new ArrayList<IAttributeFormalism>(map.values());
	}
 
	/** {@inheritDoc} */
	@Override
	public final List<IGraphicalDescription> getAllGraphicalDescription() { return graphicalDescriptions; }

	/** {@inheritDoc} */
	@Override
	public final IGraphicalDescription getGraphicalDescription() { return graphicalDescriptions.get(0); }

	/** {@inheritDoc} */
	@Override
	public final void addGraphicalDescription(IGraphicalDescription graphicalDescription) {
		this.graphicalDescriptions.add(graphicalDescription);
	}

	/** {@inheritDoc} */
	@Override
	public final IFormalism getFormalism() { return this; }
	
	/** {@inheritDoc} */
	@Override
	public final void setAbstract(boolean isabstract) {
		this.isabstract = isabstract;
	}
	
	/** {@inheritDoc} */
	@Override
	public final boolean isAbstract() {
		return isabstract;
	}

	/** {@inheritDoc} */
	@Override
	public final void addReference(String href, int minOccurs, int maxOccurs) {
		//a formalism does not have references
	}

	/** {@inheritDoc} */
	@Override
	public final List<IReference> getReferences() {
		//a formalism does not have references
		return new ArrayList<IReference>();
	}
	
	/** {@inheritDoc} */
	@Override
	public final String getHref() {
		return href;
	}
	
	/** @param name The formalism's name */
	public final void setName(String name) {
		this.name = name;
	}

}
