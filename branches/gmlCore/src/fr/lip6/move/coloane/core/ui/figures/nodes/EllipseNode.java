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
package fr.lip6.move.coloane.core.ui.figures.nodes;

import fr.lip6.move.coloane.core.ui.figures.AbstractNodeFigure;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Describe an ellipse figure.<br>
 * This can be used to draw a node in the editor
 * @author Clément Demoulins
 */
public class EllipseNode extends AbstractNodeFigure {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void fillShape(Graphics graphics) {
		graphics.fillOval(getBounds().getResized(-1, -1));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void outlineShape(Graphics graphics) {
		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getBounds());
		r.width--;
		r.height--;
		r.shrink((getLineWidth() - 1) / 2, (getLineWidth() - 1) / 2);
		graphics.drawOval(r);
	}

	/**
	 * {@inheritDoc}
	 */
	public final ConnectionAnchor getConnectionAnchor() {
		return new EllipseAnchor(this);
	}

}
