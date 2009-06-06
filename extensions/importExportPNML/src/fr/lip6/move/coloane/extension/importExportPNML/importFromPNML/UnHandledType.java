package fr.lip6.move.coloane.extension.importExportPNML.importFromPNML;

/**
 * 
 * @author Guillaume Giffo
 * 
 */
public class UnHandledType extends Exception {
	/**
	 * Serialization stuff.
	 */
	private static final long serialVersionUID = -544239706092600793L;

	/**
	 * Default constructor.
	 */
	public UnHandledType() {
		super();
	}

	/**
	 * Constructor with message.
	 * 
	 * @param mssg
	 *            the message.
	 */
	public UnHandledType(String mssg) {
		super(mssg);
	}

	/**
	 * Constructor with message and cause of the exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public UnHandledType(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor with the cause of the exception.
	 * 
	 * @param cause
	 *            the cause.
	 */
	public UnHandledType(Throwable cause) {
		super(cause);
	}
}
