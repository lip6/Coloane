package fr.lip6.move.coloane.extensions.importFromPNMLWeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

import fr.lip6.move.pnmlweb.interfaces.IFormat;

public class ImportWizard extends Wizard implements IImportWizard {
	
	private ImportWizardPage mainPage;
	private ModelsDescriptorPage modelsDescriptorPage;
	private DownloadModelsPage downloadModelsPage;
	private SearchModel model = new SearchModel();

	public ImportWizard() {
		super();
	}

	
	public boolean canFinish() {
		if (this.getContainer().getCurrentPage() == downloadModelsPage) 
			return true;
		//System.out.println("yes !");
        return false;		
	}
	
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		IFormat f = model.selected.getCurrentVersion().getPNML();
		int cpt = 0;
		
		URL url;
		try {
			url = new URL(f.getUri());
			URLConnection uc = url.openConnection();	// open URL (HTTP query)

			InputStreamReader input = new InputStreamReader(uc.getInputStream());	// open data stream
			BufferedReader in = new BufferedReader(input);
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				cpt++;
			}

			in.close();
			System.out.println("yes, nbLine = " + cpt);
			MessageDialog.openInformation(getShell(), "Resultat", "Le nombre de lignes est de : " + cpt);
			return true;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageDialog.openError(getShell(), "Error", e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageDialog.openError(getShell(), "Error", e.getMessage());
		} 
		return false;
        
	}
	 
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("Pnml Import Wizard"); //NON-NLS-1
		mainPage = new ImportWizardPage("Import Pnml"); //NON-NLS-1
	}
	
	/* (non-Javadoc)
     * @see org.eclipse.jface.wizard.IWizard#addPages()
     */
    public void addPages() {
        addPage(mainPage);  
        modelsDescriptorPage = new ModelsDescriptorPage("Models Descriptor");
        addPage(modelsDescriptorPage);
        downloadModelsPage = new DownloadModelsPage("Download models descriptor");
        addPage(downloadModelsPage);
    }
    
    public ModelsDescriptorPage getModelsDescriptorPage(){
    	return modelsDescriptorPage;
    }
    
    public DownloadModelsPage getDownloadModelsPage(){
    	return downloadModelsPage;
    }
    
    
    public SearchModel getModel(){
    	return model;
    }
    
    
}