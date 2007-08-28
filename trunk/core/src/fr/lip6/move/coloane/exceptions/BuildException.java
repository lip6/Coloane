package fr.lip6.move.coloane.exceptions;

/**
 * Exception utilisee pour faire remonter les erreurs de construction de modele.
 */
public class BuildException extends Exception {
	/** Utilisé pour la serialisation */
	private static final long serialVersionUID = 1L;

	/** Message d'information sur l'exception */
	private String message;

	/**
	 * Constructeur de la classe
	 * @param s message associatée à l'exception
	 */
	public BuildException(String msg) {
		super(msg);
		message = msg;
	}

	/**
	 * Retourne le message associe a l'exception
	 * @return string message d'erreur
	 */
	@Override
	public final String getMessage() {
		return Messages.BuildException_0 + message;
	}
}
