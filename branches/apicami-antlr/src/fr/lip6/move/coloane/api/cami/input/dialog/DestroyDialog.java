package fr.lip6.move.coloane.api.cami.input.dialog;

import fr.lip6.move.coloane.api.cami.ICommand;

public class DestroyDialog implements ICommand {

	private int dialogId;

	public DestroyDialog(int dialogId) {
		super();
		this.dialogId = dialogId;
	}

	public int getDialogId() {
		return dialogId;
	}
}
