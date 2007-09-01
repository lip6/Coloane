package fr.lip6.move.coloane.api.camiCommands;

import fr.lip6.move.coloane.api.camiCommands.types.SpecialMessageType;

public final class SpecialMessages {

	public SpecialMessageType type;
	public String message;

	public SpecialMessages(SpecialMessageType type, String message) {
		this.type = type;
		this.message = message;
	}
}
