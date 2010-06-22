package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ISpecialState;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IComputedAttributeFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manage the attributes of an IElement.
 * 
 * @see ICoreElement
 */
public abstract class AbstractElement extends AbstractPropertyChange implements IElement, ISpecialState, PropertyChangeListener {

	/**
	 * Map of attributes, the key is the name of the attributes.
	 */
	private Map<String, IAttribute> attributes = new HashMap<String, IAttribute>();

	/**
	 * Map of computed attributes, the key is the name of the attributes.
	 */
	private Map<String, IAttribute> computedAttributes = new HashMap<String, IAttribute>();

	/** Unique identifier */
	private int id;

	/** Parent model element (often GraphModel... But could be another element in case of hierarchy)*/
	private IElement parent;

	/**
	 * Constructor<br>
	 * </i>(Call by subclasses)</i> 
	 * 
	 * @param id Identifier
	 * @param parent Parent model element 
	 * @param attributes Element's attributes
	 */
	AbstractElement(int id, IElement parent, List<IAttributeFormalism> attributes, List<IComputedAttributeFormalism> computedAttributes) {
		this.id = id;
		this.parent = parent;
		if (attributes != null) {
			// Browse all attributes defined by the formalism
			for (IAttributeFormalism attr : attributes) {
				IAttribute attributeModel = new AttributeModel(this, attr);
				attributeModel.addPropertyChangeListener(this);
				this.attributes.put(attr.getName(), attributeModel);
			}
			
			if (computedAttributes != null) {
				// Browse all attributes defined by the formalism
				for (IComputedAttributeFormalism attr : computedAttributes) {
					IAttribute attributeModel = new ComputedAttributeModel(this, attr);
					// Here, we register the attribute as a listener of node changes...
					// This way, we will be able to update computed attributes each time the value of an attribute is updated
					this.addPropertyChangeListener((PropertyChangeListener) attributeModel);
					this.computedAttributes.put(attr.getName(), attributeModel);
				}
			}
		}
	}

	/** {@inheritDoc} */
	public final int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	final void setId(int id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	public final IAttribute getAttribute(String name) {
		return attributes.get(name);
	}

	/** {@inheritDoc} */
	public final Collection<IAttribute> getAttributes() {
		return attributes.values();
	}
	
	protected final Collection<IAttribute> getComputedAttributes() {
		return computedAttributes.values();
	}

	/** {@inheritDoc} */
	public final Collection<IAttribute> getDrawableAttributes() {
		List<IAttribute> drawables = new ArrayList<IAttribute>();
		for (IAttribute attr : attributes.values()) {
			// All attributes coming from this list are standard ones.
			// The cast from IGlobalAttributeFormalism to IAttributeFormalism is then possible
			if (((IAttributeFormalism) attr.getAttributeFormalism()).isDrawable()) {
				drawables.add(attr);
			}
		}
		
		// All computed attributes should be added to the list of drawable attributes
		drawables.addAll(this.computedAttributes.values());
		
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

	/** {@inheritDoc} */
	public final void setSpecialState(boolean state) {
		firePropertyChange(SPECIAL_STATE_CHANGE, null, state);
	}
}
