package fr.lip6.move.coloane.core.ui.model;

import fr.lip6.move.coloane.core.motor.formalisms.elements.Attribute;
import fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute;
import fr.lip6.move.coloane.core.ui.model.interfaces.IAttributeGraphicInfo;

public class AttributeModel extends AbstractPropertyChange implements IAttribute {
	private final String name;
	private String value;

	private IAttributeGraphicInfo graphicInfo = new AttributeGraphicInfo(this);
	
	AttributeModel(Attribute attributeFormalism) {
		this.name = attributeFormalism.getName();
		this.value = attributeFormalism.getDefaultValue();
	}
	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#getValue()
	 */
	public String getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#setValue(java.lang.String)
	 */
	public void setValue(String value) {
		String oldValue = this.value;
		this.value = value;
		firePropertyChange(IAttribute.VALUE_PROP, oldValue, value);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute#getGraphicInfo()
	 */
	public IAttributeGraphicInfo getGraphicInfo() {
		return graphicInfo;
	}
}
