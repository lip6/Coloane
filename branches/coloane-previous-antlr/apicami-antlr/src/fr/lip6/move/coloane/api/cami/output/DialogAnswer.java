package fr.lip6.move.coloane.api.cami.output;

import java.util.Collection;

import fr.lip6.move.coloane.api.cami.input.results.ResultSet.ResultSetType;

public final class DialogAnswer {

	public enum DialogAnswerType {
		ok(1),
		cancel(2);
		
		private int value;
		
		private DialogAnswerType(int value) {
			this.value = value;
		}
		
		public int getInt() {
			return this.value;
		}
	}
	
	public enum DialogModificationType {
		modified(1),
		notModified(2);
		
		private int value;
		
		private DialogModificationType(int value) {
			this.value = value;
		}
		
		public int getInt() {
			return this.value;
		}
	}
	
	public int dialogID;
	public DialogAnswerType answerType;
	public DialogModificationType modificationType;
	public String valueEntered;
	public Collection<String> lines;

	public DialogAnswer(int dialogID, 
						DialogAnswerType answerType,
						DialogModificationType modificationType,
						String valueEntered, Collection<String> lines) {
		this.dialogID = dialogID;
		this.answerType = answerType;
		this.modificationType = modificationType;
		this.valueEntered = valueEntered;
		this.lines = lines;
	}

	public static DialogAnswerType DialogAnswerType(int i) {
		DialogAnswerType toReturn = DialogAnswerType.ok;
		for( DialogAnswerType s : DialogAnswerType.values() ) {
			if( s.value == i ) {
				toReturn = s;
			}
		}
		return toReturn;
	}

	public static DialogModificationType DialogModificationType(int i) {
		DialogModificationType toReturn = DialogModificationType.modified;
		for( DialogModificationType s : DialogModificationType.values() ) {
			if( s.value == i ) {
				toReturn = s;
			}
		}
		return toReturn;
	}

}
