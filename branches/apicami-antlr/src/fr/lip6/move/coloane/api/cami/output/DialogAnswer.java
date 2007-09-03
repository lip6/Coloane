package fr.lip6.move.coloane.api.cami.output;

import com.sun.tools.javac.util.List;

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
	public List<String> lines;

	public DialogAnswer(int dialogID, DialogAnswerType answerType, DialogModificationType modificationType, String valueEntered, List<String> lines) {
		this.dialogID = dialogID;
		this.answerType = answerType;
		this.modificationType = modificationType;
		this.valueEntered = valueEntered;
		this.lines = lines;
	}

	public DialogAnswer(int dialogID, DialogAnswerType answerType, DialogModificationType modificationType, String valueEntered) {
		this(dialogID,answerType,modificationType,valueEntered,null);
	}

}
