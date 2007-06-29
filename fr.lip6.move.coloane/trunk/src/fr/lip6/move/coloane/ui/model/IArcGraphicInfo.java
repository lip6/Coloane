package fr.lip6.move.coloane.ui.model;

import org.eclipse.draw2d.geometry.Point;

	/**
	 * Interface pour l'information graphique d'un noeud
	 * @see IARC
	 */
	
public interface IArcGraphicInfo {

	// Style de sa figure
	/** ID pour circle */
	public static final int FIG_ARC_SIMPLE = 0;

	/** ID pour rectangle */
	public static final int FIG_ARC_INHIBITOR = 1;
	
	/**
	 * Retourne le point central de l'arc
	 * TODO : Gerer les arc casses
	 * @return le point milieu
	 */
	public Point findMiddlePoint();
	
	
	/**
	 * Met a jour l'indicateur de point milieu pour l'arc
	 */
	public void updateMiddlePoint();
	
	
	/**
	 * Retourne le point de milieu de l'arc
	 * @return Le point de milieu 
	 */
	public Point getMiddlePoint();
	
}