package fr.lip6.move.coloane.interfaces.model;

import org.eclipse.swt.graphics.Color;

public interface IAttributeGraphicInfo extends ILocationInfo {

	/**
	 * @return couleur de fond
	 */
	Color getBackground();

	/**
	 * @param background
	 */
	void setBackground(Color background);

	/**
	 * @return couleur du noeud
	 */
	Color getForeground();

	/**
	 * @param foreground
	 */
	void setForeground(Color foreground);

}
