package fr.lip6.move.coloane.api.cami.input.results;

public final class QuestionAnswer {

	private String serviceName;
	private String questionName;

	public QuestionAnswer(String serviceName, String questionName) {
		super();
		this.serviceName = serviceName;
		this.questionName = questionName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getQuestionName() {
		return questionName;
	}
}
