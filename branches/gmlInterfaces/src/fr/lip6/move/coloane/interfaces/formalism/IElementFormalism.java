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
public interface IElementFormalism {

	/** @return The name of the element */
	String getName();

	/** @return The list of {@link AttributeFormalism} attached to this element. */
	List<IAttributeFormalism> getAttributes();

	/**
	 * Add a graphical description to the element
	 * @param graphicalDescription The additional graphical description to be considered
	 */
	void addGraphicalDescription(IGraphicalDescription graphicalDescription);
	
	/**
	 * @return The default graphical description of the element
	 * @see {@link #getAllGraphicalDescription()} to get a full list of graphical description for this element.
	 */
	IGraphicalDescription getGraphicalDescription();

	/**
	 * @return The list of all associated graphical descriptions for the element
	 * @see {@link #getGraphicalDescription()} to get the default representation for this element.
	 */
	List<IGraphicalDescription> getAllGraphicalDescription();

	/** @return The formalism that contains and uses this element */
	IFormalism getFormalism();
	
	/** 
	 * @param href The reference (as a url) to be added to this element's formalism
	 * @param minOccurs The minimum number of occurences of the reference
	 * @param maxOccurs The maximum number of occurences of the reference
	 */
	void addReference(String href, int minOccurs, int maxOccurs);
	
	/** @return This element's legal references */
	List<IReference> getReferences();
}
