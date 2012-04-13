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

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Description d'une figure en forme de rectangle aux bords arrondis.<br>
 * Cette figure peut être utilisée pour représenter un noeud sur un modèle.
 *
 * @author Clément Demoulins
 */
public class RoundedRectangleNode extends AbstractNodeFigure {
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void fillShape(Graphics graphics) {
		graphics.fillRectangle(getBounds().getResized(-1, -1));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void outlineShape(Graphics graphics) {
		Rectangle r = getBounds();
		int x = r.x + getLineWidth() / 2;
		int y = r.y + getLineWidth() / 2;
		int w = r.width - Math.max(1, getLineWidth());
		int h = r.height - Math.max(1, getLineWidth());

		Rectangle fr = new Rectangle(x, y, w, h);
		graphics.drawRoundRectangle(fr, 4, 4);
	}

	/**
	 * {@inheritDoc}
	 */
	public final ConnectionAnchor getConnectionAnchor() {
		return new ChopboxAnchor(this);
	}
}
