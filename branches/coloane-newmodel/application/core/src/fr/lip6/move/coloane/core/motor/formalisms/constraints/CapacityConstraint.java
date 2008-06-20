package fr.lip6.move.coloane.core.motor.formalisms.constraints;

import fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Graph;

/**
 * Definition d'une contrainte sur le nombre d'élément qui peuvent être contenu<br>
 * Cette contrainte <b>interdit</b> à un élement de formalisme de contenir un {@link Graph} 
 * de plus de {@link #max} objets
 */
public class CapacityConstraint implements IConstraint {

	/** Elément sur lequel s'applique la contrainte */
	private FormalismElement element;

	/** Le nombre maximum d'objets contenu dans le graphe fils du noeud */
	private int max;
	
	/**
	 * Constructeur<br>
	 * Définit les valeurs de la contrainte.<br>
	 * @param element L'élement de formalisme sur lequel s'applique la contrainte
	 * @param max Nombre maximum d'élément pouvant être contenu dans le graphe fils du noeud
	 * @see {@link Graph}
	 */
	public CapacityConstraint(FormalismElement element, int max) {
		this.element = element;
		this.max = max;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraint#isSatisfied(fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement, fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement)
	 */
	public boolean isSatisfied(FormalismElement source, FormalismElement target) {
		// TODO: A définir !
		return true;
	}

	/**
	 * Retourne l'élement concerné
	 * @return {@link FormalismElement}
	 */
	public FormalismElement getElement() {
		return element;
	}

	/**
	 * @return Retourne le nombre maximum d'objets du {@link Graph} fils du noeud
	 */
	public int getMax() {
		return this.max;
	}
}
