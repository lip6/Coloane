package fr.lip6.move.coloane.api.cami.results;

public final class AttributeChange implements IResult {

	public int id;
	public String attributeName;
	public String newValue;
	
	public AttributeChange(int id, String attributeName, String newValue) {
		this.id = id;
		this.attributeName = attributeName;
		this.newValue = newValue;
	}
}
