package fr.lip6.move.coloane.api.cami.input.connection;

public final class CloseConnectionPanic {

	public enum Severity {
		minimal(1),
		average(2),
		maximal(3);
		
		private int value;
		
		private Severity(int value) {
			this.value = value;
		}
		
		public int getInt() {
			return this.value;
		}
		
		public static Severity makeSeverity(int i) {
			Severity toReturn = maximal;
			for( Severity s : Severity.values() ) {
				if( s.value == i ) {
					toReturn = s;
				}
			}
			return toReturn;
		}
	}
	
	public String message;
	public Severity severity;
	
	public CloseConnectionPanic(String message, Severity severity) {
		this.message = message;
		this.severity = severity;
	}

}
