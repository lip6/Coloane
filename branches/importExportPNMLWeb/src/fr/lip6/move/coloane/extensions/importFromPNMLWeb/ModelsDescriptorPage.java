package fr.lip6.move.coloane.extensions.importFromPNMLWeb;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
		modelsDescriptorList.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) { return; }
			public void widgetSelected(SelectionEvent e) { handleEvent(); }
		});
				
		setControl(myComposite);
	}

	
	public IWizardPage getNextPage() {    
		saveDataSelected();
		DownloadModelsPage page = ((ImportWizard)getWizard()).getDownloadModelsPage();
		page.onEnterPage();
		return page;
	}
	
	public boolean canFlipToNextPage() {
		// no next page for this path through the wizard
		if(modelsDescriptorList.getSelectionCount() == 0)
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
			MessageDialog.openError(getShell(), "Error", e.getMessage());
		} catch (ModelDescriptorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageDialog.openError(getShell(), "Error", e.getMessage());
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageDialog.openError(getShell(), "Error", e.getMessage());
		} catch (CallerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageDialog.openError(getShell(), "Error", e.getMessage());
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
	
	/**
	 *  Mettre à jour le formalisme choisi
	 */
	public void handleEvent() {
		setPageComplete(isPageComplete());
		getWizard().getContainer().updateButtons();
	}
		
	private void saveDataSelected(){
		ImportWizard wizard = (ImportWizard)getWizard();
		SearchModel model = wizard.getModel();
		model.selected = listModels.get(modelsDescriptorList.getSelectionIndex());
		
	}
	
	
}


