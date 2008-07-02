package fr.lip6.move.coloane.core.motor.formalisms.constraints;

import fr.lip6.move.coloane.core.motor.formalisms.elements.ElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;

/**
 * Definition d'une contrainte pour la connexion de 2 éléments de formalisme<br>
 * Cette contrainte <b>interdit</b> la connexion entre l'élément <code>source</code> et l'élément <code>target</code>
 */
public class ConnectionConstraint implements IConstraint {

	/** Element en entree de l'arc. */
	private IElementFormalism source;

	/** Element en sortie de l'arc. */
	private IElementFormalism target;

	/**
	 * Constructeur
	 * Etablit quelles sont les connexions impossibles
	 * @param in élément source de l'arc
	 * @param out élément cible de l'arc
	 */
	public ConnectionConstraint(IElementFormalism source, IElementFormalism target) {
		this.source = source;
		this.target = target;
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraint#isSatisfied(fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement, fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement)
	 */
	public boolean isSatisfied(IElementFormalism source, IElementFormalism target) {
		return (!(this.source.equals(source)) || !(this.target.equals(target)));
	}

	/**
	 * Retourne l'element en entree de l'arc.
	 * @return {@link ElementFormalism}
	 */
	public final IElementFormalism getSource() {
		return this.source;
	}

	/**
	 * Retourne l'element en sortie de l'arc.
	 * @return {@link ElementFormalism}
	 */
	public final IElementFormalism getTarget() {
		return this.target;
	}
}
