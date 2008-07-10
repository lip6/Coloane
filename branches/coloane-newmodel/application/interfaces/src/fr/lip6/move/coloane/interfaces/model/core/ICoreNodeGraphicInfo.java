package fr.lip6.move.coloane.interfaces.model.core;

import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Color;

/**
 * Interface des information graphique concernant un noeud
 * @see fr.lip6.move.coloane.interfaces.model.impl.interfaces.NodeGraphicInfo
 */

public interface ICoreNodeGraphicInfo extends INodeGraphicInfo, ICoreLocationInfo {

	// Style de sa figure
	 /** ID pour un cercle */
	int FIG_CIRCLE = 0;

	/** ID pour rectangle */
	int FIG_RECT = 1;

	/** ID pour un double-cercle */
	int FIG_DBLCIRCLE = 2;
	int DIFF_CIRCLE = 4;

	/** ID pour une queue (ellipse applatie)*/
	int FIG_QUEUE = 3;

	/**
	 * Retourne la largeur du noeud
	 * @return int La largeur
	 */
	int getWidth();

	/**
	 * Retourne la hauteur du noeud
	 * @return int La hauteur
	 */
	int getHeight();

	/**
	 * Retourne les dimensions du noeud (prend en compte le zoom)
	 * @return Dimension
	 */
	Dimension getSize();

	/**
	 * Retourne un booleen indiquant si la figure est remplie ou non.
	 * @return boolean
	 */
	boolean isFilled();

	/**
	 * @return heure du dernier deplacement
	 */
	long getLastMove();

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

	/**
	 * Permet de définir la taille du noeud
	 * @param zoom pourcentage
	 */
	void setScale(int scale);

	/**
	 * @return pourcentage
	 */
	int getScale();
}
