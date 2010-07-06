package fr.lip6.move.coloane.interfaces.model.requests;


/**
 * When a user want to perform changes on the model, he/she has to use {@link IRequest}.<br>
 * Those objects hold the information about the desired change 
 * and will be used by the core project to operate model modifications. 
 *
 * @author Jean-Baptiste Voron
 */
public interface IRequest {
	
	static final int ARC_CREATE_REQUEST = 1;
	static final int ATTRIBUTE_CHANGEVALUE_REQUEST = 2;
	static final int ATTRIBUTE_POSITION_REQUEST = 3;
	static final int ATTRIBUTE_RESET_POSITION_REQUEST = 4;
	static final int INFLEXPOINT_CREATE_REQUEST = 5;
	static final int INFLEXPOINTS_DELETE_REQUEST = 6;
	static final int NODE_CREATE_REQUEST = 7;
	static final int OBJECT_POSITION_REQUEST = 8;
	static final int OBJECT_DELETE_REQUEST = 9;
	
	/**
	 * Gives the kind of request... <br>
	 * This method will be used by the core project to transform this request into real commands.<br>
	 * This type is not useful for tools developers.
	 *
	 * @return a RequestType among those defined in {@link IRequest}
	 */
	public int getRequestType();

}
