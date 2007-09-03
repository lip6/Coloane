package fr.lip6.move.coloane.api.cami.results;

public final class CreateBox implements IResult {

	public String nodeBoxType;
	public int id;
	public int pageId;
	
	public CreateBox(String nodeBoxType, int id, int pageId) {
		this.nodeBoxType = nodeBoxType;
		this.id = id;
		this.pageId = pageId;
	}
}
