package fr.lip6.move.coloane.ui.dialogs;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.Dialog;
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

/*
 * A CAMIDialog is divided in four zones :
 * 1. the icon's zone, indicating if it's a warning, an error, ...
 * 2. the message zone, which is a non-editable text zone
 * 3. the text area which can be a text input (multi or single line)
 *  or a list of choices (single or multi selection)
 * 4. a buttons' zone
 */

public class SimpleDialog extends IconAndMessageDialog
	implements IDialog {
	protected int id;
	protected int type;
	protected int buttonType;
	protected String title;
	protected String help;
	protected int inputType;
	protected int multiLine;
	protected String defaultValue;
	protected DialogResult dialogResult;
	protected ArrayList<String> choices = null;
	protected Image image;
	
	protected Shell parentShell =
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	
	protected TextArea textArea = null;
	
	public SimpleDialog(int id, int type, int buttonType,
			String title, String help, String message,
			int inputType, int multiLine, String defaultValue) {
		
		super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		
		this.id = id;
		this.type = type;
		this.buttonType = buttonType;
		this.title = title;
		this.help = help;
		this.message = message;
		this.inputType = inputType;
		this.multiLine = multiLine;
		this.defaultValue = defaultValue;
		
		/*
		 * There is a kind of dialog which does not contain any "OK" or "Cancel" button.
		 * For this kind of dialog, we create a default result.
		 * For the other dialog, this result will be overwritten by a result created
		 * when the user will clic on "OK" or "Cancel".
		 */
		dialogResult = new DialogResult(id, IDialog.TERMINATED_OK, false, new ArrayList<String>());
		
		choices = new ArrayList<String>();
		
		System.err.println(Dialog.DLG_IMG_MESSAGE_ERROR);
		
		for (Image image : Dialog.getDefaultImages()) {
			System.err.println(image.toString());
		}
		
		image = null;
		//image = new Image(parentShell.getDisplay(), Dialog.DLG_IMG_MESSAGE_WARNING);
	}
	
	protected void configureShell(Shell shell) {
    super.configureShell(shell);
    shell.setText(this.title);
 }
	
	@Override
	protected Image getImage() {
		return image;
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

    // Create a composite to hold the textArea
    Composite composite = new Composite(parent, SWT.NONE);
    GridData data = new GridData(GridData.FILL_BOTH);
    data.horizontalSpan = 2;
    composite.setLayoutData(data);
    composite.setLayout(new FillLayout());
    
    textArea = TextAreaFactory.create(composite,
    		inputType, multiLine, defaultValue);
    
    if (textArea instanceof ListTextArea)
			for (String choice : choices)
				textArea.addChoice(choice);
    
    textArea.setToolTiptext(this.help);
    
    return composite;
  }
  
  public void buttonPressed(int buttonId) {
  	int answerType =
  		(buttonId == OK ? TERMINATED_OK : TERMINATED_CANCEL);
  	
  	dialogResult = new DialogResult(id, answerType,
	  			!textArea.getText().get(0).equals(defaultValue),
	  			textArea.getText());
	  
  	this.close();
  }

	public DialogResult getDialogResult() {
		return dialogResult;
	}

	
	
	/**
	 * Adds a choice in a list
	 */
	public void addChoice(String choice) {
		choices.add(choice);
		
		if (textArea != null &&
				textArea instanceof ListTextArea)
			textArea.addChoice(choice);
	}
}
