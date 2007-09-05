package fr.lip6.move.coloane.motor.formalism;

/**
 * Regle de connexion pour un formalisme
 */
public class Rule {

	/** Element en entree de l'arc. */
	private ElementBase elementIn;

	/** Element en sortie de l'arc. */
	private ElementBase elementOut;

	/**
	 * Constructeur
	 * Etablit quelles sont les connexions impossibles
	 * @param eltIn Element en entree
	 * @param eltOut Element en Sortie
	 */
	public Rule(ElementBase eltIn, ElementBase eltOut) {
		this.elementIn = eltIn;
		this.elementOut = eltOut;
	}

	/**
	 * Retourne l'element en entree de l'arc.
	 * @return ElementBase
	 * @see ElementBase
	 */
	public final ElementBase getElementIn() {
		return elementIn;
	}

	/**
	 * Retourne l'element en sortie de l'arc.
	 * @return ElementBase
	 * @see ElementBase
	 */
	public final ElementBase getElementOut() {
		return elementOut;
	}
}
