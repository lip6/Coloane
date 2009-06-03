package fr.lip6.move.coloane.interfaces.model;

import org.eclipse.draw2d.geometry.Point;

/**
 * Définition des propriétés et comportements des objets postionnables sur un éditeur
 */
public interface ILocationInfo {

	/** ID pour la propriete lorsqu'un changement de la position */
	String LOCATION_PROP = "Location.info"; //$NON-NLS-1$

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
}
