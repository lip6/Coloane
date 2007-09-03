package fr.lip6.move.coloane.api.session.states;

import org.antlr.runtime.RecognitionException;

import fr.lip6.move.coloane.api.cami.input.messages.SpecialMessages;
import fr.lip6.move.coloane.api.session.controller.IMessage;

public final class MessageFormatFailure extends RecognitionException implements IMessage {

	private static final long serialVersionUID = -5623004355585115389L;
	private SpecialMessages specialMessages;

	public MessageFormatFailure(SpecialMessages specialMessages) {
		this.specialMessages = specialMessages;
	}

	public SpecialMessages getErrorMessage() {
		return specialMessages;
	}

}
