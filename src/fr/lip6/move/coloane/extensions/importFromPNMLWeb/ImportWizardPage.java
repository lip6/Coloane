package fr.lip6.move.coloane.extensions.importFromPNMLWeb;

import org.eclipse.jface.preference.FileFieldEditor;
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

import fr.lip6.move.pnmlweb.Caller;



public class ImportWizardPage extends WizardPage 
//implements Listener 
{
	
	public Text myText;
	
	//public String query;

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
	}
	
	/*
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		
	}	
	*/
	
	
	public IWizardPage getNextPage() {
		saveDataQuery();
		ModelsDescriptorPage page = ((ImportWizard)getWizard()).modelsDescriptorPage;
		page.onEnterPage();
		//this.query = myText.getText();
		return page;
	}
	
	/*
	public String getQuery() {
		return query;
	}
*/
	
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
		SearchModel model = wizard.model;
		
		model.query = myText.getText();
	}
	
	
	/*
	/**
	 * Applies the status to the status line of a dialog page.
	 */
	/*
	private void applyToStatusLine(IStatus status) {
		String message= status.getMessage();
		if (message.length() == 0) message= null;
		switch (status.getSeverity()) {
			case IStatus.OK:
				setErrorMessage(null);
				setMessage(message);
				break;
			case IStatus.WARNING:
				setErrorMessage(null);
				setMessage(message, WizardPage.WARNING);
				break;				
			case IStatus.INFO:
				setErrorMessage(null);
				setMessage(message, WizardPage.INFORMATION);
				break;			
			default:
				setErrorMessage(message);
				setMessage(null);
				break;		
		}
	}
	*/
	
}