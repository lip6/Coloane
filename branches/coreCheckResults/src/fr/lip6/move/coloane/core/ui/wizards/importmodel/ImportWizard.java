package fr.lip6.move.coloane.core.ui.wizards.importmodel;

import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.core.extensions.ImportFromExtension;
import fr.lip6.move.coloane.core.ui.wizards.importmodel.Messages;

import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Wizard dedicated to importing files (transform them into models).
 * 
 * @author Jean-Baptiste Voron
 */
public class ImportWizard extends Wizard implements IImportWizard, IExecutableExtension {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Wizard ID which is used to find the correspond import worker **/
	private String importType = null;

	/** The wizard page **/
	private ImportWizardPage page;

	/** The import worker */
	private IImportFrom worker;

	/** {@inheritDoc} */
	public final void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle(Messages.ImportWizard_0);
		page = new ImportWizardPage(workbench, selection, this.worker, this.importType);
		setNeedsProgressMonitor(true);
	}

	/** {@inheritDoc} */
	@Override
	public final void addPages() {
		addPage(page);
		super.addPages();
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canFinish() {
		if (this.importType == null) {
			page.setErrorMessage(Messages.ImportWizard_1);
			return false;
		}
		return super.canFinish();
	}

	/** {@inheritDoc} */
	@Override
	public final boolean performFinish() {
		return page.finish();
	}

	/**
	 * Set the kind of import that is performed.
	 * Build up the import worker.
	 * @param idWizard The wizard id used by as handle by the extension points
	 */
	protected final void setImportFormat(String idWizard) {
		LOGGER.finer("Selected wizard: " + idWizard); //$NON-NLS-1$
		this.importType = idWizard;
		
		try {
			// Set up the import builder
			this.worker = (IImportFrom) ImportFromExtension.createConvertInstance(this.importType);
			LOGGER.finer("Set up the import extension"); //$NON-NLS-1$
		} catch (CoreException ce) {
			LOGGER.warning("Unable to load the import extension: " + ce);  //$NON-NLS-1$
			page.setErrorMessage(Messages.ImportWizard_1);
		}
	}

	/** {@inheritDoc} */
	public final void setInitializationData(IConfigurationElement config, String propertyName, Object data) {
		// Fetch the identifier of the wizard, in order to find the corresponding export extension.
		this.setImportFormat(config.getAttribute("id")); //$NON-NLS-1$
	}
}
