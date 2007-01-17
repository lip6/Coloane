package fr.lip6.move.coloane.ui.dialogs;

public class ErrorDialog extends CAMIDialog {
	
	public ErrorDialog(int id, int buttonType,
			String title, String help, String message,
			boolean inputDialog, boolean multiLine, String defaultValue) {
		super(id, buttonType, title, help, message,
				inputDialog, multiLine, defaultValue);
		
		this.message = message;
	}
}
