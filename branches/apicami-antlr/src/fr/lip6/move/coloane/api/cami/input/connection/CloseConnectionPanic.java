package fr.lip6.move.coloane.api.cami.input.connection;

import fr.lip6.move.coloane.api.cami.ICommand;

public final class CloseConnectionPanic implements ICommand {

	public enum Severity {
		minimal(1), average(2), maximal(3);

		private int value;

		private Severity(int value) {
			this.value = value;
		}

		public int getInt() {
			return this.value;
		}
	}

	private String message;
	private Severity severity;

	public CloseConnectionPanic(String message, Severity severity) {
		this.message = message;
		this.severity = severity;
	}

	public static Severity Severity(int i) {
		Severity toReturn = Severity.minimal;
		for (Severity s : Severity.values()) {
			if (s.value == i) {
				toReturn = s;
			}
		}
		return toReturn;
	}

	public String getMessage() {
		return message;
	}

	public Severity getSeverity() {
		return severity;
	}

}
