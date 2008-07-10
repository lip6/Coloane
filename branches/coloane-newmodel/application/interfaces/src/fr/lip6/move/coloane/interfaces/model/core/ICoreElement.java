package fr.lip6.move.coloane.interfaces.model.core;

import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;

import java.util.Collection;

public interface ICoreElement extends ICoreAbstractPropertyChange, IElement {
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
