package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;

import java.util.logging.Logger;

/**
 * Describe an object model attribute
 * 
 * @author Jean-Baptiste Voron
 */
public class AttributeModel extends AbstractPropertyChange implements IAttribute, ILocatedElement {
	/** The main logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The reference model element */
	private IElement reference;

	private IAttributeFormalism attributFormalism;

	/** Attribute name */
	private final String name;
	/** Attribute value */
	private String value;

	/** Graphical guides to help the positioning of the attribute */
	private EditorGuide horizontalGuide;
	private EditorGuide verticalGuide;

	/** All the graphical information about this attribute */
	private IAttributeGraphicInfo graphicInfo = new AttributeGraphicInfo(this);

	/**
	 * Constructor
	 *
	 * @param reference The element to which this attribute is associated
	 * @param attributeFormalism The properties of this attribute (given by the formalism)
	 */
	AttributeModel(IElement reference, IAttributeFormalism attributeFormalism) {
		LOGGER.finest("Création d'un AttributeModel(" + attributeFormalism.getName() + ", " + reference.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.reference = reference;
		this.attributFormalism = attributeFormalism;
		this.name = attributeFormalism.getName();
		this.value = attributeFormalism.getDefaultValue();
	}

	/** {@inheritDoc} */
	public final String getName() {
		return name;
	}

	/** {@inheritDoc} */
	public final String getValue() {
		return value;
	}

	/** {@inheritDoc} */
	public final void setValue(String value) {
		if (value == null) { return; }
		String oldValue = this.value;
		this.value = value;
		if (!oldValue.equals(value)) {
			LOGGER.finest("setValue(" + value + ")"); //$NON-NLS-1$ //$NON-NLS-2$
			firePropertyChange(IAttribute.VALUE_PROP, oldValue, value);
		}
	}

	/** {@inheritDoc} */
	public final IAttributeGraphicInfo getGraphicInfo() {
		return graphicInfo;
	}

	/** {@inheritDoc} */
	public final IElement getReference() {
		return reference;
	}

	/** {@inheritDoc} */
	public final IAttributeFormalism getAttributeFormalism() {
		return attributFormalism;
	}


	/** {@inheritDoc} */
	public final EditorGuide getHorizontalGuide() {
		return this.horizontalGuide;
	}

	/** {@inheritDoc} */
	public final EditorGuide getVerticalGuide() {
		return this.verticalGuide;
	}

	/** {@inheritDoc} */
	public final void setHorizontalGuide(EditorGuide guide) {
		this.horizontalGuide = guide;
	}

	/** {@inheritDoc} */
	public final void setVerticalGuide(EditorGuide guide) {
		this.verticalGuide = guide;
	}

	/** {@inheritDoc} */
	public final ILocationInfo getLocationInfo() {
		return this.graphicInfo;
	}

	/** {@inheritDoc} */
	@Override
	public final String toString() {
		return "Attribut(" + name + ": " + value + " [" + reference + "])"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
}
