package fr.lip6.move.coloane.exceptions;

public class ColoaneException extends Exception {

	/** Pour la serialisation */
	private static final long serialVersionUID = 1L;

	/** Message d'information sur l'exception */
	private String msg;

	/**
	 * Constructeur de la classe
	 * @param s message associatée à l'exception
	 */
	public ColoaneException(String message) {
		super(message);
		msg = message;
	}

	@Override
	public final String toString() {
		return ("Coloane Internal Exception : " + this.msg);
	}
}
