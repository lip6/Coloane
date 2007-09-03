package fr.lip6.move.coloane.api.cami.input.results;

import java.util.Collection;

import fr.lip6.move.coloane.api.cami.input.connection.CloseConnectionPanic.Severity;

public final class ResultSet implements IResult {

	public enum ResultSetType {
		normal(1),
		warning(2),
		error(3);
		
		private int value;
		
		private ResultSetType(int value) {
			this.value = value;
		}
		
		public static ResultSetType makeResultSetType(int i) {
			ResultSetType toReturn = normal;
			for( ResultSetType s : ResultSetType.values() ) {
				if( s.value == i ) {
					toReturn = s;
				}
			}
			return toReturn;
		}
	}
	
	public String setName;
	public ResultSetType setType;
	public Collection<IResult> results;
	
	public ResultSet(String setName, ResultSetType setType, Collection<IResult> results) {
		this.setName = setName;
		this.setType = setType;
		this.results = results;
	}
	
}
