package fr.lip6.move.coloane.interfaces.exceptions;

/**
 * Service Exception.<br>
 * This exception is raised when something went wrong during a service execution.<br>
 * Such an exception has :
 * <ul>
 * 	<li>a type</li>
 * 	<li>a message</li>
 * </ul>
 * 
 * @author Jean-Baptiste Voron
 */
public class ServiceException extends Exception {
	
	/** When the problem is global */
	final static public int GENERAL_ERROR = 0;
	/** When the connection to the service provider is not possible */
	final static public int CONNECTION_ERROR = 1;
	/** When the connection is not possible due to an authentication error */
	final static public int AUTHENTICATION_ERROR = 2;
	
	/** The exception type */
	private int type = -1;
	
	/**
	 * Serialize
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param type The exception type
	 * @param message The message that explains the problem
	 */
	public ServiceException(int type, String message) {
		super(message);
		this.type = type;
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	/**
	 * @return 
	 */
	public String getMessage() {
		switch (this.type) {
		case GENERAL_ERROR:
			return "GENERAL ERROR: \n" + super.getMessage();
		case CONNECTION_ERROR:
			return "CONNECTION ERROR: \n" + super.getMessage();
		case AUTHENTICATION_ERROR:
			return "AUTHENTICATION ERROR: \n" + super.getMessage();
		default:
			return "UNKNOWN ERROR : " + super.getMessage();
		}
	}
}
