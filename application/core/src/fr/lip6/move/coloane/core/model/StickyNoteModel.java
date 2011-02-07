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

import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.core.ui.rulers.EditorRulerProvider;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

/**
 * Sticky Note
 *
 * @author Clément Démoulins
 * @author Jean-Baptiste Voron
 */
public class StickyNoteModel extends AbstractPropertyChange implements IStickyNote, ILocatedElement {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Location */
	private Point location;
	
	/** Dimensions */
	private Dimension dimension;

	/** Guides to help the alignment */
	private EditorGuide horizontalGuide;
	private EditorGuide verticalGuide;

	/** Default text */
	private String text = "Sticky"; //$NON-NLS-1$

	/** List of links for this note */
	private List<ILink> links = new ArrayList<ILink>();

	/** Constructor */
	StickyNoteModel() {
		LOGGER.finest("Build a sticky note with default values"); //$NON-NLS-1$
		this.location = new Point(0,0);
		this.dimension = new Dimension(100, 70);
	}

	/** {@inheritDoc} */
	public final String getLabelContents() {
		return text;
	}

	/** {@inheritDoc} */
	public final void setLabelContents(String newText) {
		LOGGER.finest("Set note value " + newText); //$NON-NLS-1$
		String oldText = this.text; // Backup the old value
		this.text = newText; // Set the text
		firePropertyChange(IStickyNote.VALUE_PROP, oldText, this.text); // Tells the editor that something has changed
	}

	/** {@inheritDoc} */
	public final ILocationInfo getLocationInfo() {
		return this;
	}

	/** {@inheritDoc} */
	public final Point getLocation() {
		return this.location;
	}

	/** {@inheritDoc} */
	public final void setLocation(Point location) {
		LOGGER.finest("Set sticky note location " + location); //$NON-NLS-1$
		Point oldLocation = getLocation(); // Backup
		this.location = location.getCopy();
		firePropertyChange(LOCATION_PROP, oldLocation, getLocation()); // Tells the editor that something has changed
	}
	
	/** {@inheritDoc} */
	public final void resetLocation() {
		setLocation(new Point(0, 0));
	}

	/** {@inheritDoc} */
	public final Dimension getSize() {
		return this.dimension;
	}

	/** {@inheritDoc} */
	public final void setSize(Dimension size) {
		LOGGER.finest("Set sticky note dimensions " + size); //$NON-NLS-1$
		Dimension oldDimension = getSize(); // Backup old value
		this.dimension = size;
		firePropertyChange(IStickyNote.RESIZE_PROP, oldDimension, getSize()); // Tells the editor that something has changed
	}

	/** {@inheritDoc} */
	public final EditorGuide getGuide(int orientation) {
		if (orientation == EditorRulerProvider.HORIZONTAL_ORIENTATION) {
			return this.horizontalGuide;
		} else {
			return this.verticalGuide;
		}
	}

	/** {@inheritDoc} */
	public final void setGuide(EditorGuide guide) {
		if (guide.getOrientation() == EditorRulerProvider.HORIZONTAL_ORIENTATION) {
			this.horizontalGuide = guide;
		} else {
			this.verticalGuide = guide;
		}
	}
	
	/** {@inheritDoc} */
	public final void removeGuide(int orientation) {
		if (orientation == EditorRulerProvider.HORIZONTAL_ORIENTATION) {
			this.horizontalGuide = null;
		} else {
			this.verticalGuide = null;
		}
	}

	/** {@inheritDoc} */
	public final void addLink(ILink link) {
		links.add(link);
		firePropertyChange(INode.INCOMING_ARCS_PROP, null, link);
	}

	/** {@inheritDoc} */
	public final List<ILink> getLinks() {
		return Collections.unmodifiableList(links);
	}

	/** {@inheritDoc} */
	public final boolean removeLink(ILink link) {
		boolean res = links.remove(link);
		firePropertyChange(INode.INCOMING_ARCS_PROP, null, link);
		return res;
	}

	/** {@inheritDoc} */
	@Override
	public final String toString() {
		return "Sticky Note {" + text + "}"; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
