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
package fr.lip6.move.coloane.core.copypast.container;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

/**
 * Class that allow to re-build a node.<br>
 * This operation is used when a user copy/cut/paste an element from the model.
 * 
 * @author Clément Démoulins
 */
public class NodeContainer {
	/** Space used in order to avoid overlapping between old objects and new objects */
	private static final int DELTA_COPY = 10;
	
	/** The node ID */
	private int id;

	/** The formalism that describes the node */
	private INodeFormalism nodeFormalism;

	/** The list of attributes attached to the node */
	private List<AttributeContainer> attributes = new ArrayList<AttributeContainer>();

	/** The current location of the node */
	private Point location;
	
	/** The background color of the node */
	private Color background;
	
	/** The foreground color of the node */
	private Color foreground;
	
	/** The current scale applied on the node */
	private int scale;

	/**
	 * Constructor
	 * @param node The node to be copied
	 */
	public NodeContainer(INode node) {
		this.id = node.getId();
		this.nodeFormalism = node.getNodeFormalism();

		// Backup attributes
		for (IAttribute attr : node.getAttributes()) {
			this.attributes.add(new AttributeContainer(attr));
		}
		
		this.location = node.getGraphicInfo().getLocation();
		this.foreground = node.getGraphicInfo().getForeground();
		this.background = node.getGraphicInfo().getBackground();
		this.scale = node.getGraphicInfo().getScale();
		
	}

	/**
	 * Duplicate a node (use when the user paste a new node)
	 * @param graph The graph that holds the node
	 * @return A new INode that has been already added to the model
	 * @throws ModelException If something went wrong during the node creation
	 */
	public final INode copy(IGraph graph) throws ModelException {
		// Move the copy to avoid overlapping
		location.x += DELTA_COPY;
		location.y += DELTA_COPY;
		// Move attributes to avoid overlapping
		for (AttributeContainer ac : attributes) {
			ac.setLocation(ac.getLocation().translate(DELTA_COPY, DELTA_COPY));
		}

		// Node creation
		INode node = graph.createNode(nodeFormalism);
		node.getGraphicInfo().setLocation(location);
		node.getGraphicInfo().setForeground(foreground);
		node.getGraphicInfo().setBackground(background);
		node.getGraphicInfo().setScale(scale);
		
		// Attributes creation
		for (AttributeContainer ac : attributes) {
			node.getAttribute(ac.getName()).setValue(ac.getValue());
			node.getAttribute(ac.getName()).getGraphicInfo().setLocation(ac.getLocation());
		}
		return node;
	}

	/**
	 * @return The ID of the node container
	 */
	public final int getId() {
		return id;
	}
}
