package fr.lip6.move.coloane.exceptions;

public class MenuNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	/**
	 * Constructeur simple
	 */
	public MenuNotFoundException() { }

	/**
	 * Constructeur avec indication
	 * @param name Nom du menu a construire
	 * @param parent Nom du parent du menu
	 */
	public MenuNotFoundException(String name, String parent) {
		this.message = parent + "->" + name;
	}

	@Override
	public final String toString() {
		return this.message;
	}

}
