package fr.lip6.move.coloane.ui.dialogs;

public abstract class TextArea {
	
	public static final int INPUT_AUTHORIZED = 1;
	public static final int INPUT_UNAUTHORIZED = 2;
	public static final int INPUT_AND_ABORT_AUTHORIZED = 5;
	
	public static final int SINGLE_LINE = 1;
	public static final int MULTI_LINE_WITH_SINGLE_SELECTION = 2;
	public static final int MULTI_LINE_WITH_MULTI_SELECTION = 5;
	
	protected boolean multiLine;
	protected boolean inputAuthorized;
	protected boolean abortAuthorized;
	protected boolean multiSelection;
	protected String defaultValue;
	
	public TextArea(String message, boolean inputAuthorized,
			boolean abortAuthorized, boolean multiLine,
			String defaultValue) {
		
	}
}
