package fr.lip6.move.coloane.interfaces.model.impl;

import fr.lip6.move.coloane.core.motor.formalisms.elements.Attribute;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.core.ICoreAttribute;

public class AttributeModel extends AbstractPropertyChange implements ICoreAttribute {
	private IElement reference;
	private Attribute attributFormalism;

	private final String name;
	private String value;

	private IAttributeGraphicInfo graphicInfo = new AttributeGraphicInfo(this);

	AttributeModel(IElement reference, Attribute attributeFormalism) {
		this.reference = reference;
		this.attributFormalism = attributeFormalism;
		this.name = attributeFormalism.getName();
		this.value = attributeFormalism.getDefaultValue();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#getName()
	 */
	public final String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#getValue()
	 */
	public final String getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#setValue(java.lang.String)
	 */
	public final void setValue(String value) {
		String oldValue = this.value;
		this.value = value;
		firePropertyChange(IAttribute.VALUE_PROP, oldValue, value);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#getGraphicInfo()
	 */
	public final IAttributeGraphicInfo getGraphicInfo() {
		return graphicInfo;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#getReference()
	 */
	public final IElement getReference() {
		return reference;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#getType()
	 */
	public final int getType() {
		return attributFormalism.getType();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#isDrawable()
	 */
	public final boolean isDrawable() {
		return attributFormalism.isDrawable();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#isMultiLine()
	 */
	public final boolean isMultiLine() {
		return attributFormalism.isMultiLine();
	}
}
