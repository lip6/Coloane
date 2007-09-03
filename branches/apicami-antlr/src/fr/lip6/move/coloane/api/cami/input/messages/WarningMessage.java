package fr.lip6.move.coloane.api.cami.input.messages;

public class WarningMessage implements IMessage {

	public String message;

	public WarningMessage(String message) {
		this.message = message;
	}
}
