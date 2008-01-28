package fr.lip6.move.coloane.api.session.states.authentication;

import org.antlr.runtime.RecognitionException;

import fr.lip6.move.coloane.api.cami.input.connection.CloseConnectionPanic;

public final class AuthenticationFailure extends RecognitionException implements IAuthenticationMessage {

	private static final long serialVersionUID = -4840351905150773735L;
	private CloseConnectionPanic closeConnectionPanic;
	
	public AuthenticationFailure(CloseConnectionPanic closeConnectionPanic) {
		super();
		this.closeConnectionPanic = closeConnectionPanic;
	}
	
	public CloseConnectionPanic getKo() {
		return closeConnectionPanic;
	}
	
	
	
}

