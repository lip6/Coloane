package fr.lip6.move.coloane.api.cami.input.results;

import java.util.Collection;

import fr.lip6.move.coloane.api.cami.input.connection.CloseConnectionPanic.Severity;
import fr.lip6.move.coloane.api.cami.input.messages.IMessage;

public final class Results {

	public enum ResultType {
		completeAnswer(1), 
		incompleteAnswer(2), 
		modelHasBeenModified(3);
		
		private int value;
		
		private ResultType(int value) {
			this.value = value;
		}
		
		public int getInt() {
			return this.value;
		}
		
		public static ResultType makeResultType(int i) {
			ResultType toReturn = completeAnswer;
			for( ResultType s : ResultType.values() ) {
				if( s.value == i ) {
					toReturn = s;
				}
			}
			return toReturn;
		}

		
	}
	
	public QuestionAnswer questionAnswer;
	public Collection<IMessage> messages;
	public Collection<QuestionState> questionStates;
	public Collection<ResultSet> resultSets;
	public ResultType resultType;
	
	public Results(	QuestionAnswer questionAnswer, Collection<IMessage> messages,
					Collection<QuestionState> questionStates, Collection<ResultSet> resultSets,
					ResultType resultType) {
		this.questionAnswer = questionAnswer;
		this.messages = messages;
		this.questionStates = questionStates;
		this.resultSets = resultSets;
		this.resultType = resultType;
	}	
}
