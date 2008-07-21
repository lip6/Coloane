package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;

public class AttributeModel extends AbstractPropertyChange implements IAttribute, ILocatedElement {
	private IElement reference;
	private IAttributeFormalism attributFormalism;

	private final String name;
	private String value;

	private EditorGuide horizontalGuide;
	private EditorGuide verticalGuide;

	private IAttributeGraphicInfo graphicInfo = new AttributeGraphicInfo(this);

	AttributeModel(IElement reference, IAttributeFormalism attributeFormalism) {
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
		String oldValue = this.value;
		this.value = value;
		if (!oldValue.equals(value)) {
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
}
