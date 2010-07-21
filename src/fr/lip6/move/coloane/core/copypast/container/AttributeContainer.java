package fr.lip6.move.coloane.core.copypast.container;

import fr.lip6.move.coloane.interfaces.model.IAttribute;
import org.eclipse.draw2d.geometry.Point;

/**
 * Class that allow to re-build an attribute.<br>
 * This operation is used when a user copy/cut/paste an element from the model.
 * 
 * @author Clément Démoulins
 */
public class AttributeContainer {
	/** The ID of the reference element */
	private int id;
	/** The name of the attribute*/
	private String name;
	/** The value of the attribute */
	private String value;
	/** The location of the attribute */
	private Point location;

	/**
	 * Constructor
	 * @param attribute The attribute to consider
	 */
	public AttributeContainer(IAttribute attribute) {
		this.name = attribute.getName();
		this.value = attribute.getValue();
		this.location = attribute.getGraphicInfo().getLocation().getCopy();
	}

	/**
	 * @return The ID of the reference model object
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @return The attribute value
	 */
	public final String getValue() {
		return value;
	}

	/**
	 * @return The attribute location
	 */
	public final Point getLocation() {
		return location;
	}

	/**
	 * @return The attribute name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Set the attribute container position
	 * @param x The new x position
	 * @param y The new y position
	 */
	public final void setLocation(Point newLocation) {
		this.location = newLocation;
	}
}
