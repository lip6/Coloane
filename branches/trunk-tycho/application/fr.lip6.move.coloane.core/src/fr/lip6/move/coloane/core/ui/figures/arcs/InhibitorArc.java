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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.RotatableDecoration;
/**
 * Inhibitor arc definition.<br>
 * This arc defines is graphically terminated by a small round.
 *
 * @author Clément Demoulins
 * @author Jean-Baptiste Voron
 */
public class InhibitorArc extends AbstractArcFigure implements IArcFigure {
	/**
	 * Constructor
	 */
	public InhibitorArc() {
		super();
		setTargetDecoration(buildDecoration());
	}

	/**
	 * Create the decoration that will be used for the target-side of the arc
	 * @return the decoration
	 */
	private RotatableDecoration buildDecoration() {
		CircleDecoration decoration = new CircleDecoration(7);

		decoration.setFill(true);
		decoration.setBackgroundColor(ColorConstants.white);
		return decoration;
	}
}
