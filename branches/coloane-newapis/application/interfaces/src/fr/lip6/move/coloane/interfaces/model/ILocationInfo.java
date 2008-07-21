package fr.lip6.move.coloane.interfaces.model;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

/**
 * Définition des propriétés et comportements des objets postionnables sur un éditeur
 */
public interface ILocationInfo {

	/**
	 * @return Les informations concernant le positionnement de l'objet
	 * @see ILocationInfo
	 */
	Point getLocation();

	/**
	 * Indique la nouvelle position de l'objet
	 * @param newLocation Un point indiquant la nouvelle position
	 */
	void setLocation(Point newLocation);

	/**
	 * @return La taille de l'objet
	 */
	Dimension getSize();

	/**
	 * Indique la nouvelle taille de l'objet
	 * @param newDimension Les nouvelles dimensions à prendre en compte
	 */
	void setSize(Dimension newDimension);
}
