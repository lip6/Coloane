package fr.lip6.move.coloane.api.cami.input.menus;

import java.util.Collection;

import fr.lip6.move.coloane.api.cami.input.results.QuestionState;

public class ServiceMenuModification {

	public EnableMainQuestion enableMainQuestion;
	public Collection<QuestionState> questionStates;
	public EndMenuTransmission endMenuTransmission;

	public ServiceMenuModification(EnableMainQuestion enableMainQuestion, Collection<QuestionState> questionStates,
			EndMenuTransmission endMenuTransmission) {
		this.enableMainQuestion = enableMainQuestion;
		this.questionStates = questionStates;
		this.endMenuTransmission = endMenuTransmission;
	}

}
