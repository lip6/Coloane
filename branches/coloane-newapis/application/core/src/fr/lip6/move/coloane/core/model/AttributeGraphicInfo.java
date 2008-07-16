package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

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

	/**
	 * Change la position de l'attribut
	 * @param x La position sur l'axe des abcisses
	 * @param y La position sur l'axe des ordonnées
	 */
	private void setLocation(int x, int y) {
		Point oldValue = new Point(this.x, this.y);
		this.x = x;
		this.y = y;
		Point newValue = new Point(this.x, this.y);

		// Lever un evenement
		((AttributeModel) this.attribute).firePropertyChange(IAttribute.LOCATION_PROP, oldValue, newValue);

		// Il faut avertir FrameKit
		Coloane.notifyModelChange(attribute.getReference());
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttributeGraphicInfo#setLocation(org.eclipse.draw2d.geometry.Point)
	 */
	public final void setLocation(Point location) {
		setLocation(location.x, location.y);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo#getBackground()
	 */
	public final Color getBackground() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo#getForeground()
	 */
	public final Color getForeground() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo#setBackground(org.eclipse.swt.graphics.Color)
	 */
	public final void setBackground(Color background) {
		return;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo#setForeground(org.eclipse.swt.graphics.Color)
	 */
	public final void setForeground(Color foreground) {
		return;
	}

	/**
	 * @return La largeur du noeud en tenant compte du zoom
	 */
	private int getHeight() {
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.ILocationInfo#getSize()
	 */
	public final Dimension getSize() {
		return new Dimension(getWidth(), getHeight());
	}

	/**
	 * @return La largeur du noeud en tenant compte du zoom
	 */
	private int getWidth() {
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.ILocationInfo#setSize(org.eclipse.draw2d.geometry.Dimension)
	 */
	public final void setSize(Dimension newDimension) {
		return;
	}
}

