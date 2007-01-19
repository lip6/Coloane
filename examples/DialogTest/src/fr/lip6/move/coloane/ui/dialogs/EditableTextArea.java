package fr.lip6.move.coloane.ui.dialogs;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;

public class EditableTextArea extends TextArea {

	public EditableTextArea(Composite parent,
			int multiLine, String defaultValue) {
		super(parent, TextArea.INPUT_AUTHORIZED, multiLine, defaultValue);
		
		if (multiLine == TextArea.SINGLE_LINE)
			textWidget = new Text(parent, SWT.SINGLE);
		else
			textWidget = new Text(parent, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		
		textWidget.setSize(250, 500);
	}
	
	public ArrayList<String> getText() {
		ArrayList<String> result = new ArrayList<String>();
		result.add(((Text)textWidget).getText());
		
		return result;
	}
}
