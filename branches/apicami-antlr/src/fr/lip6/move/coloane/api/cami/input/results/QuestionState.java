package fr.lip6.move.coloane.api.cami.input.results;

public final class QuestionState {

	private String serviceName;
	private String questionName;
	private int state;
	private String message;

	public QuestionState(String serviceName, String questionName, int state, String message) {
		this.serviceName = serviceName;
		this.questionName = questionName;
		this.state = state;
		this.message = message;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getQuestionName() {
		return questionName;
	}

	public int getState() {
		return state;
	}

	public String getMessage() {
		return message;
	}

}
