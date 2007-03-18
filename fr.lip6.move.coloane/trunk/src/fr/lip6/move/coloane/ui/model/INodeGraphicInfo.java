package fr.lip6.move.coloane.ui.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

/**
 * Interface pour l'information graphique d'un noeud
 * @see INodeImpl
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
	 * Retourne la position de la figure
	 * @return Point
	 */
	public Point getLocation();

	/**
	 * Indique la nouvelle position du noeud<br>
	 * Doit lever la notification Node.Location
	 * @param newLocation Nouvelle position
	 */
	public void setLocation(Point newLocation);

	/**
	 * Retourne la largeur (en pixels) de la figure
	 * @return int
	 */
	public int getWidth();

	/**
	 * Retourne la hauteur (en pixels) de la figure
	 * @return int
	 */
	public int getHeight();

	/**
	 * Retourne les dimension de la figure
	 * @return Dimension
	 */
	public Dimension getSize();

	/**
	 * Indique la taille de la figure<br>
	 * Doit lever la notification <b>Node.Size</b>
	 * @param newSize nouvelle taille de la figure
	 */
	public void setSize(Dimension newSize);

	/**
	 * Retourne le style de la figure
	 * @return style
	 */
	int getFigureStyle();

	/**
	 * Indique si la taille est modifiable
	 * @return boolean
	 */
	boolean isSizable();
	
	/**
	 * Indique si la figure est pleine
	 * @return boolean 
	 */
	boolean isFilled();

}
