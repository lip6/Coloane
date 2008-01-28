package fr.lip6.move.coloane.api.cami.input.results;

import java.util.Collection;

import fr.lip6.move.coloane.api.cami.input.messages.IMessage;

public final class Results {

	public enum ResultType {
		completeAnswer(1), incompleteAnswer(2), modelHasBeenModified(3);

		private int value;

		private ResultType(int value) {
			this.value = value;
		}

		public int getInt() {
			return this.value;
		}

	}

	private QuestionAnswer questionAnswer;
	private Collection<IMessage> messages;
	private Collection<QuestionState> questionStates;
	private Collection<ResultSet> resultSets;
	private ResultType resultType;

	public Results(QuestionAnswer questionAnswer, Collection<IMessage> messages,
			Collection<QuestionState> questionStates, Collection<ResultSet> resultSets, ResultType resultType) {
		this.questionAnswer = questionAnswer;
		this.messages = messages;
		this.questionStates = questionStates;
		this.resultSets = resultSets;
		this.resultType = resultType;
	}

	public static ResultType ResultType(int i) {
		ResultType toReturn = ResultType.completeAnswer;
		for (ResultType s : ResultType.values()) {
			if (s.value == i) {
				toReturn = s;
			}
		}
		return toReturn;
	}

	public QuestionAnswer getQuestionAnswer() {
		return questionAnswer;
	}

	public Collection<IMessage> getMessages() {
		return messages;
	}

	public Collection<QuestionState> getQuestionStates() {
		return questionStates;
	}

	public Collection<ResultSet> getResultSets() {
		return resultSets;
	}

	public ResultType getResultType() {
		return resultType;
	}

}
