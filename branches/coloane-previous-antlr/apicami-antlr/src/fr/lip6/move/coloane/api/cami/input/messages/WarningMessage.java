package fr.lip6.move.coloane.api.cami.input.messages;

public final class WarningMessage implements IMessage {

	private String message;

	public WarningMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
