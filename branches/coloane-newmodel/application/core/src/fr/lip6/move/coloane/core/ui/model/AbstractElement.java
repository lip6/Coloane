package fr.lip6.move.coloane.core.ui.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.lip6.move.coloane.core.motor.formalisms.elements.Attribute;
import fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute;
import fr.lip6.move.coloane.core.ui.model.interfaces.IElement;

/**
 * Manage the attributes of an IElement.
 * @see IElement
 */
public abstract class AbstractElement implements IElement {
	/**
	 * Manage properties change listeners.
	 */
	private PropertyChangeSupport pcsDelegate = new PropertyChangeSupport(this);
	
	/**
	 * Map of attributes, the keys are the names of the attributes.
	 */
	private Map<String, IAttribute> attributes = new HashMap<String, IAttribute>();
	
	AbstractElement(List<Attribute> attributes) {
		if (attributes != null) {
			for (Attribute attr : attributes) {
				this.attributes.put(
						attr.getName(),
						new AttributeModel(attr));
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IElement#getAttribute(java.lang.String)
	 */
	public IAttribute getAttribute(String name) {
		return attributes.get(name);
	}
	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IElement#getAttributes()
	 */
	public Collection<IAttribute> getAttributes() {
		return attributes.values();
	}
	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IElement#putAttribute(java.lang.String, fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute)
	 */
	public void putAttribute(String name, IAttribute attribute) {
		attributes.put(name, attribute);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IElement#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public final synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		pcsDelegate.addPropertyChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IElement#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public final synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
		pcsDelegate.removePropertyChangeListener(listener);
	}

	protected final void firePropertyChange(String property, Object oldValue, Object newValue) {
		if (pcsDelegate.hasListeners(property)) {
			pcsDelegate.firePropertyChange(property, oldValue, newValue);
		}
	}
}
