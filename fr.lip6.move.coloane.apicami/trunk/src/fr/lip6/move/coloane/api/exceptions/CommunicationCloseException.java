package fr.lip6.move.coloane.api.exceptions;

/**
 * Exception levee lorsqu'on essaie de lire sur la socket alors que celle-ci est fermee
 */
public class CommunicationCloseException extends Exception {

	/**
	 * Identifie l'exception pour la serialisation
	 */
	static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 * @param message Message d'erreur supplementaire
	 */
	public CommunicationCloseException(String message) {
		super(message);
	}
}
