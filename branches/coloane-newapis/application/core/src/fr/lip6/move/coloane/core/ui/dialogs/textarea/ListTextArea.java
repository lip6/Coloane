package fr.lip6.move.coloane.core.ui.dialogs.textarea;

import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

/**
 * Zone de texte avec une liste d'élement selectionnable
 */
public class ListTextArea extends TextArea {

	/**
	 * Constrcuteur de la zone de liste
	 * @param parent La boite de dialogue en cours de construction
	 * @param multiLine Indicateur de selection multiligne
	 * @param defaultValue Valeur par defaut
	 */
	public ListTextArea(Composite parent, int multiLine, String defaultValue) {
		super(parent, IDialog.INPUT_FORBIDDEN, multiLine, defaultValue);

		/* Autorise-t-on les choix multiples ? */
		if (multiLine == IDialog.MULTI_LINE_WITH_SINGLE_SELECTION) {
			setTextWidget(new List(parent, SWT.SINGLE | SWT.BORDER));
		} else {
			setTextWidget(new List(parent, SWT.MULTI | SWT.BORDER));
		}
	}

	/** {@inheritDoc} */
	public final void addChoice(String choice) {
		((List) getTextWidget()).add(choice);
	}

	/** {@inheritDoc} */
	@Override
	public final java.util.List<String> getText() {
		java.util.List<String> result = new ArrayList<String>();

		for (String selection : ((List) getTextWidget()).getSelection()) {
			result.add(selection);
		}

		return result;
	}
}
