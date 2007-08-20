package fr.lip6.move.coloane.interfaces.concretemodel;

import fr.lip6.move.coloane.interfaces.model.Arc;

/**
 * Arc concret pour permettre les tests unitaires
 */
public class ConcreteArc extends Arc {

	/**
	 * Constructeur
	 * @param arcType Le type d'arc
	 */
	public ConcreteArc(String arcType) {
		super(arcType);
	}

	/**
	 * Constructeur
	 * @param arcType Le type d'arc
	 * @param id L'identifiant de l'arc
	 */
	public ConcreteArc(String arcType, int id) {
		super(arcType, id);
	}

	// Pas besoin de traduction pour ce type d'arc
	public final String[] translate() {
		return null;
	}
}
