package fr.lip6.move.coloane.ui.dialogs;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class TextArea implements ITextArea {

	public static final int INPUT_AUTHORIZED = 1;
	public static final int INPUT_FORBIDDEN = 2;
	public static final int INPUT_AND_ABORT_AUTHORIZED = 5;

	public static final int SINGLE_LINE = 1;
	public static final int MULTI_LINE_WITH_SINGLE_SELECTION = 2;
	public static final int MULTI_LINE_WITH_MULTI_SELECTION = 5;

	protected String defaultValue;
	protected Control textWidget;
	protected String finalValue = defaultValue;

	/*
	 * In CAMI : inputType, multiLine, defaultValue
	 */
	public TextArea(Composite parent, int inputType, int multiLine, String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public abstract ArrayList<String> getText();

	public final void setToolTiptext(String toolTipText) {
		textWidget.setToolTipText(toolTipText);
	}
}
