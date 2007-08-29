package fr.lip6.move.coloane.api.session.states.authentication;

import org.antlr.runtime.RecognitionException;

public final class AuthenticationFailure extends RecognitionException implements IAuthenticationMessage {

	private static final long serialVersionUID = -4840351905150773735L;
	private String errorMessage;
	
	public AuthenticationFailure(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public final String getErrorMessage() {
		return errorMessage;
	}

}

