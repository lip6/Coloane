package fr.lip6.move.coloane.core.results;

import java.util.List;

/**
 * Arbre de résultat<br>
 * Les lignes sont décrites par l'arborescence et les colonnes par la liste
 * d'élément.
 */
public interface IResultTree {
	public IResultTree getParent();

	public void setParent(IResultTree parent);

	/**
	 * @return liste des fils
	 */
	public List<IResultTree> getChildren();
	
	/**
	 * Permet de rajouter un fils à ce noeud
	 * @param child
	 */
	public void addChild(IResultTree child);

	/**
	 * @return liste d'objet représentant une ligne de l'arbre des résultats
	 */
	public List<Object> getElement();
	
	/**
	 * @return l'id associe à ce noeud
	 */
	public int getId();
	
	/**
	 * Supprime le noeud courrant ainsi que tous les fils associés
	 */
	public void remove();
}
