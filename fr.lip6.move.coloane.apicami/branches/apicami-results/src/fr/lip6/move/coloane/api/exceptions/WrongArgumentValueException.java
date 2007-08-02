package fr.lip6.move.coloane.api.exceptions;

/**
 * Exception levee lorsqu'on construit un objet avec un mauvais argument entier passe en parametre
 */
public class WrongArgumentValueException extends Exception {

	/**
	 * Identifie l'exception pour la serialisation
	 */
	static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur 
	 * @param message message d'erreur supplementaire
	 */
	public WrongArgumentValueException(String message) {
		super(message);
	}
}
