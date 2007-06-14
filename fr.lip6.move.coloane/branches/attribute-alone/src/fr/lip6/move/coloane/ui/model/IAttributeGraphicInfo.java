package fr.lip6.move.coloane.ui.model;

import org.eclipse.draw2d.geometry.Point;

public interface IAttributeGraphicInfo {

	/**
	 * Retourne l'emplacement actuel de l'attribut
	 * @return Point
	 */
	public Point getLocation();

	/**
	 * Change l'emplacement de l'attribut
	 * @param x Les abcisses
	 * @param y Les ordonees
	 */
	public void setLocation(int x, int y);

}