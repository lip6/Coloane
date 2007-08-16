package fr.lip6.move.coloane.ui.dialogs;

import java.util.ArrayList;


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

	public int getAnswerType() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getDialogId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<String> getText() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasBeenModified() {
		// TODO Auto-generated method stub
		return false;
	}
}
