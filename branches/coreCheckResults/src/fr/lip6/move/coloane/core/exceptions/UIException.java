package fr.lip6.move.coloane.core.exceptions;

/**
 * Exception utilisee dans le cas d'erreurs liees a l'interface utilisateur
 * <ul>
 * 	<li>Affichage incorrect d'un menu</li>
 * 	<li>Mauvaise construction d'une boite de dialogue</li>
 * </ul>
 */
public class UIException extends Exception {
	/** Utilise pour la serialisation */
	private static final long serialVersionUID = 1L;

	/** Message d'information sur l'exception */
	private String message;

	/**
	 * Constructeur de la classe
	 * @param msg Message associe a l'exception
	 */
	public  UIException(String msg) {
		super(msg);
		message = msg;
	}

	/**
	 * Retourne le message associe a l'exception
	 * @return Le message d'erreur
	 */
	@Override
	public final String getMessage() {
		return Messages.UIException_0 + message;
	}
}
