package fr.lip6.move.coloane.api.cami.input.results;

public final class QuestionState {

	public String serviceName;
	public String questionName;
	public int state;
	public String message;
	
	public QuestionState(String serviceName, String questionName, int state, String message) {
		this.serviceName = serviceName;
		this.questionName = questionName;
		this.state = state;
		this.message = message;
	}
}
