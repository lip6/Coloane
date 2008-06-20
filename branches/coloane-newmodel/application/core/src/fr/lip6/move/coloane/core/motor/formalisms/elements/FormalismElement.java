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

public class FormalismElement {
	/** Nom associe a l'element de base. */
	private String name;

	/** Tableau des differents attributs de l'element de base. */
	private List<Attribute> attributes = new ArrayList<Attribute>(0);

	/**
	 * Constructeur
	 * @param name Nom de l'élément de base.
	 */
	public FormalismElement(String name) {
		this.name = name;
	}

	/**
	 * Ajoute un attribut à l'element de base
	 * @param attribute {@link Attribute} à ajouter à la liste des attributs
	 */
	public final void addAttribute(Attribute attribute) { 
		this.attributes.add(attribute); 
	}

	/**
	 * @return Le nom de l'élément de base
	 */
	public final String getName() {	return name; }

	/**
	 * @return Retourne la liste des {@link Attribute} d'un élément.
	 */
	public final List<Attribute> getAttributes() { return this.attributes; }
}
