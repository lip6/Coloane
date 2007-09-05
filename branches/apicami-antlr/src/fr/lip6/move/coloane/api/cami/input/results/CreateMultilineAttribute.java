package fr.lip6.move.coloane.api.cami.input.results;

public final class CreateMultilineAttribute implements IResult {

	private String attributeName;
	private int associatedNode;
	private int lineNumber;
	private String value;

	public CreateMultilineAttribute(String attributeName, int associatedNode, int lineNumber, String value) {
		this.attributeName = attributeName;
		this.associatedNode = associatedNode;
		this.lineNumber = lineNumber;
		this.value = value;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public int getAssociatedNode() {
		return associatedNode;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public String getValue() {
		return value;
	}

}
