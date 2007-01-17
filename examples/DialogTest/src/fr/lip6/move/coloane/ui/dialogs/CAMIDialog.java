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

/*
 * A CAMIDialog is divided in four zones :
 * 1. the icon's zone, indicating if it's a warning, an error, ...
 * 2. the message zone, which is a non-editable text zone
 * 3. the text area which can be a text input (multi or single line)
 *  or a list of choices (single or multi selection)
 * 4. a buttons' zone
 */

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
	protected int inputType;
	protected int multiLine;
	protected String defaultValue;
	
	protected Shell parentShell =
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	
	protected TextArea textArea = null;
	
	public CAMIDialog(int id, int buttonType,
			String title, String help, String message,
			int inputType, int multiLine, String defaultValue) {
		
		super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		
		this.id = id;
		this.buttonType = buttonType;
		this.title = title;
		this.help = help;
		this.inputType = inputType;
		this.multiLine = multiLine;
		this.defaultValue = defaultValue;
	}
	
	@Override
	protected Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Defines which buttons will be displayed.<br/>
	 * This method belongs to the org.eclipse.jfacee.dialogs.Dialog class.<br/>
	 * For more details, please read the Dialog class' documentation for
	 * more details.
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		
		switch (buttonType) {
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
   * Creates the dialog area.<br/>
   * If the dialog does not permit input and is single-line,
   * there is nothing to do.
   * 
   * Otherwise, fills this area with a TextArea object obtained
   * from a TextAreaFactory.
   * 
   * @param parent the parent composite
   * @return Control
   */
  protected Control createDialogArea(Composite parent) {
    createMessageArea(parent);
    
    if (inputType == TextArea.INPUT_FORBIDDEN &&
    		multiLine == TextArea.SINGLE_LINE)
    	return null;
    
    System.out.println("We are heeeeeree :D");
    
    // Create a composite to hold the textArea
    Composite composite = new Composite(parent, SWT.NONE);
    GridData data = new GridData(GridData.FILL_BOTH);
    data.horizontalSpan = 2;
    composite.setLayoutData(data);
    composite.setLayout(new FillLayout());
    
    new Label(parent, SWT.LEFT);
    
    
    return composite;
  }
}
