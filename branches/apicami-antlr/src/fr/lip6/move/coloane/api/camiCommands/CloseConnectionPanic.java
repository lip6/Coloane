package fr.lip6.move.coloane.api.camiCommands;

public final class CloseConnectionPanic {

	public String message;
	public int severity;

	public CloseConnectionPanic(String message, int severity) {
		this.message = message;
		this.severity = severity;
	}
}
