package fr.lip6.move.coloane.api.cami.input.results;

public final class CreateArc implements IResult {

	public String arcType;
	public int id;
	public int startNode;
	public int endNode;

	public CreateArc(String arcType, int id, int startNode, int endNode) {
		this.arcType = arcType;
		this.id = id;
		this.startNode = startNode;
		this.endNode = endNode;
	}
}
