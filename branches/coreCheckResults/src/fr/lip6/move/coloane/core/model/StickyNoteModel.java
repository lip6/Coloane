package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
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
		LOGGER.finest("Build a sticky note"); //$NON-NLS-1$
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
		LOGGER.finest("Set note location " + location); //$NON-NLS-1$
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
		LOGGER.finest("set note dimensions " + size); //$NON-NLS-1$
		Dimension oldDimension = getSize(); // Backup old value
		this.dimension = size;
		firePropertyChange(IStickyNote.RESIZE_PROP, oldDimension, getSize()); // Tells the editor that something has changed
	}

	/** {@inheritDoc} */
	public final EditorGuide getHorizontalGuide() {
		return this.horizontalGuide;
	}

	/** {@inheritDoc} */
	public final EditorGuide getVerticalGuide() {
		return this.verticalGuide;
	}

	/** {@inheritDoc} */
	public final void setHorizontalGuide(EditorGuide guide) {
		this.horizontalGuide = guide;
	}

	/** {@inheritDoc} */
	public final void setVerticalGuide(EditorGuide guide) {
		this.verticalGuide = guide;
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
}
