package fr.lip6.move.coloane.core.ui.dialogs;

import fr.lip6.move.coloane.interfaces.IDialogResult;
import fr.lip6.move.coloane.interfaces.objects.IDialogCom;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

public class FileBrowserDialog extends Dialog implements IDialog {

	private int id;

	private int buttonType;

	// private int inputType;
	// private int multiLine;
	private String defaultValue;

	private IDialogResult dialogResult;

	// private ArrayList<String> choices = null;

	private Text fileField = null;

	private Shell shell = null;

	/** Id du bouton Browse */
	private static final int BROWSE_ID = IDialogConstants.CLIENT_ID;


	 private static Shell parentShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	/**
	 * Constructeur de la boite de dialogue
	 * @param dialog La boite de dialogue construite par la com
	 */
	public FileBrowserDialog(IDialogCom dialog) {

		super(parentShell);

		this.id = dialog.getId();
		this.buttonType = dialog.getButtonType();
		//this.inputType = dialog.getInputType();
		//this.multiLine = dialog.getMultiLine();
		this.defaultValue = dialog.getDefault();

		/*
		 * There is a kind of dialog which does not contain any "OK" or "Cancel"
		 * button. For this kind of dialog, we create a default result. For the
		 * other dialog, this result will be overwritten by a result created
		 * when the user will clic on "OK" or "Cancel".
		 */
		dialogResult = new DialogResult(id, IDialog.TERMINATED_OK, false,
				new ArrayList<String>());

		// choices = new ArrayList<String>();
	}

	/**
	 * Determine quels seront les boutons affiches
	 * @param parent
	 *            La fenetre en cours de construction
	 */
	protected final void createButtonsForButtonBar(Composite parent) {
		switch (buttonType) {
		case IDialogCom.DLG_OK:
			createButton(parent, IDialogConstants.OK_ID,
					IDialogConstants.OK_LABEL, true);
			break;
		case IDialogCom.DLG_OK_CANCEL:
			createButton(parent, IDialogConstants.CANCEL_ID,
					IDialogConstants.CANCEL_LABEL, false);
			createButton(parent, IDialogConstants.OK_ID,
					IDialogConstants.OK_LABEL, true);
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

		/* Composite composite = new Composite(parent, SWT.NONE); */
		shell = this.getShell();
		shell.setText("File browser");
		Composite composite = (Composite) super.createDialogArea(parent);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		composite.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		composite.setLayoutData(gridData);

		super.createButton(parent, BROWSE_ID, "Browse", true);

		new Label(composite, SWT.NULL).setText("File:");
		fileField = new Text(composite, SWT.BORDER);
		gridData = new GridData();
		gridData.widthHint = 200;
		fileField.setLayoutData(gridData);

		return composite;
	}

	/**
	 * Action a effectuer lorsqu'un boutton est appuye
	 * @param buttonId Identifiant du button
	 */
	@Override
	public final void buttonPressed(int buttonId) {
		int answerType;
		/* Selon le type de retour */
		if (buttonId == BROWSE_ID) {
			String fileName = new FileDialog(shell).open();
			if (fileName != null) {
				fileField.setText(fileName);
			}
		}
		if (buttonId == OK) {
			answerType = TERMINATED_OK;
		} else {
			answerType = TERMINATED_CANCEL;
		}

		dialogResult = new DialogResult(id, answerType, !fileField.getText()
				.equals(defaultValue), pathToArrayList(fileField.getText()));

		this.close();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.dialogs.IDialog#addChoice(java.lang.String)
	 */
	public final void addChoice(String choice) {
		return;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.dialogs.IDialog#getDialogResult()
	 */
	public final IDialogResult getDialogResult() {
		return dialogResult;
	}

	/**
	 * Copie le contenu d'un fichier en ArrayList<String>
	 * @param path Chemin complet du fichier
	 * @return ArrayList<String> result
	 */
	public final ArrayList<String> pathToArrayList(String path) {
		ArrayList<String> result = new ArrayList<String>();

		try {
			if (path != null) {
				InputStream ips = new FileInputStream(path);
				InputStreamReader ipsr = new InputStreamReader(ips);
				BufferedReader br = new BufferedReader(ipsr);
				String line;
				while ((line = br.readLine()) != null) {
					result.add(line);
				}
				br.close();
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return result;
	}
}
