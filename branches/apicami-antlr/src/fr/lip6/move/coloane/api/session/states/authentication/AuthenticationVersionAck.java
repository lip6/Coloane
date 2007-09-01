package fr.lip6.move.coloane.api.session.states.authentication;

import fr.lip6.move.coloane.api.camiCommands.AckOpenConnection;

public final class AuthenticationVersionAck implements IAuthenticationMessage {

	private AckOpenConnection camiContent;
	
	public AuthenticationVersionAck(AckOpenConnection camiContent) {
		this.camiContent = camiContent;
	}

	public AckOpenConnection getCamiContent() {
		return camiContent;
	}

}
