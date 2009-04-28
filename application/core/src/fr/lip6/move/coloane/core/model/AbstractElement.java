package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ISpecialState;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
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
 * @see ICoreElement
 */
public abstract class AbstractElement extends AbstractPropertyChange implements IElement, ISpecialState, PropertyChangeListener {

	/**
	 * Map of attributes, the key is the name of the attributes.
	 */
	private Map<String, IAttribute> attributes = new HashMap<String, IAttribute>();

	/** Identifiant unique */
	private int id;

	private IElement parent;

	/**
	 * Constructeur
	 * @param id id de cet élément
	 * @param parent L'élément parent
	 * @param attributes Les attribut de l'élément
	 */
	AbstractElement(int id, IElement parent, List<IAttributeFormalism> attributes) {
		this.id = id;
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

	/** {@inheritDoc} */
	public final Collection<IAttribute> getDrawableAttributes() {
		List<IAttribute> drawables = new ArrayList<IAttribute>();
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

	/** {@inheritDoc} */
	public final void setSpecialState(boolean state) {
		firePropertyChange(SPECIAL_STATE_CHANGE, null, state);
	}
}
