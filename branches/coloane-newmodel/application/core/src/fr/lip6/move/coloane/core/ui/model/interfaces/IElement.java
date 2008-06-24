package fr.lip6.move.coloane.core.ui.model.interfaces;

import java.beans.PropertyChangeListener;
import java.util.Collection;

public interface IElement {
	/**
	 * @return unique id.
	 */
	int getId();

	/**
	 * @param name name of the attribute.
	 * @return the IAttribute named attribute or null.
	 */
	IAttribute getAttribute(String name);

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

	/**
	 * Attach a listener to this element.
	 * @param listener
	 */
	void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Remove a listener.
	 * @param listener
	 */
	void removePropertyChangeListener(PropertyChangeListener listener);
}
