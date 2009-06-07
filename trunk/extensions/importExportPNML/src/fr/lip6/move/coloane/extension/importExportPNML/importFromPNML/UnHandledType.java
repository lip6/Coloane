package fr.lip6.move.coloane.extension.importExportPNML.importFromPNML;

/**
 * Unsupported Petri Net type
 * @author Guillaume Giffo
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
	 * @param msg the message used to describe the error.
	 */
	public UnHandledType(String msg) {
		super(msg);
	}

	/**
	 * Constructor with the cause of the exception.
	 * @param cause The cause.
	 */
	public UnHandledType(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor with message and cause of the exception.
	 * @param msg The message used to describe the error
	 * @param cause The cause
	 */
	public UnHandledType(String msg, Throwable cause) {
		super(msg, cause);
	}
}
