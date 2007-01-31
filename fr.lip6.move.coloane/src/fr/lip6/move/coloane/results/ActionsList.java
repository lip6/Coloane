package fr.lip6.move.coloane.results;

import java.util.Observable;
import java.util.Vector;

public class ActionsList extends Observable {
	private Vector<ResultsList> resultsLists;
	
	public ActionsList() {
		resultsLists = new Vector<ResultsList>();
	}
	
	public void addResultsList(ResultsList resultList) {
		resultsLists.add(resultList);
	}
	
	public ResultsList getResultsList(int index) {
		return resultsLists.get(index);
	}
	
	public int getResultsListSize() {
		return resultsLists.size();
	}
}
