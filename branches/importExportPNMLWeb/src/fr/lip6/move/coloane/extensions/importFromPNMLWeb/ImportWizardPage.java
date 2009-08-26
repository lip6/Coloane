package fr.lip6.move.coloane.extensions.importFromPNMLWeb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;



public class ImportWizardPage extends WizardPage {
	
	protected FileFieldEditor editor;
	private Text myText;

	public ImportWizardPage(String pageName) {
		super(pageName);
		setTitle(pageName); //NON-NLS-1
		setDescription("Search of models descriptors"); //NON-NLS-1
	}

	 /* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#createLinkTarget()
	 */
	protected void createLinkTarget() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#getInitialContents()
	 */
	protected InputStream getInitialContents() {
		try {
			return new FileInputStream(new File(editor.getStringValue()));
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#validateLinkedResource()
	 */
	protected IStatus validateLinkedResource() {
		return new Status(IStatus.OK, "fr.lip6.move.coloane.extensions.importExportPNMLWeb", IStatus.OK, "", null); //NON-NLS-1 //NON-NLS-2
	}

	public void createControl(Composite parent) {
		  // create the composite to hold the widgets
		Composite myComposite =  new Composite(parent, SWT.NONE);
		myComposite.setLayout(new GridLayout());
		Label myLabel = new Label(myComposite, SWT.LEFT);
		myLabel.setText("Query: ");
		myText = new Text(myComposite, SWT.BORDER);
		myText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		// set the composite as the control for this page
		setControl(myComposite);
	}
	
	
	public IWizardPage getNextPage() {    		
		ModelsDescriptorPage page = ((ImportWizard)getWizard()).modelsDescriptorPage;
		return page;
	}

	/**
	 * @see IWizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage() {
		return true;
	}
	
	
}