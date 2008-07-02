package fr.lip6.move.coloane.core.motor.formalisms.elements;

import java.util.ArrayList;
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

public class ElementFormalism {
	/** Nom associe a l'element de base. */
	private String name;

	/** Tableau des differents attributs de l'element de base. */
	private List<AttributeFormalism> attributes = new ArrayList<AttributeFormalism>(0);
	
	/** Description graphique de l'élément de formalisme */
	private GraphicalDescription graphicalDescription;

	/**
	 * Constructeur
	 * @param name Nom de l'élément de base.
	 */
	public ElementFormalism(String name) {
		this.name = name;
	}

	/**
	 * Ajoute un attribut à l'element de base
	 * @param attribute {@link AttributeFormalism} à ajouter à la liste des attributs
	 */
	public final void addAttribute(AttributeFormalism attribute) { 
		this.attributes.add(attribute); 
	}

	/**
	 * @return Le nom de l'élément de base
	 */
	public final String getName() {	return name; }

	/**
	 * @return Retourne la liste des {@link AttributeFormalism} d'un élément.
	 */
	public final List<AttributeFormalism> getAttributes() { return this.attributes; }

	/**
	 * @return La description graphique de l'élément
	 */
	public GraphicalDescription getGraphicalDescription() {
		return graphicalDescription;
	}

	/**
	 * Ajoute la description graphique à la définition de l'élément de formalisme
	 * @param graphicalDescription La description graphique
	 */
	public void setGraphicalDescription(GraphicalDescription graphicalDescription) {
		this.graphicalDescription = graphicalDescription;
	}
}
