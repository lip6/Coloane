package fr.lip6.move.coloane.core.ui.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.lip6.move.coloane.core.motor.formalisms.elements.Attribute;

/**
 * Manage the attributes of an IElement.
 * @see IElement
 */
public abstract class AbstractElement implements IElement {
	/**
	 * Map of attributes, the keys are the names of the attributes.
	 */
	private Map<String, IAttribute> attributes = new HashMap<String, IAttribute>();
	
	AbstractElement(List<Attribute> attributes) {
		for (Attribute attr : attributes) {
			this.attributes.put(
					attr.getName(),
					new AttributeModel(attr.getName()));
		}
	}
	
	/**
	 * @param name name of the attribute.
	 * @return the IAttribute named attribute or null.
	 */
	public IAttribute getAttribute(String name) {
		return attributes.get(name);
	}
	
	/**
	 * @return Collection of all IAttribute.
	 */
	public Collection<IAttribute> getAttributes() {
		return attributes.values();
	}
	
	public void putAttribute(String name, IAttribute attribute) {
		attributes.put(name, attribute);
	}
}
