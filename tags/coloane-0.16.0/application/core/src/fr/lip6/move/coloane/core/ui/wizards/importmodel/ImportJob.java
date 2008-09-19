package fr.lip6.move.coloane.core.ui.wizards.importmodel;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.core.ui.files.ModelWriter;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Job se chargeant d'importer un fichier externe
 */
public class ImportJob extends Job {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** L'instance de conversion */
	private IImportFrom importInstance;

	private String formalism;

	private String path;

	private IFile newFile;

	/**
	 * @param name nom du job
	 * @param importInstance extension se chargeant de l'import
	 * @param formalism formalisme
	 * @param path chemin vers le fichier à charger
	 * @param newFile IFile dans lequel doivent être chargé les données
	 */
	public ImportJob(String name, IImportFrom importInstance, String formalism, String path, IFile newFile) {
		super(name);
		this.importInstance = importInstance;
		this.formalism = formalism;
		this.path = path;
		this.newFile = newFile;
	}

	/** {@inheritDoc} */
	@Override
	protected final IStatus run(IProgressMonitor monitor) {
		try {
			int totalWork = new Long(new File(path).length()).intValue();
			monitor.beginTask("Import " + path, totalWork + 100); //$NON-NLS-1$

			monitor.subTask("Create graph"); //$NON-NLS-1$
			IGraph model = importInstance.importFrom(path, formalism, monitor);

			// Traduction du modele au format xml
			monitor.subTask("Convert to XML"); //$NON-NLS-1$
			String xmlString = ModelWriter.translateToXML(model);
			InputStream inputS = new ByteArrayInputStream(xmlString.getBytes("UTF-8")); //$NON-NLS-1$

			newFile.setContents(inputS, true, false, null);
			monitor.done();
		} catch (CoreException e) {
			LOGGER.warning("Echec lors de la creation du fichier"); //$NON-NLS-1$
			return e.getStatus();
		} catch (ColoaneException e) {
			LOGGER.warning("Echec de l'import : " + e.getMessage()); //$NON-NLS-1$
			return new Status(IStatus.ERROR, "coloane", "Echec de l'import : " + e.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (UnsupportedEncodingException e) {
			LOGGER.warning("Echec lors de la creation du fichier (charset invalide)"); //$NON-NLS-1$
			return new Status(IStatus.ERROR, "coloane", "Echec lors de la creation du fichier (charset invalide)", e); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return Status.OK_STATUS;
	}

}
