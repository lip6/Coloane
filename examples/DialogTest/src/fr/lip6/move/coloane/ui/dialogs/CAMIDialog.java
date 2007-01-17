package fr.lip6.move.coloane.ui.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IconAndMessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public abstract class CAMIDialog extends IconAndMessageDialog {
	
	public static final int DLG_STANDARD = 1;
	public static final int DLG_WARNING = 2;
	public static final int DLG_ERROR = 3;
	public static final int DLG_INTERACTIVE = 4;
	
	public static final int DLG_NO_BUTTON = 1;
	public static final int DLG_OK = 2;
	public static final int DLG_OK_CANCEL = 3;
	
	protected int id;
	protected int buttonType;
	protected String title;
	protected String help;
	protected boolean inputAuthorized;
	protected boolean multiLine;
	protected String defaultValue;
	
	protected Shell parentShell =
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	
	private Label labelMessage;
	
	public CAMIDialog(int id, int buttonType,
			String title, String help, String message,
			boolean inputAuthorized, boolean multiLine, String defaultValue)
	{
		super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		
		this.id = id;
		this.buttonType = buttonType;
		this.title = title;
		this.help = help;
		this.inputAuthorized = inputAuthorized;
		this.multiLine = multiLine;
		this.defaultValue = defaultValue;
	}
	
	@Override
	protected Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		switch (buttonType) {
		case DLG_NO_BUTTON:
			break;
		case DLG_OK:
			createButton(parent, IDialogConstants.OK_ID,
					IDialogConstants.OK_LABEL, false);
			break;
		case DLG_OK_CANCEL:
			createButton(parent, IDialogConstants.CANCEL_ID,
					IDialogConstants.CANCEL_LABEL, false);
			createButton(parent, IDialogConstants.OK_ID,
					IDialogConstants.OK_LABEL, false);
			break;
		}
  }
	
	/**
   * Creates the dialog area
   * 
   * @param parent the parent composite
   * @return Control
   */
  protected Control createDialogArea(Composite parent) {
    createMessageArea(parent);

    // Create a composite to hold the label
    Composite composite = new Composite(parent, SWT.NONE);
    GridData data = new GridData(GridData.FILL_BOTH);
    data.horizontalSpan = 2;
    composite.setLayoutData(data);
    composite.setLayout(new FillLayout());
    
    labelMessage = new Label(composite, SWT.LEFT);

    return composite;
  }
}
