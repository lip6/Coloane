package fr.lip6.move.coloane.core.exceptions;

/**
 * Exception utilisee pour faire remonter les erreurs de construction de modele.
 */
public class BuildException extends Exception {
	/** Utilise pour la serialisation */
	private static final long serialVersionUID = 1L;

	/** Message d'information sur l'exception */
	private String message;

	/**
	 * Constructeur de la classe d'exception
	 * @param msg Message associee a l'exception
	 */
	public BuildException(String msg) {
		super(msg);
		message = msg;
	}

	/**
	 * Retourne le message associe a l'exception
	 * @return Le message d'erreur
	 */
	@Override
	public final String getMessage() {
		return Messages.BuildException_0 + message;
	}
}
