package fr.lip6.move.coloane.extension.importExportPNML.importFromPNML;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.interfaces.model.IGraph;

public class ImportFromImpl implements IImportFrom {

	/**
	 * Constructeur
	 */
	public ImportFromImpl() { }

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.extensions.IImportFrom#importFrom(java.lang.String, java.lang.String)
	 */
	public final IGraph importFrom(String filePath, String formalism) throws ColoaneException {

		try {
		    return null;
		} catch (Exception e) {
		    System.out.println("Aie...");
		    return null;
		}
	}
}
