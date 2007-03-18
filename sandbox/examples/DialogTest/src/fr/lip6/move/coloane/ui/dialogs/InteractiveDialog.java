package fr.lip6.move.coloane.ui.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;


public class InteractiveDialog extends Dialog
	implements IDialog {
	
	public static final int NORMAL = 1;
	public static final int WARNING = 2;
	public static final int ERROR = 3;
	
	protected Shell parentShell =
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	protected StyledText textArea;
	
	public InteractiveDialog(int id, int buttonType,
			String title, String help, String message,
			int inputType, int multiLine, String defaultValue) {
		super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
	}
	
	
	

	/*
	 * By default, a Dialog object is displayed with two buttons
	 * (OK and Cancel).
	 * We just override this method to obtain a dialog
	 * without button.
	 */
	protected  Control 	createButtonBar(Composite parent) {
		return parent;
	}
	
	protected Control createContents(Composite parent) {
		parent.setLayout(new FillLayout());
		parent.setSize(500, 500);
		textArea = new StyledText(parent, SWT.BORDER);
		
		return parent;
	}
	
	public DialogResult getDialogResult() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * This method adds a choice in a list (in the case of 
	 * a simple dialog with a list), so it has no sense here.
	 */
	public void addChoice(String choice) {
		throw new UnsupportedOperationException();
	}
}
