package fr.lip6.move.coloane.api.camiCommands;

public final class SpecialMessages {

	public enum SpecialMessageType {
		motd,
		quickAndUrgentWarning,
		copyrightMessage,
		executionStatistics;
	}
	
	public SpecialMessageType messageType;
	public String message;

	public SpecialMessages( SpecialMessageType messageType, String message) {
		this.messageType = messageType;
		this.message = message;
	}
}
