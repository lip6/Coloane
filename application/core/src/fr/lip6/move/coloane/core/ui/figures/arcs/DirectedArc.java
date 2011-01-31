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
package fr.lip6.move.coloane.core.ui.figures.arcs;

import fr.lip6.move.coloane.core.ui.figures.AbstractArcFigure;
import fr.lip6.move.coloane.core.ui.figures.IArcFigure;

import org.eclipse.draw2d.PolygonDecoration;

/**
 * Directed arc definition.<br>
 * This arc only defines a small triangle at its end.
 *
 * @author Jean-Baptiste Voron
 */
public class DirectedArc extends AbstractArcFigure implements IArcFigure {
	/**
	 * Constructor
	 */
	public DirectedArc() {
		super();
		setTargetDecoration(buildDecoration());
	}

	/**
	 * Create the decoration that will be used for the target-side of the arc
	 * @return the decoration
	 */
	protected final PolygonDecoration buildDecoration() {
		PolygonDecoration decoration = new PolygonDecoration();
		decoration.setTemplate(PolygonDecoration.TRIANGLE_TIP);
		decoration.setScale(3, 3);
		
		return decoration;
	}
}
