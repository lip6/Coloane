package fr.lip6.move.coloane.exceptions;


/**
 * Class exception
 * Cette exception est utilis�e par Authentiticationdialog
 * Exception relev�es quand l'utilisateur donne un login ou un mot de passe vide
 *
 * @author Albert DALODE
 */

public class GuiAuthenticationException extends Exception {


	/**
	 * Utilis� pour la serialisation
	 */
	private static final long serialVersionUID = -3125492952382051817L;

	/**
	 * Message d'information sur l'exception
	 */
	private String msg;

	/**
	 * Constructeur de la classe
	 * @param s message associ�e � l'excpetion
	 */
	public  GuiAuthenticationException(String message) {
		super(message);
		msg = message;
	}

	/**
	 * @return string message d'erreur
	 */
	public final String getMessage() {
		return msg;
	}

}

