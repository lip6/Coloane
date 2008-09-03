package fr.lip6.move.coloane.interfaces.formalism;

import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Définition d'un formalisme.<br>
 */
public interface IFormalism {

	/**
	 * Indique si la liaison entre deux élément est possible
	 * @param source Element (modele) source de l'arc
	 * @param target Element (modele) cible de l'arc
	 * @return <code>true</code> si la liaison est possible
	 */
	boolean isLinkAllowed(INode source, INode target);

	/**
	 * Indique si l'action sur un noeud du modele est envisageable
	 * @param node Element (modele) sur lequel l'action est entreprise
	 * @return <code>true</code> si la liaison est possible
	 */
	boolean isActionAllowed(INode node);

	/**
	 * @return l'id du formalisme
	 */
	String getId();

	/**
	 * @return Le nom du formalisme
	 */
	String getName();

	/**
	 * @return Le nom du parent du formalisme (identifiant historique)
	 */
	String getFKName();

	/**
	 * @return L'image associée à toutes les instances de ce formalisme
	 */
	String getImageName();

	/**
	 * @return Le graphe principal du formalisme
	 */
	IGraphFormalism getMasterGraph();

	/**
	 * @return Le nom du formalisme
	 */
	String toString();
}
