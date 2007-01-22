package fr.lip6.move.coloane.ui.dialogs;

public interface IDialog {
	public static final int DLG_STANDARD = 1;
	public static final int DLG_WARNING = 2;
	public static final int DLG_ERROR = 3;
	public static final int DLG_INTERACTIVE = 4;
	
	public static final int DLG_NO_BUTTON = 1;
	public static final int DLG_OK = 2;
	public static final int DLG_OK_CANCEL = 3;
	
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
