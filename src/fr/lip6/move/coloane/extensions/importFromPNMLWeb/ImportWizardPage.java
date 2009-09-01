package fr.lip6.move.coloane.extensions.importFromPNMLWeb;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Import Wizard Page
 * Entry a query to search models descriptors on PNMLWeb
 * 
 * @author Yamina AZIZ
 *
 */
public class ImportWizardPage extends WizardPage {
	
	private Text myText;
	
	/**
	 * Constructor
	 * @param pageName  Name of page
	 */
	public ImportWizardPage(String pageName) {
		super(pageName);
		setTitle(pageName); //NON-NLS-1
		setDescription("Search of models descriptors"); //NON-NLS-1
	}

	/**
	 * Create the graphic components of this wizard page
	 * @param parent  Composite of this wizard page
	 */
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
		
	/**
	 * Gets the next page
	 * @return page  The next page
	 */
	public IWizardPage getNextPage() {
		saveDataQuery();
		ModelsDescriptorPage page = ((ImportWizard)getWizard()).getModelsDescriptorPage();
		page.onEnterPage();
		return page;
	}
	
	
	/**
	 * @see IWizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage() {
		//if (getErrorMessage() != null) return false;
			return true;
	}
	
	/**
	 * Save data of the query
	 */
	private void saveDataQuery(){
		ImportWizard wizard = (ImportWizard)getWizard();
		SearchModel model = wizard.getModel();		
		model.query = myText.getText();
	}
	
	/**
	 * Gets the text
	 * @return myText
	 */
	public Text getMyText(){
		return myText;
	}
	
		
	
}