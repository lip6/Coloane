package fr.lip6.move.coloane.extensions.importFromPNMLWeb;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

public class ImportWizard extends Wizard implements IImportWizard {
	
	public ImportWizardPage mainPage;
	public ModelsDescriptorPage modelsDescriptorPage;
	public DownloadModelsPage downloadModelsPage;
	
	public SearchModel model = new SearchModel();
	

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
		System.out.println("yes!");
		return true;
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

}