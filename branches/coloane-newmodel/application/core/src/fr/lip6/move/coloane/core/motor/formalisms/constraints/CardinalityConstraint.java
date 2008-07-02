package fr.lip6.move.coloane.core.motor.formalisms.constraints;

import fr.lip6.move.coloane.core.motor.formalisms.elements.ElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;

/**
 * Definition d'une contrainte sur le nombre de connexion sur un élement de formalisme<br>
 * Cette contrainte <b>interdit</b> à un élement de formalisme :
 * <ul>
 * 	<li>plus de <code>maxIn</code> arcs en entrée (arcs cibles)</li>
 * 	<li>plus de <code>maxOut</code> arcs en sortie (arcs sources)</li>
 * </ul>
 */
public class CardinalityConstraint implements IConstraint {

	/** Elément sur lequel s'applique la contrainte */
	private IElementFormalism element;

	/** Le nombre maximum d'arcs en entrée (arcs cibles) */
	private int maxIn;
	
	/** Le nombre maximum d'arcs en sortie (arcs sources) */
	private int maxOut;

	/**
	 * Constructeur<br>
	 * Définit les valeurs de la contrainte.<br>
	 * Pour ne pas attribuer une limite pour <code>maxIn</code> ou <code>maxOut</code> : -1<br>
	 * @param element L'élement de formalisme sur lequel s'applique la contrainte
	 * @param maxIn Nombre maximum d'arcs en entrée (arcs cibles)
	 * @param maxOut Nombre maximum d'arcs en sortie (arcs sources)
	 */
	public CardinalityConstraint(IElementFormalism element, int maxIn, int maxOut) {
		this.element = element;
		this.maxIn = maxIn;
		this.maxOut = maxOut;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraint#isSatisfied(fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement, fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement)
	 */
	public boolean isSatisfied(IElementFormalism source, IElementFormalism target) {
		// TODO: A définir !
		return true;
	}

	/**
	 * Retourne l'élement concerné
	 * @return {@link ElementFormalism}
	 */
	public IElementFormalism getElement() {
		return element;
	}

	/**
	 * @return Retourne le nombre maximum d'arcs en entrée autorisé
	 */
	public int getMaxIn() {
		return maxIn;
	}

	/**
	 * @return Retourne le nombre maximum d'arcs en sortie autorisé
	 */
	public int getMaxOut() {
		return maxOut;
	}
}
