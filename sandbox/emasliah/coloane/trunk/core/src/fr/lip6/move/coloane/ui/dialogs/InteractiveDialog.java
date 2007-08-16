package fr.lip6.move.coloane.ui.dialogs;


public class InteractiveDialog implements IDialog {

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

	/**
	 * This method adds a choice in a list (in the case of
	 * a simple dialog with a list), so it has no sense here.
	 */
	public void addChoice(String choice) {
		throw new UnsupportedOperationException();
	}
}
