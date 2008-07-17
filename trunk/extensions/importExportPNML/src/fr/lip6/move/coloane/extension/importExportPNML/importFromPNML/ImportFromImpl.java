package fr.lip6.move.coloane.extension.importExportPNML.importFromPNML;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.pnml.framework.general.PnmlImport;
import fr.lip6.move.pnml.framework.hlapi.HLAPIClass;
import fr.lip6.move.pnml.ptnet.PetriNetDoc;

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

		PnmlImport pi = new PnmlImport();
		try {
		    HLAPIClass retour = pi.importFile(filePath);
		    PetriNetDoc document = (PetriNetDoc) retour;
		    return null;
		} catch (Exception e) {
		    System.out.println("Aie...");
		    return null;
		}
	}
}
