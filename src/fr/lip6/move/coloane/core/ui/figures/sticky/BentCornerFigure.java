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
package fr.lip6.move.coloane.core.ui.figures.sticky;

import fr.lip6.move.coloane.core.ui.figures.IStickyNoteFigure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * A figure that has a bent corner in the top right hand. Typically used for sticky notes.
 */
public class BentCornerFigure extends Figure {

	private int cornerSize;

	/**
	 * Constructs an empty BentCornerFigure with default background color of
	 * ColorConstants.tooltipBackground and default corner size.
	 */
	public BentCornerFigure() {
		setBackgroundColor(ColorConstants.tooltipBackground);
		setForegroundColor(ColorConstants.tooltipForeground);
		setCornerSize(IStickyNoteFigure.DEFAULT_CORNER_SIZE);
	}

	/**
	 * Returns the size, in pixels, that the figure should use to draw its bent corner.
	 * @return size of the corner
	 */
	public  final int getCornerSize() {
		return cornerSize;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void paintFigure(Graphics graphics) {
		Rectangle rect = getBounds().getCopy();

		graphics.translate(getLocation());

		// fill the Note
		PointList outline = new PointList();

		outline.addPoint(0, 0);
		outline.addPoint(rect.width - cornerSize, 0);
		outline.addPoint(rect.width - 1, cornerSize);
		outline.addPoint(rect.width - 1, rect.height - 1);
		outline.addPoint(0, rect.height - 1);

		graphics.fillPolygon(outline);

		// draw the inner outline
		PointList innerLine = new PointList();

		innerLine.addPoint(rect.width - cornerSize - 1, 0);
		innerLine.addPoint(rect.width - cornerSize - 1, cornerSize);
		innerLine.addPoint(rect.width - 1, cornerSize);
		innerLine.addPoint(rect.width - cornerSize - 1, 0);
		innerLine.addPoint(0, 0);
		innerLine.addPoint(0, rect.height - 1);
		innerLine.addPoint(rect.width - 1, rect.height - 1);
		innerLine.addPoint(rect.width - 1, cornerSize);

		graphics.drawPolygon(innerLine);

		graphics.translate(getLocation().getNegated());
	}

	/**
	 * Sets the size of the figure's corner to the given offset.
	 * @param newSize the new size to use.
	 */
	public final void setCornerSize(int newSize) {
		cornerSize = newSize;
	}
}
