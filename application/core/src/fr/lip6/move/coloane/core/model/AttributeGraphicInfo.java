package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

/**
 * Graphical description of an attribute
 * 
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class AttributeGraphicInfo implements IAttributeGraphicInfo {
	/** The attribute model object */
	private final IAttribute attribute;

	/** Graphical coordinates */
	private int x = 0;
	private int y = 0;

	/**
	 * Build an attribute graphical description
	 * @param modelAttribute the attribute which is concerned by this graphical description
	 */
	public AttributeGraphicInfo(IAttribute modelAttribute) {
		this.attribute = modelAttribute;
	}

	/** {@inheritDoc} */
	public final Point getLocation() {
		return new Point(this.x, this.y);
	}

	/**
	 * Set the location
	 * @param x x position
	 * @param y y position
	 */
	private void setLocation(int x, int y) {
		Point oldValue = new Point(this.x, this.y);
		this.x = x;
		this.y = y;
		Point newValue = new Point(this.x, this.y);

		// Throw an event ! (This event will be caught by the controller, and will eventually trigger a view update...)
		((AttributeModel) this.attribute).firePropertyChange(ILocationInfo.LOCATION_PROP, oldValue, newValue);
	}

	/** {@inheritDoc} */
	public final void setLocation(Point location) {
		if (location == null) { return; }
		setLocation(location.x, location.y);
	}
	
	public final void resetLocation() {
		setLocation(-1, -1);
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
		return !attribute.getAttributeFormalism().isDrawable();
	}
}

