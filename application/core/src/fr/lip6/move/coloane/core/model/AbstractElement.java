package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manage the attributes of an IElement.
 * @see ICoreElement
 */
public abstract class AbstractElement extends AbstractPropertyChange implements IElement, PropertyChangeListener {
	/**
	 * Map of attributes, the key is the name of the attributes.
	 */
	private Map<String, IAttribute> attributes = new HashMap<String, IAttribute>();

	private IElement parent;

	AbstractElement(IElement parent, List<IAttributeFormalism> attributes) {
		this.parent = parent;
		if (attributes != null) {
			for (IAttributeFormalism attr : attributes) {
				IAttribute attributeModel = new AttributeModel(this, attr);
				attributeModel.addPropertyChangeListener(this);
				this.attributes.put(attr.getName(), attributeModel);
			}
		}
	}

	/** {@inheritDoc} */
	public final IAttribute getAttribute(String name) {
		return attributes.get(name);
	}

	/** {@inheritDoc} */
	public final Collection<IAttribute> getAttributes() {
		return attributes.values();
	}

	/** {@inheritDoc} */
	public final Collection<IAttribute> getDrawableAttributes() {
		ArrayList<IAttribute> drawables = new ArrayList<IAttribute>();
		for (IAttribute attr : attributes.values()) {
			if (attr.getAttributeFormalism().isDrawable()) {
				drawables.add(attr);
			}
		}
		return drawables;
	}

	/** {@inheritDoc} */
	public final void putAttribute(String name, IAttribute attribute) {
		attributes.put(name, attribute);
	}

	/** {@inheritDoc} */
	public final IElement getParent() {
		return parent;
	}

	public final void propertyChange(PropertyChangeEvent evt) {
		// La valeur d'un attribut a été modifié
		if (evt.getPropertyName().equals(IAttribute.VALUE_PROP)) {
			IAttribute attr = (IAttribute) evt.getSource();
			String oldValue = (String) evt.getOldValue();
			String newValue = (String) evt.getNewValue();

			if (oldValue.equals(attr.getAttributeFormalism().getDefaultValue())
					|| newValue.equals(attr.getAttributeFormalism().getDefaultValue())) {
				firePropertyChange(IElement.ATTRIBUTE_CHANGE, null, attr);
			}
		}
	}
}
