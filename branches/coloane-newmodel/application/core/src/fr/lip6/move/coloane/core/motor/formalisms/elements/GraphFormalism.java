package fr.lip6.move.coloane.core.motor.formalisms.elements;

import java.util.ArrayList;
import java.util.List;

import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;

/**
 * Cette classe décrit un conteneur de noeuds, d'arcs et d'attributs d'un formalisme.<br>
 * Chaque instance de formalisme contient forcément au moins un élément de ce type.<br>
 * Dans le cas de la hiérachie, ce conteneur peut être contenu dans un noeud de plus haut niveau.
 */
public class GraphFormalism extends ElementFormalism implements IGraphFormalism {
	
	private List<IElementFormalism> children = new ArrayList<IElementFormalism>();

	/**
	 * Constructeur
	 * @param name Le nom du graphe
	 * @param formalism Le formalisme qui définit ce graphe
	 */
	public GraphFormalism(String name, IFormalism formalism) {
		super(name, formalism);
	}
	
	/**
	 * Ajout d'un element de base a la definition d'un graphe. Notion de hierarchie
	 * @param element {@link ElementFormalism} de base a ajouter aux enfants possibles du graphe.
	 */
	public final void addElement(ElementFormalism element) {
		if (element == null) { return; }
		this.children.add(element);
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism#getChildren()
	 */
	public List<IElementFormalism> getChildren() {
		return this.children;
	}
}
