package fr.lip6.move.coloane.interfaces.formalism;

import java.util.List;

public interface IFormalism {

	/**
	 * Indique si la liaison entre deux élément est possible
	 * @param source Element source de l'arc
	 * @param target Element cible de l'arc
	 * @return <code>true</code> si la liaison est possible
	 */
	boolean isLinkAllowed(IElementFormalism source, IElementFormalism target);

	/**
	 * Retourne la liste des éléments attachés au formalisme
	 * @return Une liste d'éléments {@FormalismElement}
	 */
	List<IElementFormalism> getListOfElementBase();

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
