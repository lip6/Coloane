package fr.lip6.move.coloane.core.motor.formalisms.elements;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphicalDescription;

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

public class ElementFormalism implements IElementFormalism {
	/** Nom associe a l'element de base. */
	private String name;

	/** Formalisme qui decrit un tel élément */
	private IFormalism formalism;

	/** Tableau des differents attributs de l'element de base. */
	private List<IAttributeFormalism> attributes = new ArrayList<IAttributeFormalism>(0);

	/** Description graphique de l'élément de formalisme */
	private IGraphicalDescription graphicalDescription;

	/**
	 * Constructeur
	 * @param name Nom de l'élément de base.
	 * @param formalism Formalisme de l'élément de base.
	 */
	public ElementFormalism(String name, IFormalism formalism) {
		this.name = name;
		this.formalism = formalism;
	}

	/**
	 * Ajoute un attribut à l'element de base
	 * @param attribute {@link AttributeFormalism} à ajouter à la liste des attributs
	 */
	public final void addAttribute(AttributeFormalism attribute) {
		this.attributes.add(attribute);
	}

	/** {@inheritDoc} */
	public final String getName() {	return name; }

	/** {@inheritDoc} */
	public final List<IAttributeFormalism> getAttributes() { return this.attributes; }

	/** {@inheritDoc} */
	public final IGraphicalDescription getGraphicalDescription() {
		return graphicalDescription;
	}

	/**
	 * Ajoute la description graphique à la définition de l'élément de formalisme
	 * @param graphicalDescription La description graphique
	 */
	public final void setGraphicalDescription(IGraphicalDescription graphicalDescription) {
		this.graphicalDescription = graphicalDescription;
	}

	/** {@inheritDoc} */
	public final IFormalism getFormalism() {
		return this.formalism;
	}
}
