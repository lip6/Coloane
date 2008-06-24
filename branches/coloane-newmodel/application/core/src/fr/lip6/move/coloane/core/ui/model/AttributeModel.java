package fr.lip6.move.coloane.core.ui.model;

import fr.lip6.move.coloane.core.motor.formalisms.elements.Attribute;
import fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute;
import fr.lip6.move.coloane.core.ui.model.interfaces.IAttributeGraphicInfo;

public class AttributeModel extends AbstractElement implements IAttribute {
	private final String name;
	private String value;

	private IAttributeGraphicInfo graphicInfo = new AttributeGraphicInfo(this);
	
	AttributeModel(Attribute attributeFormalism) {
		super(null);
		this.name = attributeFormalism.getName();
		this.value = attributeFormalism.getDefaultValue();
	}
	
	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
