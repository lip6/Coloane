package fr.lip6.move.coloane.interfaces;

public interface IDialog {
	
	public static final int DLG_STANDARD = 1;
	public static final int DLG_WARNING = 2;
	public static final int DLG_ERROR = 3;
	public static final int DLG_INTERACTIVE = 4;
	
	public static final int DLG_NO_BUTTON = 1;
	public static final int DLG_OK = 2;
	public static final int DLG_OK_CANCEL = 3;
	
	public static final int INPUT_AUTHORIZED = 1;
	public static final int INPUT_FORBIDDEN = 2;
	public static final int INPUT_AND_ABORT_AUTHORIZED = 5;
	
	public static final int SINGLE_LINE = 1;
	public static final int MULTI_LINE_WITH_SINGLE_SELECTION = 2;
	public static final int MULTI_LINE_WITH_MULTI_SELECTION = 5;
	
}
