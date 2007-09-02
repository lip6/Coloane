package fr.lip6.move.coloane.api.camiCommands.results;

public final class AttributeOutline implements IResult {

	public int id;
	public String attributeName;
	
	public AttributeOutline(int id, String attributeName) {
		this.id = id;
		this.attributeName = attributeName;
	}
	
}
