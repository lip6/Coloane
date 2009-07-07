package fr.lip6.move.coloane.interfaces.model;

import java.util.Collection;

/**
 * Définition d'un objet de modèle (graphe, noeud, attribut etc...).<br>
 * Elle définit les propriétés et comportements de base des éléments d'un modèle.
 */
public interface IElement extends IAbstractPropertyChange {
	/**
	 * Id de la propriété quand l'attribut devient DefaultValue ou était DefaultValue.
	 */
	String ATTRIBUTE_CHANGE = "AttributChange"; //$NON-NLS-1$

	/**
	 * @return unique id.
	 */
	int getId();

	/**
	 * @return parent of this IElement.
	 */
	IElement getParent();

	/**
	 * @param name name of the attribute.
	 * @return the IAttribute named attribute or null.
	 */
	IAttribute getAttribute(String name);

	/**
	 * @return Collection of drawable attribut.
	 */
	Collection<IAttribute> getDrawableAttributes();

	/**
	 * @return Collection of all IAttribute.
	 */
	Collection<IAttribute> getAttributes();

	/**
	 * Put this attribute to this element.
	 * @param name Le nom de l'attribut
	 * @param attribute L'objet décrivant l'attribut
	 */
	void putAttribute(String name, IAttribute attribute);
}
