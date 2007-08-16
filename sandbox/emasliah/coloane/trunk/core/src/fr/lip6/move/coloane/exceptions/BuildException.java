package fr.lip6.move.coloane.exceptions;

/**
 * Exception utilisee pour faire remonter les erreurs
 * de conception de modele.
 */

public class BuildException extends Exception {
	/**
	 * Utilisé pour la serialisation
	 */
	private static final long serialVersionUID = 3722590923091130674L;


	/**
	 * Message d'information sur l'exception
	 */
	private String msg;

	/**
	 * Constructeur de la classe
	 * @param s message associatée à l'exception
	 */
	public BuildException(String s) {
		super(s);
		msg = s;
	}

	/**
	 * @return string message d'erreur
	 */
	public final String getMessage() {
		return msg;
	}
}
