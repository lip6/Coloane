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

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;

/**
 * Circle decoration.
 * @author Clément Démoulins
 */
public class CircleDecoration extends Ellipse implements RotatableDecoration {

	/**
	 * Constructor
	 * @param diameter Diameter of this circle
	 */
	public CircleDecoration(int diameter) {
		setSize(diameter, diameter);
	}

	/**
	 * Set the diameter of the circle.
	 * @param diameter diameter of the circle
	 */
	public final void setSize(int diameter) {
		super.setSize(diameter, diameter);
	}

	/**
	 * Set the diameter of the circle.
	 * @param w diameter of the circle
	 * @param h not used !
	 */
	@Override
	public final void setSize(int w, int h) {
		super.setSize(w, w);
	}

	/** {@inheritDoc} */
	@Override
	public final void setReferencePoint(Point ref) {
		Point a = getLocation().getCopy();
		Point ab = ref.getTranslated(a.getNegated());
		int diameter = getSize().height;

		double cx = a.x + (ab.x * diameter) / (2. * Math.sqrt(ab.x * ab.x + ab.y * ab.y)) - diameter / 2.;
		double cy = a.y + (ab.y * diameter) / (2. * Math.sqrt(ab.x * ab.x + ab.y * ab.y)) - diameter / 2.;
		PrecisionPoint center = new PrecisionPoint(cx, cy);

		center.x = (int) Math.round(center.preciseX());
		center.y = (int) Math.round(center.preciseY());

		// Pour corriger l'arrondi
		if ((ab.x * diameter) / (2. * Math.sqrt(ab.x * ab.x + ab.y * ab.y)) < 0) {
			center.x++;
		}
		if ((ab.y * diameter) / (2. * Math.sqrt(ab.x * ab.x + ab.y * ab.y)) < 0) {
			center.y++;
		}

		setLocation(center);
	}

}
