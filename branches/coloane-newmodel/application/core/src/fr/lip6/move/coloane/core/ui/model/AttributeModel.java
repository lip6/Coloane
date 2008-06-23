package fr.lip6.move.coloane.core.ui.model;

import fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute;

public class AttributeModel implements IAttribute {
	private final String name;
	private String value;
	
	AttributeModel(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
}
