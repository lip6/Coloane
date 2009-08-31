package fr.lip6.move.coloane.extensions.importFromPNMLWeb;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;


public class ImportWizardPage extends WizardPage 
//implements Listener 
{
	
	private Text myText;
	
	
	public ImportWizardPage(String pageName) {
		super(pageName);
		setTitle(pageName); //NON-NLS-1
		setDescription("Search of models descriptors"); //NON-NLS-1
	}

	public void createControl(Composite parent) {
		  // create the composite to hold the widgets
		Composite myComposite =  new Composite(parent, SWT.NONE);
		myComposite.setLayout(new GridLayout());
		Label myLabel = new Label(myComposite, SWT.LEFT);
		myLabel.setText("Query: ");
		myText = new Text(myComposite, SWT.BORDER);
		myText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//myText.addListener(SWT.KeyUp, this);
		// set the composite as the control for this page
		setControl(myComposite);
		//addListeners();
	}
	
	
	
	public IWizardPage getNextPage() {
		saveDataQuery();
		//if(isTextNonEmpty(myText)){
			ModelsDescriptorPage page = ((ImportWizard)getWizard()).getModelsDescriptorPage();
			page.onEnterPage();
			//this.query = myText.getText();
			return page;
		//}
		//return null;
	}
	
	
	/**
	 * @see IWizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage() {
		//if (getErrorMessage() != null) return false;
		/*
		if (isTextNonEmpty(myText))
			return true;
		return false;
		*/
		return true;
	}
	
	/*
	private static boolean isTextNonEmpty(Text t) {
		String s = t.getText();
		if ((s!=null) && (s.trim().length() >0)) return true;
		return false;
	}
*/
	
	private void saveDataQuery(){
		ImportWizard wizard = (ImportWizard)getWizard();
		SearchModel model = wizard.getModel();		
		model.query = myText.getText();
	}
	
	public Text getMyText(){
		return myText;
	}
	
	/*
	private void addListeners() {
		myText.addListener(SWT.KeyUp, this);
	}
	*/

	/*
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		 // Initialize a variable with the no error status
	    Status status = new Status(IStatus.OK, "not_used", 0, "", null);
	    // If the event is triggered by the destination or departure fields
	    // set the corresponding status variable to the right value
	    if ((event.widget == myText)) {
	        if (!"".equals(myText.getText()))
	            status = new Status(IStatus.ERROR, "not_used", 0, 
	                "Le champ \"query\" ne peut etre vide", null);        		
	    }
	    getWizard().getContainer().updateButtons();
	}
	*/
	
	
}