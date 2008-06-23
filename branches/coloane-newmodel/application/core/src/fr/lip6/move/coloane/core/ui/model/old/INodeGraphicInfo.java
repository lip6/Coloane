package fr.lip6.move.coloane.core.ui.model.old;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

/**
 * Interface des information graphique concernant un noeud
 * @see fr.lip6.move.coloane.core.ui.model.old.NodeGraphicInfo
 */

public interface INodeGraphicInfo {

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
	 * Retourne l'emplacement actuel du noeud
	 * @return Point
	 */
	Point getLocation();

	/**
	 * Change l'emplacement d'un noeud
	 * @param x Les abcisses
	 * @param y Les ordonees
	 */
	void setLocation(int x, int y);

	/**
	 * Change l'emplacement d'un noeud
	 * @param location coordonnées
	 */
	void setLocation(Point location);

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
	 * Retourne le style de dessin de la figure tel que prevu par le formalisme
	 * @return int
	 */
	int getFigureStyle();

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
