package fr.lip6.move.coloane.api.cami.input.results;

public final class CreateMonolineAttribute implements IResult {

	public String attributeName;
	public int associatedNode;
	public String value;

	public CreateMonolineAttribute(String attributeName, int associatedNode, String value) {
		this.attributeName = attributeName;
		this.associatedNode = associatedNode;
		this.value = value;
	}
}
