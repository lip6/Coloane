package fr.lip6.move.coloane.exceptions;

/**
 * Class exception 
 * Cette exception est utilisée par DialogTool
 * @author Albert DALODE
 */

public class DialogException extends Exception {
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
	public  DialogException ( String s ) {
		super(s);
		msg = s;
	}
	
	/**
	 * @return string message d'erreur
	 */
	public String getMessage() {
		return msg;
	}
}
