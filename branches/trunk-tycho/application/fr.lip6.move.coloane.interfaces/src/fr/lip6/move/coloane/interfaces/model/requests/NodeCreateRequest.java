package fr.lip6.move.coloane.interfaces.model.requests;

import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;

import org.eclipse.draw2d.geometry.Point;

/**
 * Ask the core project to create a new node.<br>
 * User could specify the initial position of this new node.
 *
 * @author Jean-Baptiste Voron
 */
public class NodeCreateRequest implements IRequest {

	/** The formalism that describes the new node */
	private INodeFormalism formalism;

	/** Initial position */
	private Point initialPosition;

	/**
	 * Constructor
	 * @param formalism The formalism that describes the new node
	 */
	public NodeCreateRequest(INodeFormalism formalism) {
		new NodeCreateRequest(formalism, new Point());
	}
	
	/**
	 * Constructor that proposes to specify an initialPosition for the new node
	 * @param formalism The formalism that describes the new node
	 * @param initialPosition The initial position of the created node
	 */
	public NodeCreateRequest(INodeFormalism formalism, Point initialPosition) {
		this.formalism = formalism;
		this.initialPosition = initialPosition;
	}

	/**
	 * @return The formalism that describes the new node
	 */
	public INodeFormalism getFormalism() {
		return formalism;
	}

	/**
	 * @return The desired initial position for the new node
	 */
	public Point getInitialPosition() {
		return initialPosition;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getRequestType() {
		return IRequest.NODE_CREATE_REQUEST;
	}
}
