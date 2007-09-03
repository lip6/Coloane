package fr.lip6.move.coloane.api.cami.input.connection;

public final class CloseConnectionPanic {

	public String message;
	public int severity;

	public CloseConnectionPanic(String message, int severity) {
		this.message = message;
		this.severity = severity;
	}
}
