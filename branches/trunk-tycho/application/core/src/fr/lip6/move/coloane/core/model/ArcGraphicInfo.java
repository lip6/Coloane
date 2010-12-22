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

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IArcGraphicInfo;

import java.util.logging.Logger;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

/**
 * Graphical definition of an arc {@link ArcModel}
 * 
 * @author Clément Démoulins
 * @author Jean-Baptiste Voron
 */
public class ArcGraphicInfo implements IArcGraphicInfo {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The last middle point has to be stored */
	private Point middlePoint = null;

	/** The foreground color */
	private Color color = ColorConstants.black;

	/** Is the arc curved ? */
	private boolean curve = Boolean.parseBoolean(Coloane.getInstance().getPreference("COLORARC_LINESTYLE")); //$NON-NLS-1$

	/** The model object reference */
	private IArc arc;

	/**
	 * Constructor
	 * @param arc The arc model object to which this graphical definition is associated to
	 */
	public ArcGraphicInfo(IArc arc) {
		this.arc = arc;
		this.middlePoint = this.findMiddlePoint();
	}

	/** {@inheritDoc} */
	public final Point findMiddlePoint() {
		Point source, target;
		int size = arc.getInflexPoints().size();
		if (size > 0) {
			if (size % 2 == 0) {
				source = arc.getInflexPoint(size / 2 - 1);
				target = arc.getInflexPoint(size / 2);
			} else {
				source = arc.getInflexPoint(size / 2);
				target = source;
			}
		} else {
			source = arc.getSource().getGraphicInfo().getLocation();
			target = arc.getTarget().getGraphicInfo().getLocation();
		}
		Point middle = source.getTranslated(target).scale(0.5);
		return middle;
	}

	/** {@inheritDoc} */
	public final void updateMiddlePoint() {
		LOGGER.finest("updateMiddlePoint"); //$NON-NLS-1$
		this.middlePoint = findMiddlePoint();
	}

	/** {@inheritDoc} */
	public final Point getMiddlePoint() {
		return this.middlePoint;
	}

	/** {@inheritDoc} */
	public final Color getColor() {
		return color;
	}

	/** {@inheritDoc} */
	public final void setColor(Color color) {
		LOGGER.finest("setColor(" + color + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		Color oldValue = this.color;
		this.color = color;
		((ArcModel) arc).firePropertyChange(IArc.COLOR_PROP, oldValue, color);
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean getCurve() {
		return this.curve;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setCurve(boolean flag) {
		this.curve = flag;
		((ArcModel) arc).firePropertyChange(IArc.CURVE_PROP, !flag, flag);
	}
}
