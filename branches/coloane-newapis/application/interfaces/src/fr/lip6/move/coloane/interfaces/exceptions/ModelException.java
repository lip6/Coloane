package fr.lip6.move.coloane.interfaces.exceptions;

/**
 * Model Exception<br>
 * Exception levée lors de la manipulation du modèle :
 * <ul>
 * 	<li>Ajout</li>
 *  <li>Modification</li>
 *  <li>Suppression</li>
 *  <li>Déplacement</li>
 *  <li>etc...</li>
 * </ul>
 */
public class ModelException extends Exception {

	/** Pour la conformité */
	private static final long serialVersionUID = 1L;

	/** Message associee a l'exception */
	private String message;

	/** Constructeur */
	public ModelException() {
		this.message = "Unknown Model Error"; //$NON-NLS-1$
	}

	/**
	 * Consutructeur de l'exception
	 * @param message Information sur l'exception
	 */
	public ModelException(final String message) {
		this.message = "Model Error : " + message; //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getMessage() {
		return this.message;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String toString() {
		return message;
	}
}
