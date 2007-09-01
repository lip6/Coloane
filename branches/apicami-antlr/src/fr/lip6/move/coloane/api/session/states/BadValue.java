package fr.lip6.move.coloane.api.session.states;

import org.antlr.runtime.RecognitionException;
import fr.lip6.move.coloane.api.session.controller.IMessage;

public final class BadValue extends RecognitionException implements IMessage {

	private static final long serialVersionUID = 8292506558280277707L;
	private int badValue;

	public BadValue(int badValue) {
		this.badValue = badValue;
	}

	public int getBadValue() {
		return this.badValue;
	}

}
