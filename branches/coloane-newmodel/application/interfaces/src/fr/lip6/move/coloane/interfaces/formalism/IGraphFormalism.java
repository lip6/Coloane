package fr.lip6.move.coloane.interfaces.formalism;

import java.util.List;

/**
 * Cette classe décrit un conteneur de noeuds, d'arcs et d'attributs d'un formalisme.<br>
 * Chaque instance de formalisme contient forcément au moins un élément de ce type.<br>
 * Dans le cas de la hiérachie, ce conteneur peut être contenu dans un noeud de plus haut niveau.
 */
public interface IGraphFormalism extends IElementFormalism {
	
	/**
	 * @return la liste de tous les éléments fils de ce graphe
	 */
	List<IElementFormalism> getChildren();

}
