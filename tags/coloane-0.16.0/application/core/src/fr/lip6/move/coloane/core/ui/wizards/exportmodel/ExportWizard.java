package fr.lip6.move.coloane.core.ui.wizards.exportmodel;

import fr.lip6.move.coloane.core.extensions.ExportToExtension;
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
 * Assistant générique d'export de fichier modele<br/>
 * Le format d'export est defini par l'extension qui appelle l'assistant
 */
public class ExportWizard extends FileSystemExportWizard implements IExecutableExtension {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Le format d'export */
	private String idWizard = null;

	/** La seule et unique page du wizard */
	private ExportWizardPage page;

	/** {@inheritDoc} */
	@Override
	public final void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		IStructuredSelection select = null;
		if (currentSelection.getFirstElement() instanceof IFile) { select = currentSelection; }
		this.page = new ExportWizardPage("ExportPage", select); //$NON-NLS-1$
		super.init(workbench, currentSelection);
	}

	/** {@inheritDoc} */
	@Override
	public final boolean canFinish() {
		boolean canPerform = true;

		// On vérifie que chaque fichier peut être exporté
		for (IResource res : page.getSelectedRessource()) {
			IFormalism formalism = ModelLoader.loadFormalismFromXml((IFile) res);
			if (formalism == null || !ExportToExtension.canPerform(idWizard, formalism)) {
				canPerform = false;
				page.setErrorMessage(Messages.ExportWizard_0 + "'" + res.getName() + "'" + Messages.ExportWizard_3);  //$NON-NLS-1$//$NON-NLS-2$
			}
		}
		if (idWizard != null
				&& canPerform
				&& !page.getSelectedDirectory().equals("") //$NON-NLS-1$
				&& page.getSelectedRessource().size() > 0) {
			return super.canFinish();
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final void addPages() {
		addPage(this.page);
	}

	/** {@inheritDoc} */
	@Override
	public final boolean performFinish() {

		if (!page.ensureTargetIsValid(new File(page.getSelectedDirectory()))) {
			return false;
		}

		for (IResource res : page.getSelectedRessource()) {
			try {
				Job job = new ExportJob("Export " + res.getName(), //$NON-NLS-1$
						(IFile) res,
						ExportToExtension.createConvertInstance(this.idWizard),
						page.getSelectedDirectory(),
						ExportToExtension.findExtension(this.idWizard));

				job.setPriority(Job.LONG);
				job.setRule(res);
				job.setUser(true);
				job.schedule();
			} catch (CoreException ce) {
				LOGGER.warning("Erreur lors de l'initialisation de l'extension chargee de l'export: " + ce);  //$NON-NLS-1$
				return false;
			}
		}

		return true;
	}

	/**
	 * Indique le format d'export utilise dans cette instance d'assistant
	 * @param idWizard Le format a utiliser pour l'export
	 */
	protected final void setExportFormat(String idWizard) {
		LOGGER.finer("Wizard selectionne : " + idWizard); //$NON-NLS-1$
		this.idWizard = idWizard;
	}


	/** {@inheritDoc} */
	public final void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		// Recuperation de l'identitifant de l'appelant permettant ansi de determiner le format d'export
		this.setExportFormat(config.getAttribute("id")); //$NON-NLS-1$
	}
}
