package fr.lip6.move.coloane.results;

import java.util.Observable;
import java.util.Vector;


public class ResultsList extends Observable {
	private String actionName;
	private Vector<Result> resultsList;
	
	public ResultsList(String actionName) {
		this.actionName = actionName;
		resultsList = new Vector<Result>();;
	}
	
	public void add(Result result) {
		resultsList.add(result);
	}
	
	public String getActionName() {
		return actionName;
	}
	
	public Result getResult(int index) {
		return resultsList.get(index);
	}
	
	public int getResultsNumber() {
		return resultsList.size();
	}
}
