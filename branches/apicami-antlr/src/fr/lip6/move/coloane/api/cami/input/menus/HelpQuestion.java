package fr.lip6.move.coloane.api.cami.input.menus;

public final class HelpQuestion {

	private String questionName;
	private String helpMessage;

	public HelpQuestion(String questionName, String helpMessage) {
		this.questionName = questionName;
		this.helpMessage = helpMessage;
	}

	public String getQuestionName() {
		return questionName;
	}

	public String getHelpMessage() {
		return helpMessage;
	}

}
