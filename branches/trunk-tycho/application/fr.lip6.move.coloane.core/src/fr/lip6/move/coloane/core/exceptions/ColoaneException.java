package fr.lip6.move.coloane.core.exceptions;

/**
 * Main exception used in case of unrecoverable error. 
 */
public class ColoaneException extends Exception {
	/** Serialize */
	private static final long serialVersionUID = 1L;

	/** Information message about the exception */
	private String message;

	/**
	 * Constructor
	 * @param message the information message about the exception
	 */
	public ColoaneException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * @return Return the error message
	 */
	@Override
	public final String getMessage() {
		return message;
	}

	/**
	 * @return Return the error message with an header
	 */
	public final String getLogMessage() {
		return Messages.ColoaneException_0 + message;
	}
}
