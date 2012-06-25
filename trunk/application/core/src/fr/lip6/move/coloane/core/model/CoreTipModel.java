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

import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.core.ui.rulers.EditorRulerProvider;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import org.eclipse.draw2d.geometry.Point;

/**
 * Describe a special element : TIP.<br>
 * A tip is like a bubble and is often sticked to an element.
 *
 * @author Clément Démoulins
 */
public class CoreTipModel extends AbstractPropertyChange implements ILocatedElement, ILocationInfo, ICoreTip {

	/** Guides */
	private EditorGuide horizontalGuide;
	private EditorGuide verticalGuide;

	/** Current position */
	private Point location;
	/** The description of the tip */
	private ITip tip;
	private ArcTipModel arcModel;

	/**
	 * Class that describes an arc between an {@link IElement} and a {@link CoreTipModel}.<br>
	 * Only {@link Object} methods are used here.
	 */
	public static class ArcTipModel { }

	/**
	 * Constructor
	 * @param tip The tip
	 */
	public CoreTipModel(ITip tip) {
		this.tip = tip;
		this.arcModel = new ArcTipModel();
	}

	/** {@inheritDoc} */
	@Override
	public final ArcTipModel getArcModel() {
		return arcModel;
	}

	/** {@inheritDoc} */
	@Override
	public final ILocationInfo getLocationInfo() {
		return this;
	}

	/** {@inheritDoc} */
	@Override
	public final int getIdObject() {
		return tip.getIdObject();
	}

	/** {@inheritDoc} */
	@Override
	public final String getName() {
		return tip.getName();
	}

	/** {@inheritDoc} */
	@Override
	public final String getValue() {
		return tip.getValue();
	}

	/** {@inheritDoc} */
	@Override
	public final EditorGuide getGuide(int orientation) {
		if (orientation == EditorRulerProvider.HORIZONTAL_ORIENTATION) {
			return this.horizontalGuide;
		} else {
			return this.verticalGuide;
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void setGuide(EditorGuide guide) {
		if (guide.getOrientation() == EditorRulerProvider.HORIZONTAL_ORIENTATION) {
			this.horizontalGuide = guide;
		} else {
			this.verticalGuide = guide;
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void removeGuide(int orientation) {
		if (orientation == EditorRulerProvider.HORIZONTAL_ORIENTATION) {
			this.horizontalGuide = null;
		} else {
			this.verticalGuide = null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public final Point getLocation() {
		return location;
	}

	/** {@inheritDoc} */
	@Override
	public final void setLocation(Point newLocation) {
		Point oldLocation = this.location;
		location = newLocation;
		firePropertyChange(LOCATION_PROP, oldLocation, location);
	}

	/** {@inheritDoc} */
	@Override
	public final void resetLocation() {
		setLocation(new Point(0, 0));
	}
}

