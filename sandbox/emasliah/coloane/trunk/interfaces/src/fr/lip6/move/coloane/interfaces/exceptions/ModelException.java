package fr.lip6.move.coloane.interfaces.exceptions;

/**
 * Model Exception
 * Erreur lors de la manipulation du modele :
 * <ul>
 * 	<li>Ajout</li>
 *  <li>Modification</li>
 *  <li>Suppression</li>
 * </ul>
 */
public class ModelException extends Exception {

	/** Pour la conformite */
	private static final long serialVersionUID = 1L;

	/** Message associee a l'exception */
	private String msg;

	/** Constructeur */
	public ModelException() {
		this.msg = "Unknown Model Error";
	}

	/**
	 * Consutructeur de l'exception
	 * @param message Information sur l'exception
	 */
	public ModelException(final String message) {
		this.msg = "Model Error : " + message;
	}

	/**
	 * Production d'une chaine de caractere depuis l'exception
	 * @return string Le messaeg complet d'erreur
	 */
	public final String toString() {
		return new String(msg);
	}
}
