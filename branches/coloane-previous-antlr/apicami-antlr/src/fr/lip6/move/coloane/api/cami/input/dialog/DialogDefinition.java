package fr.lip6.move.coloane.api.cami.input.dialog;

import java.util.Collection;

public final class DialogDefinition {

	private DialogCreation dialogCreation;
	private Collection<NextDialog> nextDialogs;

	public DialogDefinition(DialogCreation dialogCreation, Collection<NextDialog> nextDialogs) {
		this.dialogCreation = dialogCreation;
		this.nextDialogs = nextDialogs;
	}

	public DialogCreation getDialogCreation() {
		return dialogCreation;
	}

	public Collection<NextDialog> getNextDialogs() {
		return nextDialogs;
	}
}
