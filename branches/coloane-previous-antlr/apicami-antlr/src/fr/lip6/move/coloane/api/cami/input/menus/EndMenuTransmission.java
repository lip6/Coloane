package fr.lip6.move.coloane.api.cami.input.menus;

public final class EndMenuTransmission {

	public enum AckType {
		afterSessionConnection(3), userModificationAck(2);

		private int value;

		private AckType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	private AckType ackType;

	public EndMenuTransmission(AckType ackType) {
		this.ackType = ackType;
	}

	public AckType getAckType() {
		return ackType;
	}

	public static AckType AckType(int i) {
		AckType toReturn = AckType.afterSessionConnection;
		for (AckType s : AckType.values()) {
			if (s.value == i) {
				toReturn = s;
			}
		}
		return toReturn;
	}
}
