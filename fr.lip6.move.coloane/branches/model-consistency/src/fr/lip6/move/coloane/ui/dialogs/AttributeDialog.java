package fr.lip6.move.coloane.ui.dialogs;

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
	private String title;
	private String help;
	
	protected Shell parentShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	
	public AttributeDialog(String title, String help) {
		super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		
		this.title = title;
	}
	
	@Override
	protected void configureShell(Shell shell) {
	    super.configureShell(shell);
	    shell.setText(this.title);
	 }
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.CANCEL_ID,IDialogConstants.CANCEL_LABEL, false);
		createButton(parent, IDialogConstants.OK_ID,IDialogConstants.OK_LABEL, false);
	}


	@Override
	protected Control createDialogArea(Composite parent) {
		createMessageArea(parent);

	    // Create a composite to hold the textArea
	    Composite composite = new Composite(parent, SWT.NONE);
	    GridData data = new GridData(GridData.FILL_BOTH);
	    data.horizontalSpan = 2;
	    composite.setLayoutData(data);
	    composite.setLayout(new FillLayout());
	    
	    composite.getParent().setSize(400, 200);
	    Control textarea =  new Text(composite, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);
	    textarea.setToolTipText(this.help);
	    
	    return composite;
	}
	
	@Override
	protected Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
