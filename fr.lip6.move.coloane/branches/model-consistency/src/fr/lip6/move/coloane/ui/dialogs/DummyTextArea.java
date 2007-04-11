package fr.lip6.move.coloane.ui.dialogs;


import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class DummyTextArea extends TextArea {

	Composite parent;
	
	public DummyTextArea(Composite parent) {
		super(parent, TextArea.INPUT_FORBIDDEN, TextArea.SINGLE_LINE, "");
		this.parent = parent;
		System.err.println("ICI");
	}
	
	public ArrayList<String> getText() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("");
		
		return result;
	}

	/**
	 * Add a choice in a BummyTextArea is a nonsense.
	 */
	public void addChoice(String choice) {
		throw new UnsupportedOperationException();
	}
	
	public void setToolTiptext(String toolTipText) {
		parent.setToolTipText(toolTipText);
	}
	
	protected void setToolTiptext(String toolTipText, Label label) {
		label.setToolTipText(toolTipText);
	}
}
