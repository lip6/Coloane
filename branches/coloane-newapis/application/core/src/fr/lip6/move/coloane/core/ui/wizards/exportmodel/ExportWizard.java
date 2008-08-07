package fr.lip6.move.coloane.core.ui.wizards.exportmodel;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.ExportToExtension;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.File;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
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
		if ((this.idWizard != null) && (!page.getSelectedDirectory().equals("")) && (page.getSelectedRessource().size() > 0)) { //$NON-NLS-1$
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

		// Parcours de toutes les ressources selectionnees
		for (IResource res : page.getSelectedRessource()) {
			LOGGER.finer("Fichier a exporter : " + res.getName() + " vers " + page.getSelectedDirectory()); //$NON-NLS-1$ //$NON-NLS-2$

			// Cast de la ressource en IFile pour recuperer le contenu
			IFile file = (IFile) res;

			try {
				IGraph model = ModelLoader.loadFromXML(file);
				IExportTo exportInstance = ExportToExtension.createConvertInstance(this.idWizard);
				LOGGER.fine("L'assistant d'export a ete trouve et instancie... Calcul du nom final"); //$NON-NLS-1$

				// Manipulation du nom de fichier pour supprimer l'ancienne extension et remplacer par la nouvelle
				String newExtension = ExportToExtension.findExtension(this.idWizard);
				String newName = file.getName().substring(0, file.getName().lastIndexOf('.') + 1) + newExtension;
				LOGGER.finer("Nom final : " + newName); //$NON-NLS-1$

				if (exportInstance == null) { return false;	}
				exportInstance.export(model, page.getSelectedDirectory() + "/" + newName); //$NON-NLS-1$
			} catch (ColoaneException e) {
				LOGGER.warning("Erreur lors de l'export du fichier...");  //$NON-NLS-1$
				LOGGER.warning("Echec de l'export..." + e.getMessage()); //$NON-NLS-1$
				return false;
			} catch (CoreException ce) {
				LOGGER.warning("Erreur lors de l'initialisation de l'extension chargee de l'export");  //$NON-NLS-1$
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
