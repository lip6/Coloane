package fr.lip6.move.coloane.core.exceptions;

/**
 * Exception utilisee en cas d'erreur importante et non specialisee de Coloane
 */
public class ColoaneException extends Exception {
	/** Utilise pour la serialisation */
	private static final long serialVersionUID = 1L;

	/** Message d'information sur l'exception */
	private String message;

	/**
	 * Constructeur de la classe
	 * @param msg Message associee a l'exception
	 */
	public ColoaneException(String msg) {
		super(msg);
		message = msg;
	}

	/**
	 * Retourne le message associe a l'exception
	 * @return Le message d'erreur
	 */
	@Override
	public final String getMessage() {
		return Messages.ColoaneException_0 + message;
	}
}
