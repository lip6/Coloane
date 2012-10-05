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

import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.Collection;
import java.util.Map;

/**
 * Describe a formalism.<br>
 * Here, the formalism is used to describe all nodes, arcs and attributes that can be used in a model.<br>
 * Some constraints can also be defined :
 * <ul>
 * 	<li>constraints between nodes</li>
 * 	<li>constraints on node actions</li>
 * </ul>
 */
public interface IFormalism  extends IElementFormalism {

	/**
	 * Tells if the arc creation is allowed or not
	 * @param source The source node
	 * @param target The target node
	 * @param arcFormalism The arc formalism
	 * @return <code>true</code> if the creation is allowed
	 */
	boolean isLinkAllowed(INode source, INode target, IArcFormalism arcFormalism);

	/**
	 * Tells if the action is allowed on this particular node
	 * @param node The node
	 * @return <code>true</code> if the action is allowed
	 */
	boolean isActionAllowed(INode node);

	/**
	 * @return The formalism ID
	 */
	String getId();

	/**
	 * @return The formalism name
	 */
	String getName();

	/**
	 * @return The image associated to this formalism
	 */
	String getImageName();

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

	/**
	 * Get the description (formalism) of an attribute using its name and
	 * the name of the element it references
	 * @param name The attribute's name
	 * @param reference The name of the referenced element
	 * @return The attribute's formalism
	 */
	IAttributeFormalism getAttributeFormalism(String name, String reference);
	
	/**
	 * Get the description (formalism) of all an element's attributes
	 * @param reference The name of the element
	 * @return The map of attribute formalisms mapped by name
	 */
	Map<String, IAttributeFormalism> getAllAttributeFormalism(String reference);
	
	/**
	 * Add an attribute's formalism to the formalism.
	 * @param attribute The attribute to be added.
	 */
	void addAttribute(IAttributeFormalism attribute);
	
	/**
	 * Set whether the formalism is abstract
	 * @param isabstract whether the formalism is abstract
	 */
	void setAbstract(boolean isabstract);
	
	/**
	 * @return Whether the formalism is abstract or not
	 */
	boolean isAbstract();

	/** @return The formalism's url */
	String getHref();

}
