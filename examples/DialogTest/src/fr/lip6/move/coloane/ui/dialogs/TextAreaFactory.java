package fr.lip6.move.coloane.ui.dialogs;

import org.eclipse.swt.widgets.Composite;

public class TextAreaFactory {
	public static TextArea create(Composite parent,
			int inputType, int multiLine, String defaultVal) {
		
		if (inputType == TextArea.INPUT_FORBIDDEN && multiLine != TextArea.SINGLE_LINE)
			return new ListTextArea(parent,	multiLine, defaultVal);
		else
			return new EditableTextArea(parent, multiLine, defaultVal);
	}
}
