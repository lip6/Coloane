package fr.lip6.move.coloane.api.cami.input.results;

public final class AttributeChange implements IResult {

	private int id;
	private String attributeName;
	private String newValue;

	public AttributeChange(int id, String attributeName, String newValue) {
		this.id = id;
		this.attributeName = attributeName;
		this.newValue = newValue;
	}

	public int getId() {
		return id;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public String getNewValue() {
		return newValue;
	}

}
