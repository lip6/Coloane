package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;

import java.util.ArrayList;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

/**
 * Description d'une note qui sera affichée sur l'éditeur
 */
public class StickyNote extends AbstractElement implements IStickyNote, ILocatedElement {

	/** Les coordonnées de la note */
	private int x;
	private int y;
	private int height;
	private int width;

	/** Les guides pour l'alignement automatique */
	private EditorGuide horizontalGuide;
	private EditorGuide verticalGuide;

	/** Le texte par défaut de la note */
	private String text = "Sticky";

	/** L'identifiant de la note parmi les noeuds du graphe */
	private int id;

	/**
	 * Constructeur
	 * @param parent L'élément parent (le graphe)
	 * @param id L'identifiant attribué par le graphe parent
	 */
	public StickyNote(IElement parent, int id) {
		super(parent, new ArrayList<IAttributeFormalism>());
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.interfaces.IStickyNote#getLabelContents()
	 */
	public final String getLabelContents() {
		return text;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.interfaces.IStickyNote#setLabelContents(java.lang.String)
	 */
	public final void setLabelContents(String newText) {
		// sauvegarde de l'ancienne valeur
		String oldText = this.text;

		// Mise en place de la nouvelle valeur
		this.text = newText;

		// Evenement
		firePropertyChange(IStickyNote.VALUE_PROP, oldText, this.text); //$NON-NLS-2$//$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IElement#getId()
	 */
	public final int getId() {
		return this.id;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.interfaces.ILocatedElement#getLocationInfo()
	 */
	public final ILocationInfo getLocationInfo() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.ILocationInfo#getLocation()
	 */
	public final Point getLocation() {
		return new Point(this.x, this.y);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.ILocationInfo#setLocation(org.eclipse.draw2d.geometry.Point)
	 */
	public final void setLocation(Point location) {
		// Sauvegarde des anciennes valeurs
		Point oldLocation = getLocation();

		// Mise en place des nouvelles
		this.x = location.x;
		this.y = location.y;

		// Evénement
		firePropertyChange(IStickyNote.LOCATION_PROP, oldLocation, getLocation());
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.ILocationInfo#getSize()
	 */
	public final Dimension getSize() {
		return new Dimension(this.width, this.height);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.ILocationInfo#setSize(org.eclipse.draw2d.geometry.Dimension)
	 */
	public final void setSize(Dimension size) {
		// Sauvegarde des anciennes valeurs
		Dimension oldDimension = getSize();

		// Mise en place des nouvelles valeurs
		this.width = size.width;
		this.height = size.height;

		// Evénement
		firePropertyChange(IStickyNote.RESIZE_PROP, oldDimension, getSize());
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.interfaces.ILocatedElement#getHorizontalGuide()
	 */
	public final EditorGuide getHorizontalGuide() {
		return this.horizontalGuide;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.interfaces.ILocatedElement#getVerticalGuide()
	 */
	public final EditorGuide getVerticalGuide() {
		return this.verticalGuide;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.interfaces.ILocatedElement#setHorizontalGuide(fr.lip6.move.coloane.core.ui.rulers.EditorGuide)
	 */
	public final void setHorizontalGuide(EditorGuide guide) {
		this.horizontalGuide = guide;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.model.interfaces.ILocatedElement#setVerticalGuide(fr.lip6.move.coloane.core.ui.rulers.EditorGuide)
	 */
	public final void setVerticalGuide(EditorGuide guide) {
		this.verticalGuide = guide;
	}
}
