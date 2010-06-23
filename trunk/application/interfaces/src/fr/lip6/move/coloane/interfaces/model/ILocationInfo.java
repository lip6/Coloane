package fr.lip6.move.coloane.interfaces.model;

import org.eclipse.draw2d.geometry.Point;

/**
 * Define properties and behaviors of objects that can be moved and located on an editor.
 */
public interface ILocationInfo {

	/** The property ID */
	String LOCATION_PROP = "Location.info"; //$NON-NLS-1$

	/**
	 * @return location information of the current object
	 * @see ILocationInfo
	 */
	Point getLocation();

	/**
	 * Set a new position for this object
	 * @param newLocation a single point that designates the new location
	 */
	void setLocation(Point newLocation);
	
	/**
	 * Reset the location of the current element.<br>
	 * In fact, the location is set to (-1, -1). <br>
	 * The element EditPart is in charge of the new location computation.
	 */
	void resetLocation();
}
