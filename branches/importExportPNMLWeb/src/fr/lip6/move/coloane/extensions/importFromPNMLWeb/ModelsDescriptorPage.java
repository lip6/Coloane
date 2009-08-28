package fr.lip6.move.coloane.extensions.importFromPNMLWeb;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import fr.lip6.move.pnmlweb.Caller;
import fr.lip6.move.pnmlweb.exceptions.CallerException;
import fr.lip6.move.pnmlweb.exceptions.ModelDescriptorException;
import fr.lip6.move.pnmlweb.exceptions.ParserException;
import fr.lip6.move.pnmlweb.exceptions.PnmlWEBException;
import fr.lip6.move.pnmlweb.interfaces.IModelDescriptor;


public class ModelsDescriptorPage extends WizardPage {
	
	private List modelsDescriptorList;
	protected String query;
	private String[] models;
	
	private java.util.List<IModelDescriptor> listModels;
		
	protected ModelsDescriptorPage(String pageName) {
		super(pageName);
		setTitle(pageName);
		setDescription("Choose a model descriptor to import");
		// TODO Auto-generated constructor stub
	}

	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		int nbModel = 0;
		Caller c = new Caller("http://pnmlweb.lip6.fr", "admin", "admin1234");
		
		
		try {
			System.out.println("--------------" + query);
			listModels = c.searchModeldescriptors(query);//((ImportWizardPage)getWizard()).myText.getText());
			
			nbModel = listModels.size();
			models = new String[nbModel];
			for (int i = 0; i < nbModel; i++) {
				models[i] = listModels.get(i).getName();
			}
			
			Composite myComposite = new Composite(parent, SWT.NONE);
			myComposite.setLayout(new FillLayout(SWT.FILL));
			Label modelsDescriptorLabel = new Label(myComposite, SWT.LEFT);
			modelsDescriptorLabel.setText(listModels.size() + " models descriptors found.");
			modelsDescriptorList = new List (myComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		    modelsDescriptorList.setItems(models);
		    
		    setControl(myComposite);
			//setPageComplete(true);
		    
		} catch (PnmlWEBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ModelDescriptorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CallerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/////////////
			
			/*
			//java.util.List<IModelDescriptor> ms = c.searchModeldescriptors(((ImportWizardPage)getWizard()).myText.getText());
			//modelsDescriptorList = (List) ms;
			Composite myComposite = new Composite(parent, SWT.NONE);
			myComposite.setLayout(new FillLayout(SWT.VERTICAL));
			//Label label = new Label (myComposite, SWT.UP);
		    //label.setText ("Choose a model descriptor to import: ");
		    modelsDescriptorList = new List (myComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		    modelsDescriptorList.setItems (new String [] {"Item 1", "Item2"});
		      
		    setControl(myComposite);
			//setPageComplete(true);
		      */
		
	}

	
	public IWizardPage getNextPage() {    
		//saveDataSelected();
		DownloadModelsPage page = ((ImportWizard)getWizard()).downloadModelsPage;
		//page.onEnterPage();
		return page;
	}
	
	public boolean canFlipToNextPage() {
		// no next page for this path through the wizard
		if(listModels.size()==0)
			return false;
		return true;
	}
	
	public void onEnterPage()
	{
	    // Gets the model
	    ImportWizard wizard = (ImportWizard)getWizard();
		ModelsDescriptorPage model = wizard.model;
		System.out.println("**********"+model.query);
		query = model.query;
	}
	
	
	/*
	private void saveDataSelected(){
		ImportWizard wizard = (ImportWizard)getWizard();
		DownloadModelsPage model2 = wizard.model2;
		
		model2.selected = modelsDescriptorList.getSelection()[0];
	}
	*/
	
	/*
	public boolean isPageComplete() {
		//ImportWizard wizard = (ImportWizard)getWizard();
		return true;
	}
	*/
	
	
}



