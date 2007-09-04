package fr.lip6.move.coloane.api.cami.input.dialog;

public final class NextDialog {

	private int dialogId;
	private String line;

	public NextDialog(int dialogId, String line) {
		super();
		this.dialogId = dialogId;
		this.line = line;
	}

	public int getDialogId() {
		return dialogId;
	}

	public String getLine() {
		return line;
	}

}
