package fr.lip6.move.coloane.ui.dialogs;

public class CAMIDialogFactory {
	public CAMIDialog create(int id, int type, int buttonType,
			String title, String help, String message,
			boolean inputDialog, boolean multiLine, String defaultValue)
		throws UnknowDialogException {
		switch (type) {
		case CAMIDialog.DLG_STANDARD:
			return new StandardDialog(id, buttonType, title, help, message,
					inputDialog, multiLine, defaultValue);
		case CAMIDialog.DLG_WARNING:
			return new WarningDialog(id, buttonType, title, help, message,
					inputDialog, multiLine, defaultValue);
		case CAMIDialog.DLG_ERROR:
			return new ErrorDialog(id, buttonType, title, help, message,
					inputDialog, multiLine, defaultValue);
		case CAMIDialog.DLG_INTERACTIVE:
			return new InteractiveDialog(id, buttonType, title, help, message,
					inputDialog, multiLine, defaultValue);
		default:
			throw new UnknowDialogException();
		}
	}
}
