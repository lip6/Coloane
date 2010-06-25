package fr.lip6.move.coloane.interfaces.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Color;

/**
 * Graphical properties and behaviors of an attribute
 * @see IAttribute
 * 
 * @author Jean-Baptiste Voron
 */
public interface IAttributeGraphicInfo extends ILocationInfo {

	/**
	 * @return background color
	 */
	Color getBackground();

	/**
	 * @param background color
	 */
	void setBackground(Color background);

	/**
	 * @return foreground color
	 */
	Color getForeground();

	/**
	 * @param foreground color
	 */
	void setForeground(Color foreground);

	/**
	 * @return <code>true</code> if the attribute has to be displayed
	 */
	boolean isVisible();

	/**
	 * @return size of the attribute
	 */
	Dimension getSize();
}
