package fr.lip6.move.coloane.api.cami.types;

public enum InteractiveAnswerType {
	normalMessageWithDisplay(0),
	normalMessage(1),
	warningMessage(2),
	errorMessage(3); 
	
	private int value;
	
	private InteractiveAnswerType(int value) {
		this.value = value;
	}
	
	public int getInt() {
		return this.value;
	}
	
}
