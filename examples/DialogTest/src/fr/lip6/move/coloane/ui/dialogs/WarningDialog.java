package fr.lip6.move.coloane.ui.dialogs;


public class WarningDialog extends CAMIDialog {
	
	public WarningDialog(int id, int buttonType,
			String title, String help, String message,
			int inputType, int multiLine, String defaultValue) {
		super(id, buttonType, title, help, message,
				inputType, multiLine, defaultValue);
		
		this.message = message;
	}
}
