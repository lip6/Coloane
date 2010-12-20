package fr.lip6.move.coloane.interfaces.model.requests;

import fr.lip6.move.coloane.interfaces.model.IArc;

/**
 * Ask the core project to remove all inflex points from a given arc.<br>
 *
 * @author Jean-Baptiste Voron
 */
public class InflexPointsDeleteRequest implements IRequest {

	/** The arc for which all inflex points should be removed */
	private IArc arc; 
	
	/**
	 * Constructor
	 */
	public InflexPointsDeleteRequest(IArc arc) {
		this.arc = arc;
	}
	
	/**
	 * @return the arc for which all inflex point should be removed
	 */
	public IArc getArc() {
		return arc;
	}
	
	/** {@inheritDoc} */
	public int getRequestType() {
		return IRequest.INFLEXPOINTS_DELETE_REQUEST;
	}
}
