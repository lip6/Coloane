package fr.lip6.move.coloane.api.cami.input.results;

public final class CreateNode implements IResult {

	public String nodeBoxType;
	public int id;
	
	public CreateNode(String nodeBoxType, int id) {
		this.nodeBoxType = nodeBoxType;
		this.id = id;
	}
}
