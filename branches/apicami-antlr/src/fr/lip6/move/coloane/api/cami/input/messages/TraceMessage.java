package fr.lip6.move.coloane.api.cami.input.messages;

public class TraceMessage implements IMessage {

	private String message;

	public TraceMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
