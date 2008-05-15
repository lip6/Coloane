package fr.lip6.move.coloane.core.ui.dialogs.textarea;


import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

public class DummyTextArea extends TextArea {

	/**
	 * Constructeur de la zone de texte
	 * @param parent La boite de dialogue en cours de construction
	 */
	public DummyTextArea(Composite p) {
		super(p, TextArea.INPUT_FORBIDDEN, TextArea.SINGLE_LINE, ""); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.dialogs.textarea.TextArea#getText()
	 */
	public final ArrayList<String> getText() {
		ArrayList<String> result = new ArrayList<String>();
		result.add(""); //$NON-NLS-1$
		return result;
	}

	/* Add a choice in a DummyTextArea is a nonsense. */
	public final void addChoice(String choice) {
		throw new UnsupportedOperationException();
	}
}
