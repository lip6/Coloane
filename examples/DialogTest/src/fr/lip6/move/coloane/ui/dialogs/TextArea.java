package fr.lip6.move.coloane.ui.dialogs;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class TextArea {
	
	public static final int INPUT_AUTHORIZED = 1;
	public static final int INPUT_FORBIDDEN = 2;
	public static final int INPUT_AND_ABORT_AUTHORIZED = 5;
	
	public static final int SINGLE_LINE = 1;
	public static final int MULTI_LINE_WITH_SINGLE_SELECTION = 2;
	public static final int MULTI_LINE_WITH_MULTI_SELECTION = 5;
	
	protected boolean inputAuthorized;
	protected boolean abortAuthorized;
	protected boolean multiLine;
	protected boolean multiSelection;
	protected String defaultValue;
	protected Control textWidget;
	
	/*
	 * In CAMI : inputType, multiLine, defaultValue
	 */
	public TextArea(Composite parent, int inputType,
			int multiLine, String defaultValue) {
		setinputAuthorized(inputType);
		setAbortAuthorized(inputType);
		setMultiLine(multiLine);
		setMultiSelection(multiLine);
		this.defaultValue = defaultValue;
	}
	
	protected void setinputAuthorized(int inputType) {
		inputAuthorized = (inputType == INPUT_FORBIDDEN ? false : true);
	}
	
	protected void setAbortAuthorized(int inputType) {
		abortAuthorized = (inputType == INPUT_AND_ABORT_AUTHORIZED ? true : false);
	}
	
	protected void setMultiLine(int multiLine) {
		this.multiLine = (multiLine == 1 ? false : true);
	}
	
	protected void setMultiSelection(int multiLine) {
		multiSelection = (multiLine == MULTI_LINE_WITH_MULTI_SELECTION ? true : false);
	}
	
	public Control getTextWidget() {
		return textWidget;
	}
}
