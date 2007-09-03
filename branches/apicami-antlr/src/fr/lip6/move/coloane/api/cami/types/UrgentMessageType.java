package fr.lip6.move.coloane.api.cami.types;

public enum UrgentMessageType {
	abort(1),
	quit(2);
	
	private int value;
	
	private UrgentMessageType(int value) {
		this.value = value;
	}
	
	public int getInt() {
		return this.value;
	}
}
