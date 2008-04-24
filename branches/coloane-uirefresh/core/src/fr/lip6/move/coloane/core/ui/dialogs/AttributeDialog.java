package fr.lip6.move.coloane.core.ui.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IconAndMessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

public class AttributeDialog extends IconAndMessageDialog {

	// INPUTS
	private String title;
	private String help;
	private String byDefault;

	// OUTPUTS
	private String result = ""; //$NON-NLS-1$

	private Control textarea;

	public AttributeDialog(String t, String h, String d) {
		super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());

		this.title = t;
		this.help = h;
		this.byDefault = d;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	@Override
	protected final void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.AttributeDialog_0 + this.title);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected final void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, false);
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

		composite.getParent().setSize(400, 200);
		textarea =  new Text(composite, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);
		textarea.setToolTipText(this.help);
		((Text) textarea).setText(byDefault);

		return composite;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#buttonPressed(int)
	 */
	@Override
	public final void buttonPressed(int button) {
		//boolean answerType = (button == IDialogConstants.OK_ID)?true:false;

		this.result  = ((Text) textarea).getText();

		this.close();
	}


	/**
	 * Retourne le resultat
	 * @return
	 */
	public final String getResult() {
		return this.result;
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
