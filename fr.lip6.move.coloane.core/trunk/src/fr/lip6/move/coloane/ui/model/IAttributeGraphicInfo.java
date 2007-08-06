package fr.lip6.move.coloane.ui.model;

import org.eclipse.draw2d.geometry.Point;

public interface IAttributeGraphicInfo {

	/**
	 * Types d'affichage
	 */

	/* Niveau 1 */
	int L1 = 1;

	/* Niveau 2 */
	int L2 = 2;

	/* Affichage normal */
	int NOR = 3;


	/**
	 * Retourne l'emplacement actuel de l'attribut
	 * @return Point
	 */
	Point getLocation();

	/**
	 * Change l'emplacement de l'attribut
	 * @param x Les abcisses
	 * @param y Les ordonees
	 */
	void setLocation(int x, int y);
}
