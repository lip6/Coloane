package fr.lip6.move.coloane.core.ui.dialogs;

import fr.lip6.move.coloane.core.ui.dialogs.textarea.ListTextArea;
import fr.lip6.move.coloane.core.ui.dialogs.textarea.TextArea;
import fr.lip6.move.coloane.core.ui.dialogs.textarea.TextAreaFactory;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IconAndMessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
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
public class SimpleDialog extends IconAndMessageDialog implements IDialogUI {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private static Shell parentShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	/** L'identifiant de la boite de dialogue */
	private int id;

	/** Le type de la boite de dialogue : ERROR, WARNING, STANDART */
	private int type;

	/** Les types de boutons de la boite de dialogue */
	private int buttonType;

	/** Le type de saisie */
	private int inputType;

	/** Est-ce que la saisie est multi-ligne ? */
	private int lineType;

	/** Valeur par defaut */
	private String defaultValue;

	/** L'objet de resultats */
	private IDialogAnswer dialogAnswer;

	/** Les choix de la boite de dialogue */
	private List<String> choices = new ArrayList<String>();

	private TextArea textArea = null;

	/**
	 * Constructeur de la boite de dialogue
	 * @param dialog La boite de dialogue construite par la com
	 */
	public SimpleDialog(IDialog dialog) {
		super(parentShell);

		// On recupere les informations de l'objet transmis par l'API
		id = dialog.getId();
		type = dialog.getType();
		buttonType = dialog.getButtonType();
		message = dialog.getMessage();
		inputType = dialog.getInputType();
		lineType = dialog.getLineType();
		defaultValue = dialog.getDefaultValue();

		// La liste des choix
		if (dialog.getLines() != null) {
			choices.addAll(dialog.getLines());
		}
	}


	/** {@inheritDoc} */
	@Override
	protected final void createButtonsForButtonBar(final Composite parent) {

		// Dans le cas d'une boite de dialogue avec un zone de texte, on ajoute
		// un bouton permettant de charger le contenu d'un fichier
		if (inputType == IDialog.INPUT_AUTHORIZED && lineType != IDialog.SINGLE_LINE) {
			((GridLayout) parent.getLayout()).numColumns++;
			Button open = new Button(parent, SWT.PUSH);
			open.setText(Messages.SimpleDialog_0);
			open.setFont(JFaceResources.getDialogFont());
			open.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					// Boite de dialogue pour choisir le fichier
					FileDialog fd = new FileDialog(parentShell, SWT.OPEN);
					fd.open();
					File in = new File(fd.getFilterPath() + "/" + fd.getFileName()); //$NON-NLS-1$

					// On vérifie un minimum que le fichier peut être lu
					if (in.exists() && in.canRead()) {
						try {
							BufferedReader reader = new BufferedReader(new FileReader(in));
							try {
								StringBuilder sb = new StringBuilder();
								while (reader.ready()) {
									sb.append(reader.readLine()).append("\n"); //$NON-NLS-1$
								}
								((Text) textArea.getTextWidget()).setText(sb.toString());
							} catch (IOException e1) {
								LOGGER.warning(e1.getMessage());
								e1.printStackTrace();
							}
						} catch (FileNotFoundException e1) {
							throw new AssertionError();
						}
					}
				}
			});
			setButtonLayoutData(open);
		}

		// Affichage des boutons classiques
		switch (buttonType) {
			// 1 bouton
			case IDialog.DLG_OK:
				createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
				break;
			// 2 boutons
			case IDialog.DLG_OK_CANCEL:
				createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
				createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
				break;
			// Aucun bouton
			default:
				break;
		}
	}

	/** {@inheritDoc} */
	@Override
	protected final Control createDialogArea(Composite parent) {
		createMessageArea(parent);

		// Create a composite to hold the textArea
		Composite composite = new Composite(parent, SWT.NONE);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 2;
		composite.setLayoutData(data);
		composite.setLayout(new FillLayout());

		textArea = TextAreaFactory.create(composite, inputType, lineType, defaultValue);
		if (textArea instanceof ListTextArea) {
			for (String choice : choices) { ((ListTextArea) textArea).addChoice(choice); }
		}

		return composite;
	}

	/** {@inheritDoc} */
	@Override
	public final void buttonPressed(int buttonId) {

		// Selon le type de retour
		int answerType;
		if (buttonId == OK) {
			answerType = IDialogAnswer.BUTTON_OK;
		} else {
			answerType = IDialogAnswer.BUTTON_CANCEL;
		}

		// Le contenu de la boite de dialogue a-t-elle ete modifiee ?
		boolean hasbeenmodified = false;
		if (inputType != IDialog.INPUT_FORBIDDEN) {
			hasbeenmodified = !textArea.getText().get(0).equals(defaultValue);
		}

		dialogAnswer = new DialogAnswer(id, answerType, hasbeenmodified, textArea.getText());

		this.close();
	}

	/** {@inheritDoc} */
	public final IDialogAnswer getDialogResult() {
		return dialogAnswer;
	}

	/** {@inheritDoc} */
	@Override
	protected final Image getImage() {
		switch (type) {
		case IDialog.DLG_ERROR:	return getErrorImage();
		case IDialog.DLG_WARNING: return getWarningImage();
		case IDialog.DLG_STANDARD: return getInfoImage();
		default: return getInfoImage();
		}
	}
}
