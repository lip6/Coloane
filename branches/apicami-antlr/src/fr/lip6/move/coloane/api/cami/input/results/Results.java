package fr.lip6.move.coloane.api.cami.results;

import com.sun.tools.javac.util.List;

import fr.lip6.move.coloane.api.cami.Question;
import fr.lip6.move.coloane.api.cami.SpecialMessages;
import fr.lip6.move.coloane.api.cami.WarningMessage;
import fr.lip6.move.coloane.api.cami.results.QuestionState;
import fr.lip6.move.coloane.api.cami.results.ResultSet;

public final class Results {

	public enum ResultType {
		completeAnswer(), 
		incompleteAnswer(), 
		modelHasBeenModified();
		
		private ResultType() {
			// TODO Auto-generated constructor stub
		}
	}
	
	public Question question;
	public List<SpecialMessages> specialMessages;
	public List<WarningMessage> warningMessages;
	public List<QuestionState> questionStates;
	public List<ResultSet> results;
	public ResultType resultType;
	
	public Results(	Question question, List<SpecialMessages> specialMessages, List<WarningMessage> warningMessages,
					List<QuestionState> questionSates, List<ResultSet> resultSet, ResultType resultType) {
		this.question = question;
		this.specialMessages = specialMessages;
		this.warningMessages = warningMessages;
		this.questionStates = questionSates;
		this.results = resultSet;
		this.resultType = resultType;
	}
	
	public void addSpecialMessage(SpecialMessages specialMessage) {
		this.specialMessages.add(specialMessage);
	}
	
	public void addWarningMessage(WarningMessage warningMessage) {
		this.warningMessages.add(warningMessage);
	}
	
	public void addQuestionState(QuestionState questionState) {
		this.questionStates.add(questionState);
	}
	
	public void addResultSet(ResultSet resultSet) {
		this.results.add(resultSet);
	}
	
}
