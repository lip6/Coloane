package fr.lip6.move.coloane.ui.dialogs;

public class DialogFactory {
	public static IDialog create(int id, int type, int buttonType,
			String title, String help, String message,
			int inputType, int multiLine, String defaultValue) throws UnknowDialogException {

		switch (type) {
			case IDialog.DLG_INTERACTIVE:
				return new InteractiveDialog(id, buttonType, title, help, message,
						inputType, multiLine, defaultValue);
	
			case IDialog.DLG_STANDARD:
			case IDialog.DLG_WARNING:
			case IDialog.DLG_ERROR:
				return new SimpleDialog(id, type, buttonType, title, help, message,
						inputType, multiLine, defaultValue);
	
			default:
				throw new UnknowDialogException();
		}
	}
}
