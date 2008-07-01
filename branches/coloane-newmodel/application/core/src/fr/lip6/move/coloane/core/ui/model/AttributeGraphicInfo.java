package fr.lip6.move.coloane.core.ui.model;

import fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute;
import fr.lip6.move.coloane.core.ui.model.interfaces.IAttributeGraphicInfo;

import org.eclipse.draw2d.geometry.Point;

public class AttributeGraphicInfo implements IAttributeGraphicInfo {

	/** Le noeud enrichi */
	private final IAttribute attribute;

	/** Les coordonees */
	private int x = 0;
	private int y = 0;

	/**
	 * Constructeur
	 * @param attributeImpl L'attribut enrichi
	 */
	public AttributeGraphicInfo(IAttribute attr) {
		this.attribute = attr;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeGraphicInfo#getLocation()
	 */
	public final Point getLocation() {
		return new Point(this.x, this.y);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IAttributeGraphicInfo#setLocation(int, int)
	 */
	public final void setLocation(int xPosition, int yPosition) {
		Point oldValue = new Point(this.x, this.y);
		this.x = xPosition;
		this.y = yPosition;
		Point newValue = new Point(this.x, this.y);

		// Lever un evenement
		((AttributeModel) this.attribute).firePropertyChange(IAttribute.LOCATION_PROP, oldValue, newValue);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttributeGraphicInfo#setLocation(org.eclipse.draw2d.geometry.Point)
	 */
	public final void setLocation(Point location) {
		setLocation(location.x, location.y);
	}
}

