package fr.lip6.move.coloane.extension.importExportPNML.importFromPNML;

import org.eclipse.core.runtime.IProgressMonitor;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.interfaces.model.IGraph;

public class ImportFromImpl implements IImportFrom {

	/**
	 * Constructeur
	 */
	public ImportFromImpl() { }

	/** {@inheritDoc} */
	public final IGraph importFrom(String filePath, String formalism, IProgressMonitor monitor) throws ColoaneException {

		try {
		    return null;
		} catch (Exception e) {
		    System.out.println("Aie...");
		    return null;
		}
	}
}
