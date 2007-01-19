package fr.lip6.move.coloane.ui.dialogs;

import java.util.ArrayList;

public class DialogResult {
	protected int dialogId;
	protected int answerType;
	protected boolean modified;
	ArrayList<String> text;
	
	public DialogResult(int dialogId, int answerType,
			boolean modified, ArrayList<String> text) {
		this.dialogId = dialogId;
		this.answerType = answerType;
		this.modified = modified;
		this.text = text;
	}
	
	public String getText() {
		return text.get(0);
	}

	public int getAnswerType() {
		return answerType;
	}

	public int getDialogId() {
		return dialogId;
	}

	public boolean isModified() {
		return modified;
	}
}
