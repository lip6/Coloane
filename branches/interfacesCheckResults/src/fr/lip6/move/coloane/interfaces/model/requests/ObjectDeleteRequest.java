package fr.lip6.move.coloane.interfaces.model.requests;

import fr.lip6.move.coloane.interfaces.model.IElement;

/**
 * Ask the core project to remove an object.<br>
 *
 * @author Jean-Baptiste Voron
 */
public class ObjectDeleteRequest implements IRequest {
	/** The element to remove from the model */
	private IElement element;

	/**
	 * Constructor
	 * @param element The element to remove from the model
	 */
	public ObjectDeleteRequest(IElement element) {
		this.element = element;
	}

	/**
	 * @return the element to remove from the model
	 */
	public IElement getElement() {
		return element;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getRequestType() {
		return IRequest.OBJECT_DELETE_REQUEST;
	}
}
