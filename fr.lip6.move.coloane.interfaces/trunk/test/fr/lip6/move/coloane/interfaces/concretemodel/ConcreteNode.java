package fr.lip6.move.coloane.interfaces.concretemodel;

import fr.lip6.move.coloane.interfaces.model.Node;

/**
 * Noeud concret pour le passage des tests unitaires
 */
public class ConcreteNode extends Node {

	/**
	 * Constructeur
	 * @param nodeType Le type du noeud
	 */
	public ConcreteNode(String nodeType) {
		super(nodeType);
	}

	/**
	 * Constructeur
	 * @param nodeType Le type du noeud
	 * @param x Position en X
	 * @param y Position en Y
	 */
	public ConcreteNode(String nodeType, int x, int y) {
		super(nodeType, x, y);
	}

	/**
	 * Constructeur
	 * @param nodeType Le type du noeud
	 * @param x Position en X
	 * @param y Position en Y
	 * @param id L'identifiant du noeud
	 */
	public ConcreteNode(String nodeType, int x, int y, int id) {
		super(nodeType, x, y, id);
	}

	// Pas besoin de traduction pour cet objet
	@Override
	public final String[] translate() {
		return null;
	}
}
