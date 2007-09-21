package fr.lip6.move.coloane.core.ui.dialogs.textarea;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class EditableTextArea extends TextArea {

	/**
	 * Constructeur
	 * @param parent La boite de dialogue en cours de construction
	 * @param multiLine Indicateur multiligne
	 * @param defaultValue Valeur par defaut
	 */
	public EditableTextArea(Composite parent, int multiLine, String defaultValue) {
		super(parent, TextArea.INPUT_AUTHORIZED, multiLine, defaultValue);

		if (multiLine == TextArea.SINGLE_LINE) {
			setTextWidget(new Text(parent, SWT.SINGLE));
		} else {
			parent.getParent().setSize(400, 300);
			setTextWidget(new Text(parent, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL));
		}

		((Text) getTextWidget()).setText(defaultValue);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.dialogs.textarea.TextArea#getText()
	 */
	public final ArrayList<String> getText() {
		ArrayList<String> result = new ArrayList<String>();
		String[] tokens = ((Text) getTextWidget()).getText().split("(\n\r)|(\r\n)|(\n)|(\r)"); //$NON-NLS-1$

		for (String token : tokens) {
			result.add(token);
		}

		return result;
	}

	/* Add a choice in a DummyTextArea is a nonsense. */
	public final void addChoice(String choice) {
		throw new UnsupportedOperationException();
	}
}

