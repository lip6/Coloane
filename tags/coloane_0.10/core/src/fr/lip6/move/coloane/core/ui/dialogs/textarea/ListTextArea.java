package fr.lip6.move.coloane.core.ui.dialogs.textarea;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

public class ListTextArea extends TextArea {

	/**
	 * Constrcuteur de la zone de liste
	 * @param parent La boite de dialogue en cours de construction
	 * @param multiLine Indicateur de selection multiligne
	 * @param defaultValue Valeur par defaut
	 */
	public ListTextArea(Composite parent, int multiLine, String defaultValue) {
		super(parent, TextArea.INPUT_FORBIDDEN, multiLine, defaultValue);

		/* Autorise-t-on les choix multiples ? */
		if (multiLine == TextArea.MULTI_LINE_WITH_SINGLE_SELECTION) {
			setTextWidget(new List(parent, SWT.SINGLE));
		} else {
			setTextWidget(new List(parent, SWT.MULTI));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.dialogs.textarea.ITextArea#addChoice(java.lang.String)
	 */
	public final void addChoice(String choice) {
		((List) getTextWidget()).add(choice);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.dialogs.textarea.TextArea#getText()
	 */
	public final ArrayList<String> getText() {
		ArrayList<String> result = new ArrayList<String>();

		for (String selection : ((List) getTextWidget()).getSelection()) {
			result.add(selection);
		}

		return result;
	}
}
