package fr.lip6.move.coloane.extensions.importexportpnmlweb.importWizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


public class ModelsDescriptorPage extends WizardPage {

	protected ModelsDescriptorPage(String pageName) {
		super(pageName);
		setTitle(pageName);
		setDescription("Choose a model descriptor to import");
		// TODO Auto-generated constructor stub
	}

	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		
		Composite myComposite = new Composite(parent, SWT.NONE);
		Label myLabel = new Label(myComposite, SWT.LEFT);
		myLabel.setText("page2");
		setControl(myComposite);
		//setPageComplete(true);
		
	}

	
	public boolean canFlipToNextPage() {
		// no next page for this path through the wizard
		return false;
	}
	
	/*
	public boolean isPageComplete() {
		//ImportWizard wizard = (ImportWizard)getWizard();
		return true;
	}
	*/
	
}
