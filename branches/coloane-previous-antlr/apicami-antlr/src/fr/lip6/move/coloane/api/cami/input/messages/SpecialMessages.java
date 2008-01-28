package fr.lip6.move.coloane.api.cami.input.messages;

public final class SpecialMessages implements IMessage {

	public enum SpecialMessageType {
		motd(1), quickAndUrgentWarning(2), copyrightMessage(3), executionStatistics(4);

		private int value;

		private SpecialMessageType(int value) {
			this.value = value;
		}

		public int getInt() {
			return this.value;
		}
	}

	private SpecialMessageType messageType;
	private String message;

	public SpecialMessages(SpecialMessageType messageType, String message) {
		this.messageType = messageType;
		this.message = message;
	}

	public static SpecialMessageType SpecialMessageType(int i) {
		SpecialMessageType toReturn = SpecialMessageType.motd;
		for (SpecialMessageType s : SpecialMessageType.values()) {
			if (s.value == i) {
				toReturn = s;
			}
		}
		return toReturn;
	}

	public SpecialMessageType getMessageType() {
		return messageType;
	}

	public String getMessage() {
		return message;
	}

}
