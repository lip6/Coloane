package fr.lip6.move.coloane.ui.dialogs;

public interface IDialog extends fr.lip6.move.coloane.interfaces.IDialog {
	
	// For the simple dialogs
	public static final int TERMINATED_OK = 1;
	public static final int TERMINATED_CANCEL = 2;
	
	// For the interactive dialogs
	public static final int TERMINATED_ABORT = 1;
	public static final int TERMINATED_QUIT = 2;
	
	public int open();
	public void addChoice(String choice);
	public DialogResult getDialogResult();
	
}
