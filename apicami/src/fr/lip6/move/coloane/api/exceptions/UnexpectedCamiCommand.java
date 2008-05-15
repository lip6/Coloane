package fr.lip6.move.coloane.api.exceptions;

/**
 * Exception leve lorsqu'on construit un objet a partir du CAMI et que les commandes donnees
 * en parametre sont inconnues
 */
public class UnexpectedCamiCommand extends Exception {

	/**
	 * Identifie l'exception pour la serialisation
	 */
	static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 * @param message Message d'erreur supplementaire
	 */
	public UnexpectedCamiCommand(String message) {
		super(message);
	}
}
