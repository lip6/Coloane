package fr.lip6.move.coloane.ui.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class StandardTextArea extends TextArea {
	public StandardTextArea(Composite parent, int inputType,
			int multiLine, String defaultValue) {
		super(parent, inputType, multiLine,defaultValue);
		
		textWidget = new Text(parent, SWT.SINGLE);
	}
}
