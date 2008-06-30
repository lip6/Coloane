package fr.lip6.move.coloane.core.motor.formalisms.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe décrit un conteneur de noeuds, d'arcs et d'attributs d'un formalisme.<br>
 * Chaque instance de formalisme contient forcément au moins un élément de ce type.<br>
 * Dans le cas de la hiérachie, ce conteneur peut être contenu dans un noeud de plus haut niveau.
 */
public class Graph extends FormalismElement {
	
	private List<FormalismElement> children = new ArrayList<FormalismElement>();

	/**
	 * Constructeur
	 * @param name Le nom du graphe
	 */
	public Graph(String name) {
		super(name);
	}
	
	/**
	 * Ajout d'un element de base a la definition d'un graphe. Notion de hierarchie
	 * @param element {@link FormalismElement} de base a ajouter aux enfants possibles du graphe.
	 */
	public final void addElement(FormalismElement element) {
		if (element == null) { return; }
		this.children.add(element);
	}
}
