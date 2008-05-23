package fr.lip6.move.coloane.core.results;

import java.util.List;

/**
 * Arbre de résultats<br>
 * Les lignes sont décrites par l'arborescence et les colonnes par la liste d'élément.
 */
public interface IResultTree {

	/**
	 * Retourne le père du sous-arbre
	 * @return l'arbre parent
	 */
	IResultTree getParent();

	/**
	 * Indique qui est le père de l'arbre de résultats.<br>
	 * Attention ! Seul le lien fils -> pere est fait.<br>
	 * Utilisez de preference la methode {@link #addChild(IResultTree)}
	 * @param parent L'arbre de resultat parent
	 */
	void setParent(IResultTree parent);

	/**
	 * Retourne la liste des fils
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
	 * Retourne la liste des elements a mettre en valeur lors de la selection de ce resultat
	 * @return la liste d'identifiants d'objets a mettre en valeur
	 */
	List<Integer> getHighlighted();

	/**
	 * Ajoute un element a mettre en valeur lors de la selection du sous-resultat
	 * @param toHighlight
	 */
	void addHighlighted(int... toHighlight);

	/**
	 * Supprime le noeud courrant ainsi que tous les fils associés
	 */
	void remove();
}
