package fr.lip6.move.coloane.interfaces.exceptions;

/**
 * Syntax Error Exception
 * <ul>
 *  <li>Erreur de syntaxe dans la reception ou l'envoi de commandes a la plateforme</li>
 *  <li>Erreur lors de la construction du modele</li>
 * </ul>
 */
public class SyntaxErrorException extends Exception {

	/** Pour la conformite */
	private static final long serialVersionUID = 1L;

	/** Message associee a l'exception */
	private String message;

	/** Constructeur */
	public SyntaxErrorException() {
		this.message = "Unknown Syntax Error";
	}

	/**
	 * Construteur
	 * Ce construteur permet d'associer un message d'erreur a l'exception qu'on lève
	 * @param message Message de détails
	 */
	public SyntaxErrorException(final String message) {
		this.message = "Syntax Error : " + message;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMessage() {
		return this.message;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String toString() {
		return this.message;
	}
}
