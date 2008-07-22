package fr.lip6.move.coloane.interfaces.formalism;

import fr.lip6.move.coloane.interfaces.model.INode;

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
	 * @return L'adresse du xschema a utliser pour la validation
	 */
	String getSchema();

	/**
	 * @return Le graphe principal du formalisme
	 */
	IGraphFormalism getMasterGraph();

	/** {@inheritDoc} */
	String toString();
}
