package fr.lip6.move.coloane.ui.dialogs;

import java.util.ArrayList;
import fr.lip6.move.coloane.interfaces.IDialogResult;

public class DialogResult implements IDialogResult {
	protected int dialogId;
	protected int answerType;
	protected boolean modified;
	ArrayList<String> text;

	public DialogResult(int dialogId, int answerType, boolean modified, ArrayList<String> text) {
		this.dialogId = dialogId;
		this.answerType = answerType;
		this.modified = modified;
		this.text = text;
	}

	public ArrayList<String> getText() {
		return text;
	}

	public int getAnswerType() {
		return answerType;
	}

	public int getDialogId() {
		return dialogId;
	}

	public boolean hasBeenModified() {
		return modified;
	}
}
