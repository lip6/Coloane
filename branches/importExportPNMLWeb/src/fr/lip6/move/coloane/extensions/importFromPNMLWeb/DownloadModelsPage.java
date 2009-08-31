package fr.lip6.move.coloane.extensions.importFromPNMLWeb;


import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import fr.lip6.move.pnmlweb.interfaces.IModelDescriptor;

public class DownloadModelsPage extends WizardPage {

	private IModelDescriptor selected;
	private Label name;
	private Label description;
	private Label tag;
	
	protected DownloadModelsPage(String pageName) {
		super(pageName);
		setTitle(pageName);
		setDescription("Clic to \"finish\" to import this model descriptor");
		// TODO Auto-generated constructor stub
	}

	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		
		Composite myComposite = new Composite(parent, SWT.NONE);
		myComposite.setLayout(new FillLayout(SWT.VERTICAL));
		
		name = new Label (myComposite, SWT.NONE);
	    description = new Label (myComposite, SWT.NONE);
		tag= new Label (myComposite, SWT.NONE);
		    	      
	    setControl(myComposite);		
	}

	
	public boolean canFlipToNextPage() {
		// no next page for this path through the wizard
		return false;
	}
	
	
	public void onEnterPage(){
	    // Gets the model
	    ImportWizard wizard = (ImportWizard)getWizard();
		SearchModel model = wizard.getModel();
		System.out.println("**********"+model.selected);
		this.selected = model.selected;

		name.setText ("Name : " + selected.getName());
		name.redraw();
		description.setText ("Description : " + selected.getDescription());
		description.redraw();
		tag.setText ("Tags : " + selected.getTags());
		tag.redraw();
		
	}
	
	
}