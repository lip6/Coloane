package fr.lip6.move.coloane.core.ui.dialogs.textarea;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

/**
 * Zone de texte simple ligne pour afficher un simple message
 */
public class DummyTextArea extends TextArea {

	/**
	 * Constructeur de la zone de texte
	 * @param p La boite de dialogue en cours de construction
	 */
	public DummyTextArea(Composite p) {
		super(p, TextArea.INPUT_FORBIDDEN, TextArea.SINGLE_LINE, ""); //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	@Override
	public final List<String> getText() {
		List<String> result = new ArrayList<String>();
		result.add(""); //$NON-NLS-1$
		return result;
	}

	/** {@inheritDoc} */
	public final void addChoice(String choice) {
		throw new UnsupportedOperationException();
	}
}
