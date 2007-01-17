package fr.lip6.move.coloane.ui.dialogs;

import org.eclipse.swt.widgets.Composite;

public class TextAreaFactory {
	public TextArea create(Composite parent, boolean list,
			int inputType, int multiLine, String defaultVal) {
		
		if (list)
			return new ListTextArea(parent, inputType,
					multiLine, defaultVal);
		else
			return new EditableTextArea(parent, inputType,
					multiLine, defaultVal);
	}
}
