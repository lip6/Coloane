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

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Description d'une figure en forme de triangle.<br>
 * Cette figure peut être utilisée pour représenter un noeud sur un modèle.
 */
public class TriangleNode extends AbstractNodeFigure {
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void fillShape(Graphics graphics) {
		graphics.fillRectangle(getBounds());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void outlineShape(Graphics graphics) {
		PointList list = new PointList();

		// Contraintes de la figure imposée par le formalisme
		Rectangle r = getBounds();

		list.addPoint(r.getTop());
		list.addPoint(r.getBottomLeft().getTranslated(0, -1));
		list.addPoint(r.getBottomRight().getTranslated(0, -1));

		graphics.drawPolygon(list);
	}

	/**
	 * {@inheritDoc}
	 */
	public final ConnectionAnchor getConnectionAnchor() {
		return new AbstractConnectionAnchor(this) {
			/**
			 * Calcul du point de connection a cette figure.<br>
			 *
			 * Le point retourné est le sommet le plus proche du point de référence
			 */
			public Point getLocation(Point reference) {
				Rectangle r = getBounds();

				Point[] points = new Point[3];
				points[0] = r.getTop();
				points[1] = r.getBottomLeft().getTranslated(0, -1);
				points[2] = r.getBottomRight().getTranslated(0, -1);

				int distMin = Integer.MAX_VALUE;
				int indexMin = 0;
				for (int i = 0; i < points.length; i++) {
					int distTmp = points[i].getDistance2(reference);
					if (distTmp < distMin) {
						indexMin = i;
						distMin = distTmp;
					}
				}

				return points[indexMin];
			}
		};
	}
}
