package fr.lip6.move.coloane.core.ui.wizards;

import fr.lip6.move.coloane.core.extensions.ExportToExtension;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.ColoaneMessages;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.datatransfer.FileSystemExportWizard;

/**
 * Assistant (Wizard) generique d'export de fichier modele<br/>
 * Le format d'export est defini par l'extension qui appelle l'assistant
 *
 * @author Jean-Baptiste Voron
 */
public class ExportWizard extends FileSystemExportWizard implements IExecutableExtension {

	/** Le format d'export */
	private String idWizard = null;

	/** La seule et unique page du wizard */
	private ExportWizardPage page;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.wizards.datatransfer.FileSystemExportWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public final void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		this.page = new ExportWizardPage("ExportPage", currentSelection); //$NON-NLS-1$
		super.init(workbench, currentSelection);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	@Override
	public final boolean canFinish() {
		if ((this.idWizard != null) && (!page.getSelectedDirectory().equals("")) && (page.getSelectedRessource().size() > 0)) { //$NON-NLS-1$
			return super.canFinish();
		} else {
			page.setErrorMessage(ColoaneMessages.ExportWizard_2);
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.wizards.datatransfer.FileSystemExportWizard#addPages()
	 */
	@Override
	public final void addPages() {
		addPage(this.page);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.wizards.datatransfer.FileSystemExportWizard#performFinish()
	 */
	@Override
	public final boolean performFinish() {

		if (!page.ensureTargetIsValid(new File(page.getSelectedDirectory()))) {
			return false;
		}

		// Parcours de toutes les ressources selectionnees
		for (IResource res : page.getSelectedRessource()) {
			Coloane.getLogger().finer("Fichier a exporter : " + res.getName() + " vers " + page.getSelectedDirectory()); //$NON-NLS-1$ //$NON-NLS-2$

			// Cast de la ressource en IFile pour recuperer le contenu
			IFile file = (IFile) res;

			try {
				IModelImpl model = ModelLoader.loadFromXML(file);
				IExportTo exportInstance = ExportToExtension.createConvertInstance(this.idWizard);

				// Manipulation du nom de fichier pour supprimer l'ancienne extension et remplacer par la nouvelle
				String newExtension = ExportToExtension.findExtension(this.idWizard);
				String newName = file.getName().substring(0, file.getName().lastIndexOf('.') + 1) + newExtension;

				if (exportInstance == null) { return false;	}
				exportInstance.export(model, page.getSelectedDirectory() + "/" + newName); //$NON-NLS-1$
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Indique le format d'export utilise dans cette instance d'assistant
	 * @param exportFormat Le format a utiliser pour l'export
	 */
	protected final void setExportFormat(String idWizard) {
		Coloane.getLogger().finer("Wizard selectionne : " + idWizard); //$NON-NLS-1$
		this.idWizard = idWizard;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
	 */
	public final void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		// Recuperation de l'identitifant de l'appelant permettant ansi de determiner le format d'export
		this.setExportFormat(config.getAttribute("id")); //$NON-NLS-1$
	}
}
