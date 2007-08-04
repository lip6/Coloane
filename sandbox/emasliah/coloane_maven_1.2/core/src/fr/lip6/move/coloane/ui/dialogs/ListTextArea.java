package fr.lip6.move.coloane.ui.dialogs;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

public class ListTextArea extends TextArea {
	public ListTextArea(Composite parent,
			int multiLine, String defaultValue) {
		super(parent, TextArea.INPUT_FORBIDDEN, multiLine,defaultValue);
		if (multiLine == TextArea.MULTI_LINE_WITH_SINGLE_SELECTION) {
			textWidget = new List(parent, SWT.SINGLE);
		} else {
			textWidget = new List(parent, SWT.MULTI);
		}
	}

	public void addChoice(String choice) {
		((List)textWidget).add(choice);
	}

	public ArrayList<String> getText() {
		ArrayList<String> result = new ArrayList<String>();

		for (String selection : ((List)textWidget).getSelection()) {
			result.add(selection);
		}

		return result;
	}
}
