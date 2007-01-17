package fr.lip6.move.coloane.ui.dialogs;


public class InteractiveDialog extends CAMIDialog {

	public InteractiveDialog(int id, int buttonType,
			String title, String help, String message,
			boolean inputDialog, boolean multiLine, String defaultValue) {
		super(id, buttonType, title, help, message,
				inputDialog, multiLine, defaultValue);
	}
	
}
