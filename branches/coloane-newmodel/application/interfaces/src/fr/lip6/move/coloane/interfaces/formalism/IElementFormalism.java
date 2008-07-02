package fr.lip6.move.coloane.interfaces.formalism;

import java.util.List;

/**
 * Cette classe représente un élément du base du formalisme.<br>
 * Un element de base est le composant d'un formalisme.<br>
 * Un element de base contient toutes les informations decrivant ce composant de formalisme :
 * <ul>
 * 	<li>Nom</li>
 * 	<li>Liste des attributs attachés à cet élément de formalisme</li>
 * </ul>
 */
public interface IElementFormalism {

	/**
	 * @return Le nom de l'élément de base
	 */
	String getName();

	/**
	 * @return Retourne la liste des {@link AttributeFormalism} d'un élément.
	 */
	List<IAttributeFormalism> getAttributes();

	/**
	 * @return La description graphique de l'élément
	 */
	IGraphicalDescription getGraphicalDescription();
	
	/**
	 * @return le formalisme qui definit cet élément de formalisme
	 */
	IFormalism getFormalism();
}
