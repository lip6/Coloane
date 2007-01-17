package fr.lip6.move.coloane.ui.dialogs;


public class InteractiveDialog extends CAMIDialog {

	public InteractiveDialog(int id, int buttonType,
			String title, String help, String message,
			int inputType, int multiLine, String defaultValue) {
		super(id, buttonType, title, help, message,
				inputType, multiLine, defaultValue);
	}
	
}
