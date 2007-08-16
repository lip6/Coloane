package fr.lip6.move.coloane.ui.dialogs;

import fr.lip6.move.coloane.interfaces.DialogConstants;

public class DialogFactory {
	public static IDialog create(int id, int type, int buttonType, String title, String help, String message, int inputType, int multiLine, String defaultValue) throws UnknowDialogException {

		switch (type) {
			case DialogConstants.DLG_INTERACTIVE:
				return new InteractiveDialog(id, buttonType, title, help, message,
						inputType, multiLine, defaultValue);

			case DialogConstants.DLG_STANDARD:
			case DialogConstants.DLG_WARNING:
			case DialogConstants.DLG_ERROR:
				return new SimpleDialog(id, type, buttonType, title, help, message,
						inputType, multiLine, defaultValue);

			default:
				throw new UnknowDialogException();
		}
	}
}
