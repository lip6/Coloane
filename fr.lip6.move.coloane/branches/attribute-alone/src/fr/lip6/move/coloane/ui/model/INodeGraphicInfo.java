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
	public static final int FIG_CIRCLE = 0;
	
	/** ID pour rectangle */
	public static final int FIG_RECT = 1;
	
	/** ID pour double-circle */
	public static final int FIG_DBLCIRCLE = 2;
	
	/** ID pour la queue.*/
	public static final int FIG_QUEUE = 3;

	/**
	 * Retourne l'emplacement actuel du noeud
	 * @return Point
	 */
	public Point getLocation();

	/**
	 * Change l'emplacement d'un noeud
	 * @param x Les abcisses
	 * @param y Les ordonees
	 */
	public void setLocation(int x, int y);

	/**
	 * Retourne les dimensions du noeud telles que prevues par le formalisme
	 * @return Dimension 
	 */
	public Dimension getSize();

	/**
	 * Retourne le style de dessin de la figure tel que prevu par le formalisme
	 * @return int
	 */
	public int getFigureStyle();

	/**
	 * Retourne un booleen indiquant si la figure est remplie ou non.
	 * @return boolean
	 */
	public boolean isFilled();
}