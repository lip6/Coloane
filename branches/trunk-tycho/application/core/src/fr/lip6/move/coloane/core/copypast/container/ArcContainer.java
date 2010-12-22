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
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

/**
 * Class that allow to re-build an arc.<br>
 * This operation is used when a user copy/cut/paste an element from the model.
 * 
 * @author Clément Démoulins
 */
public class ArcContainer {
	/** Space used in order to avoid overlapping between old objects and new objects */
	private static final int DELTA_COPY = 10;

	/** Arc ID */
	private int id;

	/** Arc source ID */
	private int idSource;
	
	/** Arc target ID */
	private int idTarget;
	
	/** Arc formalism */
	private IArcFormalism arcFormalism;

	/** List of arc attributes */
	private List<AttributeContainer> attributs = new ArrayList<AttributeContainer>();

	/** Arc foreground color */
	private Color color;
	
	/** List of inflex point */
	private List<Point> pis = new ArrayList<Point>();

	/**
	 * Constructor
	 * @param arc The arc to store
	 */
	public ArcContainer(IArc arc) {
		this.id = arc.getId();
		this.idSource = arc.getSource().getId();
		this.idTarget = arc.getTarget().getId();
		this.color = arc.getGraphicInfo().getColor();
		this.arcFormalism = arc.getArcFormalism();

		// Inflex points backup
		for (Bendpoint bp : arc.getInflexPoints()) {
			pis.add(bp.getLocation());
		}
		// Attributes backup
		for (IAttribute attr : arc.getAttributes()) {
			attributs.add(new AttributeContainer(attr));
		}
	}

	/**
	 * Duplicate an arc (use when the user paste a new arc)
	 * @param graph The graph that will hold the arc
	 * @param source The source of the arc
	 * @param target The target of the arc
	 * @return An IArc already added to the graph
	 * @throws ModelException If something went wrong during the creation
	 */
	public final IArc copy(IGraph graph, INode source, INode target) throws ModelException {
		// Move inflex point to create a distinct arc
		for (Point p : pis) {
			p.translate(DELTA_COPY, DELTA_COPY);
		}
		
		// If there is no inflex point, create one to be able to distinguish between the two arcs
		if (pis.isEmpty()) {
			pis.add(source.getGraphicInfo().getLocation().getCopy().translate(DELTA_COPY, DELTA_COPY));
		}
		
		// Move attributes to avoid overlapping with old attributes
		for (AttributeContainer ac : attributs) {
			ac.setLocation(ac.getLocation().translate(DELTA_COPY, DELTA_COPY));
		}

		// Create the arc
		IArc arc = graph.createArc(arcFormalism, source, target);
		arc.getGraphicInfo().setColor(color);
		// Set the attributes
		for (AttributeContainer ac : attributs) {
			arc.getAttribute(ac.getName()).setValue(ac.getValue());
			arc.getAttribute(ac.getName()).getGraphicInfo().setLocation(ac.getLocation());
		}
		// Set the inflex points
		for (int index = 0; index < pis.size(); index++) {
			Point p = pis.get(index);
			arc.addInflexPoint(p, index);
		}
		return arc;
	}

	/**
	 * @return The ID of the source node 
	 */
	public final int getIdSource() {
		return idSource;
	}

	/**
	 * @return The ID of the target node
	 */
	public final int getIdTarget() {
		return idTarget;
	}

	/**
	 * @return The ID of the arc container
	 */
	public final int getId() {
		return id;
	}
}
