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
		else {
			parent.getParent().setSize(400, 300);
			textWidget =
				new Text(parent, SWT.MULTI | SWT.WRAP | SWT.BORDER);
		}
		
		((Text)textWidget).setText(defaultValue);
	}
	
	public ArrayList<String> getText() {
		ArrayList<String> result = new ArrayList<String>();
		
		String[] tokens = ((Text)textWidget).getText().split("\n");
		
		for (String token : tokens)
			result.add(token);
		
		return result;
	}

	public void addChoice(String choice) {
		throw new UnsupportedOperationException();
	}
}

