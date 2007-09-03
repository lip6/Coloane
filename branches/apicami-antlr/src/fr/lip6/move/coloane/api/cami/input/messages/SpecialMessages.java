package fr.lip6.move.coloane.api.cami;

public final class SpecialMessages {

	public enum SpecialMessageType {
		motd(1),
		quickAndUrgentWarning(2),
		copyrightMessage(3),
		executionStatistics(4);
		
		private SpecialMessageType(int i) {
		}
	}

	public SpecialMessageType messageType;
	public String message;

	public SpecialMessages( SpecialMessageType messageType, String message) {
		this.messageType = messageType;
		this.message = message;
	}

	public static SpecialMessageType SpecialMessageType(int intValue) {
		return SpecialMessageType(intValue);
	}
}
