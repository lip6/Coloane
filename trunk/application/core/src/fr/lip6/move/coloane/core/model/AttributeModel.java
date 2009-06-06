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
 * Description d'un attribut d'un objet du modèle
 */
public class AttributeModel extends AbstractPropertyChange implements IAttribute, ILocatedElement {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private IElement reference;
	private IAttributeFormalism attributFormalism;

	private final String name;
	private String value;

	private EditorGuide horizontalGuide;
	private EditorGuide verticalGuide;

	private IAttributeGraphicInfo graphicInfo = new AttributeGraphicInfo(this);

	/**
	 * Constructeur
	 * @param reference L'élément de référence del'attribut
	 * @param attributeFormalism Le formalisme accroché à l'attribut
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
