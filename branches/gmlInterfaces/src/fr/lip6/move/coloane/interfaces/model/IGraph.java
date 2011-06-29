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
package fr.lip6.move.coloane.interfaces.model;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;

import java.util.Collection;

/**
 * Define a graph object.<br>
 */
public interface IGraph extends IElement {

	/** Event raised when a node is added to the graph */
	String NODE_ADDED_PROP = "Model.AddingNode"; //$NON-NLS-1$

	/** Event raised when a node is removed from the graph */
	String NODE_REMOVED_PROP = "Model.RemovingNode"; //$NON-NLS-1$

	/** Event raised when an arc is added to the graph */
	String ARC_ADDED_PROP = "Model.AddingArc"; //$NON-NLS-1$

	/** Event raised when an arc is removed from the graph */
	String ARC_REMOVED_PROP = "Model.RemovingArc"; //$NON-NLS-1$

	/** Property when an attribute is added to the graph */
	String ATTRIBUTE_ADDED_PROP = "Model.AddingAttribute"; //$NON-NLS-1$

	/**
	 * Create a node and add it to the graph
	 * @param nodeFormalism Formalism used by this node
	 * @return The new node
	 * @throws ModelException If the type is not correct according to the graph formalism
	 */
	INode createNode(INodeFormalism nodeFormalism) throws ModelException;
	
	/**
	 * Create an attribute and add it to the reference
	 * @param reference
	 * @param elementFormalism
	 * @param name
	 * @return
	 * @throws ModelException
	 */
	IAttribute createAttribute(IElement reference, IElementFormalism elementFormalism, String name) throws ModelException;
	
	/**
	 * Create an attribute and add it to the reference
	 * @param reference
	 * @param parent
	 * @param attributeFormalism
	 * @param name
	 * @return
	 * @throws ModelException
	 */
	IAttribute createAttribute(IElement reference, IAttribute parent, IAttributeFormalism attributeFormalism, String name) throws ModelException;

	/**
	 * Create a node and add it to the graph
	 * @param nodeFormalism Formalism used by this node
	 * @param id Node ID
	 * @return The new node
	 * @throws ModelException If the type is <code>null</code> or if it is not correct according to the graph formalism
	 */
	INode createNode(INodeFormalism nodeFormalism, int id) throws ModelException;

	/**
	 * Delete a node.<br>
	 * Incoming arcs, outgoing arcs and sticky links will be deleted too.
	 * @param node The node that has to be removed from the graph
	 */
	void deleteNode(INode node);

	/**
	 * Delete a not
	 * @param id The id of the node to remove from the graph
	 */
	void deleteNode(int id);

	/**
	 * Fetch a node in the graph
	 * @param id The id of the node to find
	 * @return The node or <code>null</code> if it has not been found
	 */
	INode getNode(int id);

	/**
	 * @return All graph nodes
	 */
	Collection<INode> getNodes();

	/**
	 * Add an existing node to the graph
	 * @param node The node to add to the graph
	 */
	void addNode(INode node);
	
	/**
	 * Add an existing attribute to the graph
	 * @param attr the attribute to add
	 * @param parent the attribute parent of this attribute
	 */
	void addAttribute(IAttribute attr, IAttribute parent);
	
	/**
	 * Add an existing attribute to the graph
	 * @param attr the attribute to add
	 * @param parent the attribute parent of this attribute
	 */
	void addAttribute(IAttribute attr, IElement parent);

	/**
	 * Create an arc that links a source and a target
	 * @param arcFormalism The formalism used by this arc
	 * @param source The source node
	 * @param target The target node
	 * @return The created arc
	 * @throws ModelException if one of the type of arc is not correct or if either the source or the target does not exist
	 */
	IArc createArc(IArcFormalism arcFormalism, INode source, INode target) throws ModelException;

	/**
	 * Create an arc that links a source and a target
	 * @param arcFormalism The formalism used by this arc
	 * @param source The source node
	 * @param target The target node
	 * @param id The ID of the new arc
	 * @return The created arc
	 * @throws ModelException if one of the arc formalism is not correct or if either the source or the target does not exist
	 */
	IArc createArc(IArcFormalism arcFormalism, INode source, INode target, int id) throws ModelException;

	/**
	 * Remove an arc from the graph.<br>
	 * All <b>sticky links</b> attached with this arc will be removed too !
	 * @param arc The arc to remove from the graph
	 */
	void deleteArc(IArc arc);

	/**
	 * Remove an arc from the graph
	 * @param id The ID of the arc to remove
	 */
	void deleteArc(int id);

	/**
	 * Fetch an arc
	 * @param id The ID of the arc to find
	 * @return The arc or <code>null</code> if it has not been found
	 */
	IArc getArc(int id);

	/**
	 * @return All graph arcs
	 */
	Collection<IArc> getArcs();

	/**
	 * Add an existing arc
	 * @param arc The arc to add to the graph
	 */
	void addArc(IArc arc);

	/**
	 * Fetch an object from the graph
	 * @param id The ID of the object to find
	 * @return The object as a {@link IElement}
	 */
	IElement getObject(int id);

	/**
	 * Remove an object identified by its ID<br>
	 * If no object has this ID, nothing is done.
	 * @param id The ID of the object to remove
	 * @throws ModelException If something went wrong
	 */
	void deleteObject(int id) throws ModelException;

	/**
	 * Add all graph element into the current graph
	 * @param graph The graph from which all elements have to be copied into the current graph
	 */
	void addGraph(IGraph graph);

	/**
	 * @return The graph formalism
	 */
	IFormalism getFormalism();

	/**
	 * @return The date of last modification
	 */
	int getDate();
}
