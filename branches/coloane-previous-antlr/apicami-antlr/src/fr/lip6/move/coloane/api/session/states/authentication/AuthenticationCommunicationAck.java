package fr.lip6.move.coloane.api.session.states.authentication;

import fr.lip6.move.coloane.api.cami.input.connection.AckOpenCommunication;

public final class AuthenticationCommunicationAck implements IAuthenticationMessage {

	private AckOpenCommunication camiContent;
	
	public AuthenticationCommunicationAck(AckOpenCommunication camiContent) {
		this.camiContent = camiContent;
	}

	public AckOpenCommunication getCamiContent() {
		return camiContent;
	}

}
