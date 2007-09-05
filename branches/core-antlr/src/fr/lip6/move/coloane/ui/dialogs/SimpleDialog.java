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

	/** L'identifiant de la boite de dialogue */
	private int id;

	/** Le type de la boite de dialogue : ERROR, WARNING, STANDART */
	private int type;

	/** Les types de boutons de la boite de dialogue */
	private int buttonType;

	/** Le type de saisie */
	private int inputType;

	/** Est-ce que la saisie est multi-ligne ? */
	private int multiLine;

	/** Valeur par defaut */
	private String defaultValue;

	/** L'objet de resultats */
	private IDialogResult dialogResult;

	/** Les choix de la boite de dialogue */
	private ArrayList<String> choices = null;

	private static Shell parentShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	private TextArea textArea = null;

	/**
	 * Constructeur de la boite de dialogue
	 * @param dialog La boite de dialogue construite par la com
	 */
	public SimpleDialog(IDialogCom dialog) {
		super(parentShell);

		// On recupere les informations de l'objet transmis par l'API
		id = dialog.getId();
		type = dialog.getType();
		buttonType = dialog.getButtonType();
		message = dialog.getMessage();
		inputType = dialog.getInputType();
		multiLine = dialog.getMultiLine();
		defaultValue = dialog.getDefault();

		// La liste des choix
		choices = new ArrayList<String>();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected final void createButtonsForButtonBar(Composite parent) {
		switch (buttonType) {
			// 1 bouton
			case IDialogCom.DLG_OK:
				createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
				break;
			// 2 boutons
			case IDialogCom.DLG_OK_CANCEL:
				createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
				createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
				break;
			// Aucun bouton
			default:
				break;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
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
			for (String choice : choices) { textArea.addChoice(choice); }
		}

		return composite;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#buttonPressed(int)
	 */
	@Override
	public final void buttonPressed(int buttonId) {

		// Selon le type de retour
		int answerType;
		if (buttonId == OK) {
			answerType = TERMINATED_OK;
		} else {
			answerType = TERMINATED_CANCEL;
		}

		// Le contenu de la boite de dialogue a-t-elle ete modifiee ?
		boolean hasbeenmodified = !textArea.getText().get(0).equals(defaultValue);

		dialogResult = new DialogResult(id, answerType, hasbeenmodified, textArea.getText());

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
		switch (type) {
		case IDialogCom.DLG_ERROR:	return getErrorImage();
		case IDialogCom.DLG_WARNING: return getWarningImage();
		case IDialogCom.DLG_STANDARD: return getInfoImage();
		default: return getInfoImage();
		}
	}
}
