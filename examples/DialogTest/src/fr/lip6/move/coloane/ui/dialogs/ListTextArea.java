package fr.lip6.move.coloane.ui.dialogs;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

public class ListTextArea extends TextArea {
	public ListTextArea(Composite parent,
			int multiLine, String defaultValue) {
		super(parent, TextArea.INPUT_FORBIDDEN, multiLine,defaultValue);
	}

	public ArrayList<String> getText() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
