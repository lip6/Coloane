package fr.lip6.move.coloane.ui.dialogs;


public class InteractiveDialog implements ICAMIDialog {

	public InteractiveDialog(int id, int buttonType,
			String title, String help, String message,
			int inputType, int multiLine, String defaultValue) {
		/*super(id, buttonType, title, help, message,
				inputType, multiLine, defaultValue);*/
	}
	
	public int open() {
		throw new UnsupportedOperationException();
	}

	public DialogResult getDialogResult() {
		// TODO Auto-generated method stub
		return null;
	}
}
