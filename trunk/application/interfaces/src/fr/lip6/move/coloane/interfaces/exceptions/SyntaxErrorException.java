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
	private String msg;

	/** Constructeur */
	public SyntaxErrorException() {
		super();
	}

	/**
	 * Construteur
	 * Ce construteur permet d'associer un message d'erreur a l'exception qu'on leve
	 * @param message Me message d'explication
	 */
	public SyntaxErrorException(final String message) {
		super(message);
		this.msg = message;
	}

	/**
	 * Production d'une chaine de caractere depuis l'exception
	 * @return string Le messaeg complet d'erreur
	 */
	public final String toString() {
		if (msg != null) {
			return new String("Syntax Error Exception : " + msg);
		} else {
			return "Syntax Error";
		}
	}
}
