package fr.lip6.move.coloane.api.cami.input.messages;

public final class SpecialMessages implements IMessage {

	public enum SpecialMessageType {
		motd(1),
		quickAndUrgentWarning(2),
		copyrightMessage(3),
		executionStatistics(4);
		
		private int value;
		
		private SpecialMessageType(int value) {
			this.value = value;
		}
		
		public int getInt() {
			return this.value;
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
