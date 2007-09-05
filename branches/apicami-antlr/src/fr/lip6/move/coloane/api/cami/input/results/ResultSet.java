package fr.lip6.move.coloane.api.cami.input.results;

import java.util.Collection;

public final class ResultSet implements IResult {

	public enum ResultSetType {
		normal(1), warning(2), error(3);

		private int value;

		private ResultSetType(int value) {
			this.value = value;
		}

		public int getInt() {
			return this.value;
		}
	}

	private String setName;
	private ResultSetType setType;
	private Collection<IResult> results;

	public ResultSet(String setName, ResultSetType setType, Collection<IResult> results) {
		this.setName = setName;
		this.setType = setType;
		this.results = results;
	}

	public static ResultSetType ResultSetType(int i) {
		ResultSetType toReturn = ResultSetType.normal;
		for (ResultSetType s : ResultSetType.values()) {
			if (s.value == i) {
				toReturn = s;
			}
		}
		return toReturn;
	}

	public String getSetName() {
		return setName;
	}

	public ResultSetType getSetType() {
		return setType;
	}

	public Collection<IResult> getResults() {
		return results;
	}

}
