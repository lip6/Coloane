package fr.lip6.move.coloane.api.cami.input.dialog;

import fr.lip6.move.coloane.api.cami.ICommand;

public final class HideDialog implements ICommand {

	private int dialogId;

	public HideDialog(int dialogId) {
		super();
		this.dialogId = dialogId;
	}

	public int getDialogId() {
		return dialogId;
	}

}
