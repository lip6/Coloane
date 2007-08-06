package fr.lip6.move.coloane.results;

public class Result {

	private String name;
	private String description;

	public Result(String resultName, String resultDesc) {
		this.name = resultName;
		this.description = resultDesc;
	}

	public final String getName() {
		return name;
	}

	public final String getDescription() {
		return description;
	}
}
