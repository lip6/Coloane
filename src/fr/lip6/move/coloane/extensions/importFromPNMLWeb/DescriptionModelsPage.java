package fr.lip6.move.coloane.extensions.importFromPNMLWeb;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import fr.lip6.move.pnmlweb.interfaces.IModelDescriptor;

/**
 * Import Wizard Page
 * Model descriptor description chosen
 * 
 * @author Yamina AZIZ
 * @author Monir CHAOUKI
 *
 */
public class DescriptionModelsPage extends WizardPage {

	private IModelDescriptor selected;
	private Label name;
	private Label description;
	private Label tag;
	
	/**
	 * Constructor
	 * @param pageName  Name of this page
	 */
	public DescriptionModelsPage(String pageName) {
		super(pageName);
		setTitle(pageName);
		setDescription("Clic to \"finish\" to import this model descriptor");
	}

	/**
	 * Create the graphic components of this wizard page
	 * @param parent  Composite of this wizard page
	 */
	public void createControl(Composite parent) {
		
		Composite myComposite = new Composite(parent, SWT.NONE);
		myComposite.setLayout(new FillLayout(SWT.VERTICAL));
		
		name = new Label (myComposite, SWT.NONE);
	    description = new Label (myComposite, SWT.NONE);
		tag= new Label (myComposite, SWT.NONE);
		    	      
	    setControl(myComposite);		
	}

	/**
	 * @see IWizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage() {
		// no next page for this path through the wizard
		return false;
	}
	
	/**
	 * Called when entering this wizard page.
	 */
	public void onEnterPage(){
	    // Gets the model
	    ImportWizard wizard = (ImportWizard)getWizard();
		SearchModel model = wizard.getModel();
		this.selected = model.selected;

		name.setText ("Name : " + selected.getName());
		name.redraw();
		description.setText ("Description : " + selected.getDescription());
		description.redraw();
		tag.setText ("Tags : " + selected.getTags());
		tag.redraw();
		
	}
	
	
}