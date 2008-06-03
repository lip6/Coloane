package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;

public class AttributContainer {
	private int id;
	private String value;

	public AttributContainer(IAttributeImpl attr) {
		id = attr.getId();
		value = attr.getValue();
	}

	public final int getId() {
		return id;
	}

	public final String getValue() {
		return value;
	}
}
