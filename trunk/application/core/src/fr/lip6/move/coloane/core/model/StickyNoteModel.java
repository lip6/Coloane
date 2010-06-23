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
 * Description d'une note qui sera affichée sur l'éditeur
 */
public class StickyNoteModel extends AbstractPropertyChange implements IStickyNote, ILocatedElement {
	/** Logger 'fr.lip6.move.coloane.core'. */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Les coordonnées de la note */
	private int x;
	private int y;
	private int height;
	private int width;

	/** Les guides pour l'alignement automatique */
	private EditorGuide horizontalGuide;
	private EditorGuide verticalGuide;

	/** Le texte par défaut de la note */
	private String text = "Sticky"; //$NON-NLS-1$

	/** Liste des liens */
	private List<ILink> links = new ArrayList<ILink>();

	/**
	 * Constructeur
	 */
	StickyNoteModel() {
		LOGGER.finest("Création d'une StickyNote()"); //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	public final String getLabelContents() {
		return text;
	}

	/** {@inheritDoc} */
	public final void setLabelContents(String newText) {
		LOGGER.finest("setLabelContent(" + newText + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		// sauvegarde de l'ancienne valeur
		String oldText = this.text;

		// Mise en place de la nouvelle valeur
		this.text = newText;

		// Evenement
		firePropertyChange(IStickyNote.VALUE_PROP, oldText, this.text);
	}

	/** {@inheritDoc} */
	public final ILocationInfo getLocationInfo() {
		return this;
	}

	/** {@inheritDoc} */
	public final Point getLocation() {
		return new Point(this.x, this.y);
	}

	/** {@inheritDoc} */
	public final void setLocation(Point location) {
		LOGGER.finest("setLocation(" + location + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		// Sauvegarde des anciennes valeurs
		Point oldLocation = getLocation();

		// Mise en place des nouvelles
		this.x = location.x;
		this.y = location.y;

		// Evénement
		firePropertyChange(LOCATION_PROP, oldLocation, getLocation());
	}
	
	/** {@inheritDoc} */
	public final void resetLocation() {
		setLocation(new Point(0, 0));
	}

	/** {@inheritDoc} */
	public final Dimension getSize() {
		return new Dimension(this.width, this.height);
	}

	/** {@inheritDoc} */
	public final void setSize(Dimension size) {
		LOGGER.finest("setSize(" + size + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		// Sauvegarde des anciennes valeurs
		Dimension oldDimension = getSize();

		// Mise en place des nouvelles valeurs
		this.width = size.width;
		this.height = size.height;

		// Evénement
		firePropertyChange(IStickyNote.RESIZE_PROP, oldDimension, getSize());
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
