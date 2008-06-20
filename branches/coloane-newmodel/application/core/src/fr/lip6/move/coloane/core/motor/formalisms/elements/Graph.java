package fr.lip6.move.coloane.core.motor.formalisms.elements;

/**
 * Cette classe décrit un conteneur de noeuds, d'arcs et d'attributs d'un formalisme.<br>
 * Chaque instance de formalisme contient forcément au moins un élément de ce type.<br>
 * Dans le cas de la hiérachie, ce conteneur peut être contenu dans un noeud de plus haut niveau.
 */
public class Graph extends FormalismElement {

	/**
	 * Constructeur
	 * @param name Le nom du graphe
	 */
	public Graph(String name) {
		super(name);
	}
}
