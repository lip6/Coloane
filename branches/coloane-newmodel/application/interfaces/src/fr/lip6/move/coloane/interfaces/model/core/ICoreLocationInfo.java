package fr.lip6.move.coloane.interfaces.model.core;

import org.eclipse.draw2d.geometry.Point;

/**
 * Interface définissant les méthodes que doivent implémenter les objets postionnables sur un éditeur
 */
public interface ICoreLocationInfo {
	
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

}
