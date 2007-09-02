package fr.lip6.move.coloane.api.camiCommands.results;

public class MultipleObjectDeletion implements IResult {

	public int id;
	public int pageId;
	
	public MultipleObjectDeletion(int id, int pageId) {
		this.id = id;
		this.pageId = pageId;
	}
}
