package fr.lip6.move.coloane.interfaces.formalism;

import java.util.List;

public interface IFormalism {

	/**
	 * Indique si la liaison entre deux élément est possible
	 * @param source Element source de l'arc
	 * @param target Element cible de l'arc
	 * @return <code>true</code> si la liaison est possible
	 */
	public abstract boolean isLinkAllowed(IElementFormalism source,
			IElementFormalism target);

	/**
	 * Retourne la liste des éléments attachés au formalisme
	 * @return Une liste d'éléments {@FormalismElement}
	 */
	public abstract List<IElementFormalism> getListOfElementBase();

	/**
	 * @return Le nom du formalisme
	 */
	public abstract String getName();

	/**
	 * @return L'image associée à toutes les instances de ce formalisme
	 */
	public abstract String getImageName();

	/**
	 * @return La chaine de caractères à utiliser pour l'extension du fichier
	 */
	public abstract String getExtension();

	/**
	 * @return L'adresse du xschema a utliser pour la validation
	 */
	public abstract String getSchema();

	/**
	 * @return Le graphe principal du formalisme
	 */
	public abstract IElementFormalism getMasterGraph();

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();

}