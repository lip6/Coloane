package fr.lip6.move.coloane.api.cami.results;

public final class MultipleObjectDeletion implements IResult {

	public int id;
	public int pageId;
	
	public MultipleObjectDeletion(int id, int pageId) {
		this.id = id;
		this.pageId = pageId;
	}
}
