package fr.lip6.move.coloane.ui.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

public interface INodeGraphicInfo {
	
	//	 Style de sa figure
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
	 * @param newLocation Nouvelle localisation du noeud
	 */
	public void setLocation(int x, int y);

	/**
	 * Retourne la largeur du noeud telle que prevue par le formalisme
	 * @return int
	 */
	public int getWidth();

	/**
	 * Retourne la hauteur du noeud telle que prevue par le formalisme
	 * @return int
	 */
	public int getHeight();

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
	 * Indique si le redimensionnement est possible
	 * @return boolean 
	 */
	public boolean isSizable();

	/**
	 * Retourne un booleen indiquant si la figure est remplie ou non.
	 * @return boolean
	 */
	public boolean isFilled();

	/**
	 * Modifie la taille de l'objet
	 * Inutile Ici !
	 */
	public void setSize(Dimension newSize);

}