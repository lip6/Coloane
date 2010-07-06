package fr.lip6.move.coloane.interfaces.model.requests;

import fr.lip6.move.coloane.interfaces.model.IElement;

import org.eclipse.draw2d.geometry.Point;

/**
 * Ask the core project to change the position of an object ({@link IElement}).
 *
 * @author Jean-Baptiste Voron
 */
public class ObjectPositionRequest implements IRequest {
	/** Id of the object to move */
	private IElement element;
	
	/** the new position for the object*/
	private Point newPosition;

	/**
	 * Constructor
	 * @param element The element to move
	 * @param newPosition The new position for this object
	 */
	public ObjectPositionRequest(IElement element, Point newPosition) {
		this.element = element;
		this.newPosition = newPosition;
	}
	
	/**
	 * @return The element to move
	 */
	public IElement getElement() {
		return element;
	}
	
	/**
	 * @return The new position for the considered object
	 */
	public Point getNewPosition() {
		return newPosition;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getRequestType() {
		return IRequest.OBJECT_POSITION_REQUEST;
	}
}
