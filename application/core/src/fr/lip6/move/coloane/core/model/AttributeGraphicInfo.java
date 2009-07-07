package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;

import java.util.logging.Logger;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

/**
 * Description graphique d'un attribut
 */
public class AttributeGraphicInfo implements IAttributeGraphicInfo {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Le noeud enrichi */
	private final IAttribute attribute;

	/** Les coordonees */
	private int x = 0;
	private int y = 0;

	/**
	 * Constructeur
	 * @param attr L'attribut concerné par ces propriétés graphiques
	 */
	public AttributeGraphicInfo(IAttribute attr) {
		this.attribute = attr;
	}

	/** {@inheritDoc} */
	public final Point getLocation() {
		return new Point(this.x, this.y);
	}

	/**
	 * Change la position de l'attribut
	 * @param x La position sur l'axe des abcisses
	 * @param y La position sur l'axe des ordonnées
	 */
	private void setLocation(int x, int y) {
		if (this.x != 0 || this.y != 0 || !attribute.getValue().equals(attribute.getAttributeFormalism().getDefaultValue())) {
			LOGGER.finest("setLocation(" + x + ", " + y + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			Point oldValue = new Point(this.x, this.y);
			this.x = x;
			this.y = y;
			Point newValue = new Point(this.x, this.y);

			// Lever un evenement
			((AttributeModel) this.attribute).firePropertyChange(ILocationInfo.LOCATION_PROP, oldValue, newValue);
		}
	}

	/** {@inheritDoc} */
	public final void setLocation(Point location) {
		setLocation(location.x, location.y);
	}

	/** {@inheritDoc} */
	public final Color getBackground() {
		return ColorConstants.white;
	}

	/** {@inheritDoc} */
	public final Color getForeground() {
		return ColorConstants.black;
	}

	/** {@inheritDoc} */
	public final void setBackground(Color background) {
		return;
	}

	/** {@inheritDoc} */
	public final void setForeground(Color foreground) {
		return;
	}

	/** {@inheritDoc} */
	public final Dimension getSize() {
		String text = attribute.getValue();
		Font font = JFaceResources.getDefaultFont();
		return FigureUtilities.getTextExtents(text, font);
	}

	/** {@inheritDoc} */
	public final boolean isVisible() {
		return !attribute.getAttributeFormalism().getDefaultValue().equals(attribute.getValue());
	}
}

