package fr.lip6.move.coloane.interfaces.formalism;

import java.util.List;

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
	 * Retourne la liste des éléments attachés au formalisme
	 * @return Une liste d'éléments {@FormalismElement}
	 */
	List<IElementFormalism> getListOfFormalismElement();

	/**
	 * Retourne l'élément de formalisme correspondant au nom passé en paramètre
	 * @param name le nom de l'élément de formalisme souhaité
	 * @return L'IElementFormalism associé à ce nom ou <code>null</code>.
	 */
	IElementFormalism getElementFormalism(String name);

	/**
	 * @return Le nom du formalisme
	 */
	String getName();
	
	/**
	 * @return Le nom du parent du formalisme (identifiant historique)
	 */
	String getParent();

	/**
	 * @return L'image associée à toutes les instances de ce formalisme
	 */
	String getImageName();

	/**
	 * @return La chaine de caractères à utiliser pour l'extension du fichier
	 */
	String getExtension();

	/**
	 * @return L'adresse du xschema a utliser pour la validation
	 */
	String getSchema();

	/**
	 * @return Le graphe principal du formalisme
	 */
	IElementFormalism getMasterGraph();

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	String toString();
}
