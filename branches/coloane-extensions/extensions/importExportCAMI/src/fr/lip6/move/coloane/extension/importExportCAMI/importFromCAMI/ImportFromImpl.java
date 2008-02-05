package fr.lip6.move.coloane.extension.importExportCAMI.importFromCAMI;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.interfaces.IImportFrom;
import fr.lip6.move.coloane.core.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public class ImportFromImpl implements IImportFrom {

	public ImportFromImpl() {}

	public IModelImpl importFrom(String filePath) throws ColoaneException {
		
		// Chargement du gestionnaire de formalismes
		FormalismManager fm = new FormalismManager();
		
		// Importation
		return fm.importModel(filePath);
	}

}
