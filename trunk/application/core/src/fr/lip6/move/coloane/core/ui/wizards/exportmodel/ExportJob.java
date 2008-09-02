package fr.lip6.move.coloane.core.ui.wizards.exportmodel;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Job pour exporter une liste de fichiers dans un format donné.
 */
public class ExportJob extends Job {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private IExportTo exportInstance;

	private String outputDirectory;

	private String newExtension;

	private IFile file;

	/**
	 * @param name nom du job
	 * @param file fichier à exporter
	 * @param exportInstance extension se chargeant de l'export
	 * @param outputDirectory dossier cible
	 * @param newExtension nouvelle extension
	 */
	public ExportJob(String name, IFile file, IExportTo exportInstance, String outputDirectory, String newExtension) {
		super(name);
		if (exportInstance == null) {
			throw new NullPointerException("exportInstance must not be null"); //$NON-NLS-1$
		}
		this.file = file;
		this.exportInstance = exportInstance;
		this.outputDirectory = outputDirectory;
		this.newExtension = newExtension;
	}

	/** {@inheritDoc} */
	@Override
	protected final IStatus run(IProgressMonitor monitor) {
		LOGGER.finer("Fichier a exporter : " + file.getName() + " vers " + outputDirectory); //$NON-NLS-1$ //$NON-NLS-2$

		IGraph model = ModelLoader.loadFromXML(file);
		LOGGER.fine("L'assistant d'export a ete trouve et instancie... Calcul du nom final"); //$NON-NLS-1$

		// Manipulation du nom de fichier pour supprimer l'ancienne extension et remplacer par la nouvelle
		String newName = file.getName().substring(0, file.getName().lastIndexOf('.') + 1) + newExtension;
		LOGGER.finer("Nom final : " + newName); //$NON-NLS-1$

		try {
			// TODO : les extensions devrais prendre en paramètre le monitor pour faire gérer la barre de progression
			exportInstance.export(model, outputDirectory + "/" + newName); //$NON-NLS-1$
		} catch (ColoaneException e) {
			return new Status(IStatus.ERROR, "coloane", "export " + file + " to " + outputDirectory + "/" + newName + "failed", e); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		}
		return Status.OK_STATUS;
	}

}
