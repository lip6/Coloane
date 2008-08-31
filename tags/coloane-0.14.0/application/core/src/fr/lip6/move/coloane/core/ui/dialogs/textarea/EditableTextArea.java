package fr.lip6.move.coloane.core.ui.dialogs.textarea;

import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * Zone de texte éditable
 */
public class EditableTextArea extends TextArea {

	/**
	 * Constructeur
	 * @param parent La boite de dialogue en cours de construction
	 * @param multiLine Indicateur multiligne
	 * @param defaultValue Valeur par defaut
	 */
	public EditableTextArea(Composite parent, int multiLine, String defaultValue) {
		super(parent, IDialog.INPUT_AUTHORIZED, multiLine, defaultValue);

		if (multiLine == IDialog.SINGLE_LINE) {
			setTextWidget(new Text(parent, SWT.SINGLE | SWT.BORDER));
		} else {
			parent.getParent().setSize(400, 300);
			setTextWidget(new Text(parent, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL));
		}

		((Text) getTextWidget()).setText(defaultValue);
	}

	/** {@inheritDoc} */
	@Override
	public final List<String> getText() {
		List<String> result = new ArrayList<String>();
		String[] tokens = ((Text) getTextWidget()).getText().split("(\n\r)|(\r\n)|(\n)|(\r)"); //$NON-NLS-1$

		for (String token : tokens) {
			result.add(token);
		}

		return result;
	}

	/** {@inheritDoc} */
	public final void addChoice(String choice) {
		throw new UnsupportedOperationException();
	}
}

