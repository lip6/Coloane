package fr.lip6.move.coloane.api.cami.input.menus;

import java.util.Collection;

import fr.lip6.move.coloane.api.cami.input.results.QuestionState;

public class ServiceMenuModification {

	private EnableMainQuestion enableMainQuestion;
	private Collection<QuestionState> questionStates;
	private EndMenuTransmission endMenuTransmission;

	public ServiceMenuModification(EnableMainQuestion enableMainQuestion, Collection<QuestionState> questionStates,
			EndMenuTransmission endMenuTransmission) {
		this.enableMainQuestion = enableMainQuestion;
		this.questionStates = questionStates;
		this.endMenuTransmission = endMenuTransmission;
	}

	public EnableMainQuestion getEnableMainQuestion() {
		return enableMainQuestion;
	}

	public Collection<QuestionState> getQuestionStates() {
		return questionStates;
	}

	public EndMenuTransmission getEndMenuTransmission() {
		return endMenuTransmission;
	}

}
