package fr.lip6.move.coloane.ui.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

/**
 * Interface des information graphique concernant un noeud
 * @see fr.lip6.move.coloane.ui.model.NodeGraphicInfo
 */

public interface INodeGraphicInfo {

	// Style de sa figure
	 /** ID pour circle */
	int FIG_CIRCLE = 0;

	/** ID pour rectangle */
	int FIG_RECT = 1;

	/** ID pour double-circle */
	int FIG_DBLCIRCLE = 2;

	/** ID pour la queue.*/
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
	 * Retourne les dimensions du noeud telles que prevues par le formalisme
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
}
