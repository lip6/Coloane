package fr.lip6.move.coloane.interfaces.model.requests;

import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Ask the core project to remove an object.<br>
 *
 * @author Jean-Baptiste Voron
 */
public class NodeDeleteRequest implements IRequest {
	/** The element to remove from the model */
	private INode element;

	/**
	 * Constructor
	 * @param element The element to remove from the model
	 */
	public NodeDeleteRequest(INode element) {
		this.element = element;
	}

	/**
	 * @return the element to remove from the model
	 */
	public INode getElement() {
		return element;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getRequestType() {
		return IRequest.NODE_DELETE_REQUEST;
	}
}
