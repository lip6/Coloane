package fr.lip6.move.coloane.interfaces.api.exceptions;

/**
 * Cette classe répresent une exception au niveau de l'API.
 */
public class ApiException extends Exception {

	/**
	 * Genere automatiquement par Eclipse
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de l'exception
	 * @param message Information sur l'exception
	 */
	public ApiException(String message) {
		super(message);
	}

}
