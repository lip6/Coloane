package fr.lip6.move.coloane.core.ui.model.old;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

/**
 * Interface pour l'information graphique d'un noeud
 * @see IARC
 */

public interface IArcGraphicInfo {

	// Style de l'arc
	/** ID pour fleche normale */
	int FIG_ARC_SIMPLE = 0;

	/** Losange au bout de la fleche */
	int FIG_ARC_INHIBITOR = 1;

	/**
	 * Retourne le point central de l'arc
	 * @return le point milieu
	 */
	Point findMiddlePoint();


	/**
	 * Met a jour l'indicateur de point milieu pour l'arc
	 */
	void updateMiddlePoint();

	/**
	 * Retourne le point de milieu de l'arc
	 * @return Le point de milieu
	 */
	Point getMiddlePoint();

	/**
	 * Retourne le style de dessin de la figure tel que prevu par le formalisme
	 * @return int
	 */
	int getFigureStyle();

	/**
	 * @return couleur de l'arc
	 */
	Color getColor();

	/**
	 * @param color couleur de l'arc
	 */
	void setColor(Color color);
}
