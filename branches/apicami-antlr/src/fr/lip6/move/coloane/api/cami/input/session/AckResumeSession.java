package fr.lip6.move.coloane.api.cami.input.session;

public class AckResumeSession {

	private String sessionName;

	public AckResumeSession(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getSessionName() {
		return sessionName;
	}

}
