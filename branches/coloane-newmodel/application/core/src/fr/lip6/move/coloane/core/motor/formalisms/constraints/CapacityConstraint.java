package fr.lip6.move.coloane.core.motor.formalisms.constraints;

import fr.lip6.move.coloane.core.motor.formalisms.elements.ElementFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.elements.GraphFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;

/**
 * Definition d'une contrainte sur le nombre d'élément qui peuvent être contenu<br>
 * Cette contrainte <b>interdit</b> à un élement de formalisme de contenir un {@link GraphFormalism} 
 * de plus de {@link #max} objets
 */
public class CapacityConstraint implements IConstraint {

	/** Elément sur lequel s'applique la contrainte */
	private IElementFormalism element;

	/** Le nombre maximum d'objets contenu dans le graphe fils du noeud */
	private int max;
	
	/**
	 * Constructeur<br>
	 * Définit les valeurs de la contrainte.<br>
	 * @param element L'élement de formalisme sur lequel s'applique la contrainte
	 * @param max Nombre maximum d'élément pouvant être contenu dans le graphe fils du noeud
	 * @see {@link GraphFormalism}
	 */
	public CapacityConstraint(IElementFormalism element, int max) {
		this.element = element;
		this.max = max;
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
	 * @return Retourne le nombre maximum d'objets du {@link GraphFormalism} fils du noeud
	 */
	public int getMax() {
		return this.max;
	}
}
