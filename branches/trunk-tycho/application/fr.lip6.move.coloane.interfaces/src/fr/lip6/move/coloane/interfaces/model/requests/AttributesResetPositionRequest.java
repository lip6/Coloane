package fr.lip6.move.coloane.interfaces.model.requests;

import fr.lip6.move.coloane.interfaces.model.IElement;

/**
 * Ask to reset all attribute positions of an {@link IElement} to their default positions
 * (i.e. relative to the node or arc they describe)
 *
 * @author Yann Thierry-Mieg, based on Jean-Baptiste Voron
 */
public class AttributesResetPositionRequest implements IRequest {
	
	/** The object that holds the attributes to reset */
	private IElement parentObject;

	/**
	 * Constructor
	 * @param parentObject The object that holds the attributes to reset
	 */
	public AttributesResetPositionRequest(IElement parentObject) {
		this.parentObject = parentObject;
	}

	/**
	 * @return The parent object that holds the attributes asked to be reseted
	 */
	public IElement getParentObject() {
		return parentObject;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getRequestType() {
		return IRequest.ATTRIBUTE_RESET_POSITION_REQUEST;
	}
}
