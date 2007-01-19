package fr.lip6.move.coloane.ui.dialogs;

public class CAMIDialogFactory {
	public static ICAMIDialog create(int id, int type, int buttonType,
			String title, String help, String message,
			int inputType, int multiLine, String defaultValue)
		throws UnknowDialogException {
		
		switch (type) {
		case CAMISimpleDialog.DLG_INTERACTIVE:
			return new InteractiveDialog(id, buttonType, title, help, message,
					inputType, multiLine, defaultValue);
			
		case CAMISimpleDialog.DLG_STANDARD:
		case CAMISimpleDialog.DLG_WARNING:
		case CAMISimpleDialog.DLG_ERROR:
			return new CAMISimpleDialog(id, type, buttonType, title, help, message,
					inputType, multiLine, defaultValue);
			
		default:
			throw new UnknowDialogException();
		}
	}
}
