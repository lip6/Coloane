package fr.lip6.move.coloane.api.cami.results;

import com.sun.tools.javac.util.List;

public final class ResultSet implements IResult {

	public enum ResultSetType {
		normal,
		warning,
		error;
	}
	
	public String setName;
	public ResultSetType setType;
	public List<IResult> results;
	
	public ResultSet(String setName, ResultSetType setType, List<IResult> results) {
		this.setName = setName;
		this.setType = setType;
		this.results = results;
	}
	
	public void addResult(IResult result) {
		this.results.add(result);
	}
	
}
