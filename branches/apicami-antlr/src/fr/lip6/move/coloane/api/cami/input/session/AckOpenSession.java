package fr.lip6.move.coloane.api.cami.input.session;

public final class AckOpenSession {

	private String sessionName;

	public AckOpenSession(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getSessionName() {
		return sessionName;
	}

}
