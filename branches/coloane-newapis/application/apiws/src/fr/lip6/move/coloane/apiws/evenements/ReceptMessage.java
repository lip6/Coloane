package fr.lip6.move.coloane.apiws.evenements;

import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;

public class ReceptMessage implements IReceptMessage {

	private int type;
	
	private String message;
	
	public ReceptMessage(int type, String message){
		this.type = type;
		this.message = message;
	}

	public int getTypeMessage() {
		return type;
	}
	
	public String getMessage() {
		return message;
	}

}