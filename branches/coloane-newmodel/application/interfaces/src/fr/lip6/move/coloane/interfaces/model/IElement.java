package fr.lip6.move.coloane.interfaces.model;

import java.util.Collection;

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
	 * @param name
	 * @param attribute
	 */
	void putAttribute(String name, IAttribute attribute);
}
