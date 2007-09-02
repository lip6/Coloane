package fr.lip6.move.coloane.api.camiCommands.results;

public class CreateMultilineAttribute implements IResult {

	public String attributeName;
	public int associatedNode;
	public int lineNumber;
	public String value;
	
	public CreateMultilineAttribute(String attributeName, int associatedNode, int lineNumber, String value) {
		this.attributeName = attributeName;
		this.associatedNode = associatedNode;
		this.lineNumber = lineNumber;
		this.value = value;
	}
}
