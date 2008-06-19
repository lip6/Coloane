package fr.lip6.move.coloane.core.motor.formalisms.constraints;

import fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement;

/**
 * Definition d'une contrainte pour la connexion de 2 éléments de formalisme<br>
 * Cette contrainte <b>interdit</b> la connexion entre l'élément <code>source</code> et l'élément <code>target</code>
 */
public class ConnectionConstraint implements IConstraint {

	/** Element en entree de l'arc. */
	private FormalismElement source;

	/** Element en sortie de l'arc. */
	private FormalismElement target;

	/**
	 * Constructeur
	 * Etablit quelles sont les connexions impossibles
	 * @param in élément source de l'arc
	 * @param out élément cible de l'arc
	 */
	public ConnectionConstraint(FormalismElement source, FormalismElement target) {
		this.source = source;
		this.target = target;
	}

	/**
	 * Retourne l'element en entree de l'arc.
	 * @return {@link FormalismElement}
	 */
	public final FormalismElement getSource() {
		return this.source;
	}

	/**
	 * Retourne l'element en sortie de l'arc.
	 * @return {@link FormalismElement}
	 */
	public final FormalismElement getTarget() {
		return this.target;
	}
}
