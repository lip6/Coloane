package fr.lip6.move.coloane.core.ui.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.core.extensions.ImportFromExtension;
import fr.lip6.move.coloane.core.main.Coloane;

public class ImportWizard extends Wizard implements IImportWizard, IExecutableExtension {
	
	/** Identifiant de l'assistant (wizard) **/
	private String idWizard = null;
	
	/** La page du wizard chargee d'afficher les controles graphiques **/
	private ImportWizardPage selectFilePage;
	
	/** L'instance de conversion a utiliser */
	private IImportFrom instance;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("Toto");
		selectFilePage = new ImportWizardPage(workbench,selection,instance);
	}
	
	@Override
	public void addPages() {
		addPage(selectFilePage);
		super.addPages();
	}
	
	@Override
	public boolean canFinish() {
		if (this.idWizard == null) {
			selectFilePage.setErrorMessage("The extension you are using to import your files is broken. Please contact the dev team");
			return false;
		}
		return super.canFinish();
	}
	
	@Override
	public boolean performFinish() {
		try {
			selectFilePage.finish();
			return true;
		} catch (ColoaneException e) {
			Coloane.getLogger().warning("Echec de l'import : "+e.getMessage());
			Coloane.showErrorMsg("An error has occured. Your file has not been imported.");
			return false;
		}
	}

	/**
	 * Indique le format d'export utilise dans cette instance d'assistant
	 * @param exportFormat Le format a utiliser pour l'export
	 */
	protected void setImportFormat(String idWizard) {
		this.idWizard = idWizard;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
	 */
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		this.idWizard = config.getAttribute("id"); //$NON-NLS-1$
		Coloane.getLogger().finer("Wizard selectionne : "+idWizard); //$NON-NLS-1$

		this.instance = (IImportFrom) ImportFromExtension.createConvertInstance(this.idWizard);
				
		if (this.instance == null) {
			Coloane.getLogger().warning("Erreur lors de la creation de l'instance de conversion");
		}
	}
}
