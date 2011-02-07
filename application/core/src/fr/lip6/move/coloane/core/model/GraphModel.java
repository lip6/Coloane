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
package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ICoreGraph;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Graph Model Object.<br>
 * This object is a container of all other objects (node, arcs, sticky notes...).
 * Moreover, it also contains attributes.
 *
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class GraphModel extends AbstractElement implements IGraph, ICoreGraph {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Main formalism used by this graph */
	private IFormalism formalism;

	/** Nodes list (ordered by ID) */
	private Map<Integer, INode>	nodes = new HashMap<Integer, INode>();

	/** Arcs list (ordered by ID) */
	private Map<Integer, IArc> arcs = new HashMap<Integer, IArc>();

	/** Sticky Notes list */
	private List<IStickyNote> stickys = new ArrayList<IStickyNote>();

	/** Local counter used to compute ID for new objects */
	private int idCounter = 2;

	/** Last modification */
	private int date = (int) System.currentTimeMillis();

	/** FrameKit status (<code>true</code> -> dirty state... needs to be updated) */
	private boolean dirty = false;

	/** Graphical properties associated to this graph */
	private GraphEditorProperties editorProperties = null;

	/**
	 * Build a new graph according to a formalism
	 * @param formalism Formalism used to create the new graph model
	 * @throws IllegalArgumentException If no such formalism exists in FormalismManager list.
	 */
	public GraphModel(IFormalism formalism) throws IllegalArgumentException {
		super(1, null, formalism.getRootGraph().getAttributes() , formalism.getRootGraph().getComputedAttributes());
		LOGGER.fine("Build a graph model using formalism:" + formalism.getName() + ""); //$NON-NLS-1$ //$NON-NLS-2$
		this.formalism = formalism;

		// Create graphical properties
		this.editorProperties = new GraphEditorProperties();

		this.addPropertyChangeListener(this);
	}

	/**
	 * @return A new and unique identifier.
	 */
	private int getNewId() {
		int proposal = idCounter + 1;
		while ((nodes.get(proposal) != null) || (arcs.get(proposal) != null)) {
			proposal++;
		}
		idCounter = proposal;
		return idCounter;
	}

	/** {@inheritDoc} */
	public final INode createNode(INodeFormalism nodeFormalism) throws ModelException {
		if (nodeFormalism == null) {
			throw new ModelException("The formalism does not correctly define the created node type"); //$NON-NLS-1$
		}
		return createNode(nodeFormalism, getNewId());
	}

	/** {@inheritDoc} */
	public final INode createNode(INodeFormalism nodeFormalism, int id) throws ModelException {
		if (nodeFormalism == null) {
			throw new ModelException("The formalism does not correctly define the created node type"); //$NON-NLS-1$
		}
		LOGGER.fine("Build a new node: " + nodeFormalism.getName()); //$NON-NLS-1$
		INode node = new NodeModel(this, nodeFormalism, id);
		addNode(node);
		return node;
	}

	/** {@inheritDoc} */
	public final void deleteNode(INode node) {
		if (nodes.remove(node.getId()) != null) {
			LOGGER.fine("Remove node #" + node.getId()); //$NON-NLS-1$
			for (IArc arc : node.getOutgoingArcs()) {
				arcs.remove(arc.getId());
			}
			for (IArc arc : node.getIncomingArcs()) {
				arcs.remove(arc.getId());
			}
			((NodeModel) node).deleteArcsLinks();
			firePropertyChange(NODE_REMOVED_PROP, null, node);
			node.removePropertyChangeListener(this);
		}
	}

	/** {@inheritDoc} */
	public final void deleteNode(int id) {
		INode node = nodes.get(id);
		if (node != null) {
			deleteNode(node);
		}
	}

	/** {@inheritDoc} */
	public final INode getNode(int id) {
		return nodes.get(id);
	}

	/** {@inheritDoc} */
	public final Collection<INode> getNodes() {
		return nodes.values();
	}

	/** {@inheritDoc} */
	public final List<IStickyNote> getStickyNotes() {
		return Collections.unmodifiableList(stickys);
	}

	/** {@inheritDoc} */
	public final void addNode(INode node) {
		if (arcs.containsKey(node.getId()) || nodes.containsKey(node.getId())) {
			LOGGER.warning("This node already exists"); //$NON-NLS-1$
		} else {
			nodes.put(node.getId(), node);
			node.addPropertyChangeListener(this);
			LOGGER.fine("Add an existing  node #" + node.getId()); //$NON-NLS-1$
			firePropertyChange(NODE_ADDED_PROP, null, node);
		}
	}

	/** {@inheritDoc} */
	public final IStickyNote createStickyNote() {
		LOGGER.fine("Create a new sticky note"); //$NON-NLS-1$
		IStickyNote note = new StickyNoteModel();
		addSticky(note);

		return note;
	}

	/** {@inheritDoc} */
	public final void addSticky(IStickyNote sticky) {
		stickys.add(sticky);
		LOGGER.finer("Add a sticky note to location " + sticky.getLocation()); //$NON-NLS-1$
		firePropertyChange(STICKY_ADD_PROP, null, sticky);
	}

	/** {@inheritDoc} */
	public final boolean deleteSticky(IStickyNote note) {
		boolean delete = stickys.remove(note);
		if (delete) {
			LOGGER.finest("Remove a sticky note"); //$NON-NLS-1$
			firePropertyChange(STICKY_REMOVED_PROP, null, note);
		}
		return delete;
	}

	/** {@inheritDoc} */
	public final IArc createArc(IArcFormalism arcFormalism, INode source, INode target) throws ModelException {
		if (arcFormalism == null) {
			throw new ModelException("This formalism does not define an arc type: " + arcFormalism); //$NON-NLS-1$
		}
		return this.createArc((IArcFormalism) arcFormalism, source, target, getNewId());
	}

	/** {@inheritDoc} */
	public final IArc createArc(IArcFormalism arcFormalism, INode source, INode target, int id) throws ModelException {
		LOGGER.fine("Build a new arc: " + arcFormalism.getName()); //$NON-NLS-1$
		if (!nodes.containsKey(source.getId()) || !nodes.containsKey(target.getId())) {
			throw new ModelException("Either the source or the target does not exist"); //$NON-NLS-1$
		}

		IArc arc = new ArcModel(this, arcFormalism, id, source, target);
		addArc(arc);

		return arc;
	}

	/** {@inheritDoc} */
	public final void deleteArc(IArc arc) {
		if (arcs.remove(arc.getId()) != null) {
			LOGGER.fine("Remove the arc #" + arc.getId()); //$NON-NLS-1$
			((NodeModel) arc.getSource()).removeOutgoingArc(arc);
			((NodeModel) arc.getTarget()).removeIncomingArc(arc);
			firePropertyChange(ARC_REMOVED_PROP, null, arc);
			arc.removePropertyChangeListener(this);
			((ArcModel) arc).delete();
		}
	}

	/** {@inheritDoc} */
	public final void deleteArc(int id) {
		IArc arc = arcs.get(id);
		if (arc != null) {
			deleteArc(arc);
		}
	}

	/** {@inheritDoc} */
	public final IArc getArc(int id) {
		return arcs.get(id);
	}

	/** {@inheritDoc} */
	public final Collection<IArc> getArcs() {
		return arcs.values();
	}

	/** {@inheritDoc} */
	public final void addArc(IArc arc) {
		if (arcs.containsKey(arc.getId()) || nodes.containsKey(arc.getId())) {
			LOGGER.warning("This object ID already exists"); //$NON-NLS-1$
		} else if (!nodes.containsKey(arc.getSource().getId()) || !nodes.containsKey(arc.getTarget().getId())) {
			LOGGER.warning("Either the source or the target of this arc does not exist"); //$NON-NLS-1$
		} else if (!formalism.isLinkAllowed(arc.getSource(), arc.getTarget(), arc.getArcFormalism())) {
			LOGGER.warning("This arc is not allowed by the formalism"); //$NON-NLS-1$
		} else {
			LOGGER.finest("Add an arc #" + arc.getId()); //$NON-NLS-1$
			arcs.put(arc.getId(), arc);
			((NodeModel) arc.getSource()).addOutgoingArc(arc);
			((NodeModel) arc.getTarget()).addIncomingArc(arc);
			arc.addPropertyChangeListener(this);
			firePropertyChange(ARC_ADDED_PROP, null, arc);
		}
	}

	/** {@inheritDoc} */
	public final IElement getObject(int id) {
		IElement obj = this.getNode(id);
		if (obj == null) {
			obj = this.getArc(id);
		}
		return obj;
	}

	/** {@inheritDoc} */
	public final void deleteObject(int id) throws ModelException {
		INode node = this.getNode(id);
		if (node != null) { this.deleteNode(node); return; }
		IArc arc = this.getArc(id);
		if (arc != null) { this.deleteArc(arc); return; }
		LOGGER.warning("The object #" + id + " does not exist... No object ware removed"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/** {@inheritDoc} */
	public final IFormalism getFormalism() {
		return formalism;
	}

	/**
	 * Refresh the last update date
	 */
	final void updateDate() {
		date = (int) System.currentTimeMillis();
	}

	/** {@inheritDoc} */
	public final int getDate() {
		return date;
	}

	/** {@inheritDoc} */
	public final boolean isDirty() {
		return dirty;
	}

	/** {@inheritDoc} */
	public final void setDirtyState(boolean state) {
		if (state != dirty) {
			if (state) {
				LOGGER.fine("The graph is now DIRTY"); //$NON-NLS-1$
			} else {
				LOGGER.fine("The graph is now CLEAN"); //$NON-NLS-1$
			}
			this.dirty = state;
		}
	}

	/**
	 * @return the graphical properties
	 */
	public final GraphEditorProperties getEditorProperties() {
		return editorProperties;
	}

	/** {@inheritDoc} */
	public final void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();

		if (NODE_ADDED_PROP.equals(prop)
				|| NODE_REMOVED_PROP.equals(prop)
				|| INode.INCOMING_ARCS_PROP.equals(prop)
				|| INode.OUTGOING_ARCS_PROP.equals(prop)
				|| INode.PUBLIC_PROP.equals(prop)
				|| IAttribute.VALUE_PROP.equals(prop)) {
			updateDate();
			setDirtyState(true);
		}
	}

	/** {@inheritDoc} */
	public final void addGraph(IGraph graph) {
		if (!formalism.getId().equals(graph.getFormalism().getId())) {
			LOGGER.warning("The two formalisms are different [" + this.formalism.getId() + " ≠ " + graph.getFormalism().getId() + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			return;
		}
		LOGGER.fine("Add all elements from graph #" + graph.getId()); //$NON-NLS-1$
		for (INode node : graph.getNodes()) {
			NodeModel nodeModel = (NodeModel) node;
			for (IArc arc : new ArrayList<IArc>(node.getIncomingArcs())) {
				nodeModel.removeIncomingArc(arc);
			}
			for (IArc arc : new ArrayList<IArc>(node.getOutgoingArcs())) {
				nodeModel.removeOutgoingArc(arc);
			}
			((AbstractElement) node).setId(getNewId());
			addNode(node);
		}
		for (IArc arc : graph.getArcs()) {
			((AbstractElement) arc).setId(getNewId());
			addArc(arc);
		}
		for (IStickyNote sticky : stickys) {
			addSticky(sticky);
		}
	}
}
