package fr.lip6.move.coloane.extension.importExportRomeo.importFromRomeo;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.File;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;


/**
 * Import a CAMI formatted model into a graph model
 *
 * @author Jean-Baptiste Voron
 */
public class ImportFromImpl implements IImportFrom {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Table needed to link CAMI id to Coloane ID**/
	private Map<Integer, Integer> ids;


	/**
	 * Import a CAMI file into a Graph object
	 * @param filePath The location of the file to be imported
	 * @param formalism The formalism (since CAMI file does not define the model formalism)
	 * @param monitor A monitor to follow the operation progress
	 * @return The resulting model {@link IGraph}
	 * @throws ColoaneException Something went wrong
	 */
	public final IGraph importFrom(String filePath, String formalism, IProgressMonitor monitor) throws ColoaneException {
		IGraph model = null;

		LOGGER.finer("Creation du fichier..."); ////$NON-NLS-1$
		File toImport = new File(filePath);

//		try {
//			// Read file and store all its line into a buffer
//			BufferedReader buffer = new BufferedReader(new FileReader(toImport));
//
//			try {
//				// Build the model
//				model = this.loadModel(buffer, formalism, monitor);
//				LOGGER.fine("Le modele importe est identifie comme instance du formalisme :" + formalism);
//			} catch (SyntaxErrorException se) {
//				throw new ColoaneException("Error while parsing the model : " + se.getMessage());
//			} catch (ModelException me) {
//				throw new ColoaneException("Error while building the model : " + me.getMessage());
//			}
//			buffer.close();
//		} catch (FileNotFoundException fe) {
//			LOGGER.warning("Le fichier " + filePath + " est introuvable...");
//			throw new ColoaneException("Filename " + filePath + "is invalid or does not exist.");
//		} catch (IOException ioe) {
//			LOGGER.warning("Erreur lors de la lecture du fichier");
//			throw new ColoaneException("An error has occured during the file reading.");
//		}
		return model;
	}


}
