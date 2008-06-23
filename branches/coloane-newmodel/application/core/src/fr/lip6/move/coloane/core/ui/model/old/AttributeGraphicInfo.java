package fr.lip6.move.coloane.core.ui.model.old;

import org.eclipse.draw2d.geometry.Point;

public class AttributeGraphicInfo implements IAttributeGraphicInfo {

	/** Le noeud enrichi */
	private final IAttributeImpl attribute;

	/** Les coordonees */
	private int x = 0;
	private int y = 0;

	/**
	 * Constructeur
	 * @param attributeImpl L'attribut enrichi
	 */
	public AttributeGraphicInfo(IAttributeImpl a) {
		this.attribute = a;
		this.x = a.getGenericAttribute().getXPosition();
		this.y = a.getGenericAttribute().getYPosition();
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
		this.x = xPosition;
		this.y = yPosition;

		// Mise a jour du noeud generique
		this.attribute.getGenericAttribute().setPosition(x, y);

		// Lever un evenement
		((AttributeImplAdapter) this.attribute).firePropertyChange(AttributeImplAdapter.LOCATION_PROP, null, new Point(this.x, this.y));
	}

	public void setLocation(Point location) {
		setLocation(location.x, location.y);
	}
}

