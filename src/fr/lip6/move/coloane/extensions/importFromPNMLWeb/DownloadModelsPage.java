package fr.lip6.move.coloane.extensions.importFromPNMLWeb;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

public class DownloadModelsPage extends WizardPage {

	private List modelsDescriptorList;
	
	protected DownloadModelsPage(String pageName) {
		super(pageName);
		setTitle(pageName);
		setDescription("Do you really import this model ?");
		// TODO Auto-generated constructor stub
	}

	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		
		Composite myComposite = new Composite(parent, SWT.NONE);
		myComposite.setLayout(new FillLayout(SWT.VERTICAL));
		Label label = new Label (myComposite, SWT.UP);
	    label.setText ("Clic to finish to import this model: ");
	    //label.setText(+ );
	    
	      
	    setControl(myComposite);		
	}

	
	public boolean canFlipToNextPage() {
		// no next page for this path through the wizard
		return false;
	}
	
	
	
	
}
