package fr.lip6.move.coloane.core.ui.wizards.exportmodel;

import fr.lip6.move.coloane.core.extensions.ExportToExtension;
import fr.lip6.move.coloane.core.ui.files.FormalismHandler;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import java.io.File;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.datatransfer.FileSystemExportWizard;

/**
 * Generic wizard dedicated to exporting models.<br/>
 * The wizard ID is used in order to find corresponding export extensions.
 */
public class ExportWizard extends FileSystemExportWizard implements IExecutableExtension {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The wizard used for the export... Some export extensions are attached to this wizard */
	private String exportType = null;

	/** The unique wizard page */
	private ExportWizardPage page;

	/** {@inheritDoc} */
	@Override
	public final void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		IStructuredSelection select = null;
		// Check if some files are currently selected
		if (currentSelection.getFirstElement() instanceof IFile) { select = currentSelection; }
		this.page = new ExportWizardPage("ExportPage", select); //$NON-NLS-1$
		super.init(workbench, currentSelection);
	}

	/** {@inheritDoc} */
	@Override
	public final void addPages() {
		addPage(this.page);
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canFinish() {
		if (exportType == null) {
			page.setErrorMessage(Messages.ExportWizard_1);
			return false;
		}
		
		// Check that every files can be exported
		for (IResource res : page.getSelectedRessource()) {
			// TODO: Use persistent attributes
			IFormalism currentFormalism = ModelLoader.loadFromXML(((IFile) res), new FormalismHandler()).getFormalism();
			// Check that it exists an export extension for this wizard, and this formalism
			if (currentFormalism == null || !ExportToExtension.canPerform(exportType, currentFormalism)) {
				page.setErrorMessage(Messages.ExportWizard_0 + "'" + res.getName() + "'" + Messages.ExportWizard_3);  //$NON-NLS-1$//$NON-NLS-2$
				return false;
			}
		}
		
		// Check the directory
		if (page.getSelectedDirectory().isEmpty()) {
			page.setErrorMessage(Messages.ExportWizard_2);
			return false;
		}
		
		// Check the number of resources to export
		if (page.getSelectedRessource().size() > 0) {
			page.setErrorMessage(Messages.ExportWizard_4);
			return false;
		}
		
		return super.canFinish();
	}

	/** {@inheritDoc} */
	@Override
	public final boolean performFinish() {

		// Check the target directory
		if (!page.ensureTargetIsValid(new File(page.getSelectedDirectory()))) {
			return false;
		}

		// For all selected resources
		for (IResource res : page.getSelectedRessource()) {
			// Export the model
			try {
				Job job = new ExportJob("Export " + res.getName(), //$NON-NLS-1$
						(IFile) res,
						ExportToExtension.createConvertInstance(this.exportType),
						page.getSelectedDirectory(),
						ExportToExtension.findExtension(this.exportType));

				job.setPriority(Job.LONG);
				job.setRule(res);
				job.setUser(true);
				job.schedule();
			} catch (CoreException ce) {
				LOGGER.warning("Unable to load export extension: " + ce);  //$NON-NLS-1$
				return false;
			}
		}

		return true;
	}

	/**
	 * Set the export type... This type is given by the wizard.
	 * Each export extension defines the wizard (thanks to the wizard id) that it contributes to.
	 * @param idWizard the wizard id that will be used to find corresponding export extension
	 */
	protected final void setExportFormat(String idWizard) {
		LOGGER.finer("Selected wizard: " + idWizard); //$NON-NLS-1$
		this.exportType = idWizard;
	}


	/** {@inheritDoc} */
	public final void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		// Fetch the identifier of the wizard, in order to find the corresponding export extension.
		this.setExportFormat(config.getAttribute("id")); //$NON-NLS-1$
	}
}
