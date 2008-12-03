package fr.lip6.move.coloane.interfaces.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Color;

/**
 * Interface des information graphique concernant un noeud
 * @see fr.lip6.move.coloane.interfaces.model.impl.interfaces.NodeGraphicInfo
 */

public interface INodeGraphicInfo extends ILocationInfo {

	/**
	 * Retourne un booleen indiquant si la figure est remplie ou non.
	 * @return boolean
	 */
	boolean isFilled();

	/**
	 * @return couleur de fond
	 */
	Color getBackground();

	/**
	 * @param background La couleur de fond souhaitée
	 */
	void setBackground(Color background);

	/**
	 * @return couleur du noeud
	 */
	Color getForeground();

	/**
	 * @param foreground La couleur d'avant plan souhaitée
	 */
	void setForeground(Color foreground);

	/**
	 * Permet de définir la taille du noeud
	 * @param scale pourcentage d'agrandissement
	 */
	void setScale(int scale);

	/**
	 * @return pourcentage
	 */
	int getScale();

	/**
	 * @return Dimension du noeud
	 */
	Dimension getSize();
}
