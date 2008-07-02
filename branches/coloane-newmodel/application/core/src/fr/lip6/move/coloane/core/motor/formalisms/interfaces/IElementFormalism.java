package fr.lip6.move.coloane.core.motor.formalisms.interfaces;

import java.util.List;

import fr.lip6.move.coloane.core.motor.formalisms.elements.AttributeFormalism;

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
	public abstract String getName();

	/**
	 * @return Retourne la liste des {@link AttributeFormalism} d'un élément.
	 */
	public abstract List<AttributeFormalism> getAttributes();

	/**
	 * @return La description graphique de l'élément
	 */
	public abstract IGraphicalDescription getGraphicalDescription();
}