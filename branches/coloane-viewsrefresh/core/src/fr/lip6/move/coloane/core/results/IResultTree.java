package fr.lip6.move.coloane.core.results;

import java.util.List;

/**
 * Arbre de résultat<br>
 * Les lignes sont décrites par l'arborescence et les colonnes par la liste
 * d'élément.
 */
public interface IResultTree {
	IResultTree getParent();

	void setParent(IResultTree parent);

	/**
	 * @return liste des fils
	 */
	List<IResultTree> getChildren();

	/**
	 * Permet de rajouter un fils à ce noeud
	 * @param child
	 */
	void addChild(IResultTree child);

	/**
	 * @return liste d'objet représentant une ligne de l'arbre des résultats
	 */
	List<Object> getElement();

	/**
	 * @return l'id associe à ce noeud
	 */
	int getId();

	/**
	 * Supprime le noeud courrant ainsi que tous les fils associés
	 */
	void remove();
}
