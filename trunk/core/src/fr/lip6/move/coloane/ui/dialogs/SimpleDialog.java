package fr.lip6.move.coloane.ui.dialogs;

import fr.lip6.move.coloane.interfaces.IDialogResult;
import fr.lip6.move.coloane.interfaces.objects.IDialogCom;
import fr.lip6.move.coloane.ui.dialogs.textarea.ListTextArea;
import fr.lip6.move.coloane.ui.dialogs.textarea.TextArea;
import fr.lip6.move.coloane.ui.dialogs.textarea.TextAreaFactory;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IconAndMessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;


/**
 * Une boite de dialogue est divise en quatre zones :
 * <ul>
 * 	<li>Une zone d'incone indiquant si la boite de dialogue est un warning, une erreur etc...</li>
 * 	<li>Une zone de message</li>
 * 	<li>Une zone de texte (simple ou multi-lignes)</li>
 * 	<li>Une zone de boutons</li>
 * </ul>
 */

public class SimpleDialog extends IconAndMessageDialog implements IDialog {

	private int id;
	private int buttonType;
	private int inputType;
	private int multiLine;
	private String defaultValue;
	private IDialogResult dialogResult;
	private ArrayList<String> choices = null;

	private static Shell parentShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	private TextArea textArea = null;

	/**
	 * Constructeur de la boite de dialogue
	 * @param dialog La boite de dialogue construite par la com
	 */
	public SimpleDialog(IDialogCom dialog) {

		super(parentShell);

		this.id = dialog.getId();
		this.buttonType = dialog.getButtonType();
		this.message = dialog.getMessage();
		this.inputType = dialog.getInputType();
		this.multiLine = dialog.getMultiLine();
		this.defaultValue = dialog.getDefault();

		/*
		 * There is a kind of dialog which does not contain any "OK" or "Cancel" button.
		 * For this kind of dialog, we create a default result.
		 * For the other dialog, this result will be overwritten by a result created
		 * when the user will clic on "OK" or "Cancel".
		 */
		dialogResult = new DialogResult(id, IDialog.TERMINATED_OK, false, new ArrayList<String>());

		choices = new ArrayList<String>();
	}


	/**
	 * Determine quels seront les boutons affiches
	 * @param parent La fenetre en cours de construction
	 */
	protected final void createButtonsForButtonBar(Composite parent) {
		switch (buttonType) {
			case IDialogCom.DLG_OK:
				createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
				break;
			case IDialogCom.DLG_OK_CANCEL:
				createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
				createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
				break;
			default:
				break;
		}
	}

	/**
	 * Creation de la boite de dialogue
	 * @param parent La fenetre en cours de construction
	 * @return Control
	 */
	@Override
	protected final Control createDialogArea(Composite parent) {
		createMessageArea(parent);

		// Create a composite to hold the textArea
		Composite composite = new Composite(parent, SWT.NONE);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 2;
		composite.setLayoutData(data);
		composite.setLayout(new FillLayout());

		textArea = TextAreaFactory.create(composite, inputType, multiLine, defaultValue);

		if (textArea instanceof ListTextArea) {
			for (String choice : choices) {
				textArea.addChoice(choice);
			}
		}

		return composite;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#buttonPressed(int)
	 */
	@Override
	public final void buttonPressed(int buttonId) {
		int answerType;

		/* Selon le type de retour */
		if (buttonId == OK) { answerType = TERMINATED_OK; } else { answerType = TERMINATED_CANCEL; }

		dialogResult = new DialogResult(id, answerType,	!textArea.getText().get(0).equals(defaultValue), textArea.getText());

		this.close();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.dialogs.IDialog#addChoice(java.lang.String)
	 */
	public final void addChoice(String choice) {
		choices.add(choice);
		if (textArea != null && textArea instanceof ListTextArea) {
			textArea.addChoice(choice);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.dialogs.IDialog#getDialogResult()
	 */
	public final IDialogResult getDialogResult() {
		return dialogResult;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IconAndMessageDialog#getImage()
	 */
	@Override
	protected final Image getImage() {
		return null;
	}
}
