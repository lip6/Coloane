package fr.lip6.move.coloane.extensions.importFromPNMLWeb;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;

import fr.lip6.move.pnmlweb.Caller;
import fr.lip6.move.pnmlweb.exceptions.CallerException;
import fr.lip6.move.pnmlweb.exceptions.ModelDescriptorException;
import fr.lip6.move.pnmlweb.exceptions.ParserException;
import fr.lip6.move.pnmlweb.exceptions.PnmlWEBException;
import fr.lip6.move.pnmlweb.interfaces.IModelDescriptor;


public class ModelsDescriptorPage extends WizardPage {
	
	private List modelsDescriptorList;
	private Label modelsDescriptorLabel;
	private String query;
	private String[] models;
	
	private java.util.List<IModelDescriptor> listModels;
		
	protected ModelsDescriptorPage(String pageName) {
		super(pageName);
		setTitle(pageName);
		setDescription("Choose a model descriptor to import");
		// TODO Auto-generated constructor stub
	}

	public void createControl(Composite parent) {		
		
		Composite myComposite = new Composite(parent, SWT.NONE);
		myComposite.setLayout(new FillLayout(SWT.VERTICAL));
		modelsDescriptorLabel = new Label(myComposite, SWT.LEFT);
		modelsDescriptorList = new List (myComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		
		setControl(myComposite);
		setPageComplete(true);
	}

	
	public IWizardPage getNextPage() {    
		//saveDataSelected();
		for(int i=0; i<listModels.size();i++){
			if(modelsDescriptorList.isSelected(i)){
				DownloadModelsPage page = ((ImportWizard)getWizard()).getDownloadModelsPage();
				//page.onEnterPage2();
				return page;
			}
		}
		return null;
	}
	
	public boolean canFlipToNextPage() {
		// no next page for this path through the wizard
		if(listModels.size()==0)
			return false;
		return true;
	}		
	
	public void onEnterPage() {
		
		ImportWizard wizard = (ImportWizard)getWizard();
		SearchModel model = wizard.getModel();
		this.query = model.query;
				
		int nbModel = 0;
		Caller c = new Caller("http://pnmlweb.lip6.fr", "admin", "admin1234");
		
		try {
			listModels = c.searchModeldescriptors(query);//((ImportWizardPage)getWizard()).myText.getText());
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
		
		nbModel = listModels.size();
		models = new String[nbModel];
		for (int i = 0; i < nbModel; i++) {
			models[i] = listModels.get(i).getName();
		}
		
		modelsDescriptorLabel.setText(listModels.size() + " models descriptors found.");
		modelsDescriptorLabel.redraw();
		
		modelsDescriptorList.setItems(models);
		modelsDescriptorList.redraw();
				
	}

	
	/*
	public boolean isPageComplete(){
		//ImportWizard wizard = (ImportWizard)getWizard();
		if (modelsDescriptorList.getSelectionCount() == 0) { 
			return false;
		}
		saveDataSelected();
		return true;
	}
	*/
	
	/*
	private void saveDataSelected(){
		ImportWizard wizard = (ImportWizard)getWizard();
		DownloadModelsPage model2 = wizard.getModel2();
		
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



