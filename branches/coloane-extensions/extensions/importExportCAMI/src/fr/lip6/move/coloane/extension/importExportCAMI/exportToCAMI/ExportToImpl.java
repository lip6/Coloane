package fr.lip6.move.coloane.extension.importExportCAMI.exportToCAMI;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.interfaces.IExportTo;
import fr.lip6.move.coloane.core.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public class ExportToImpl implements IExportTo {

	public ExportToImpl() {}

	public void export(IModelImpl modeleCourant, String filePath)
			throws ColoaneException {
		// Creation du gestionnaire de formalism en charge de l'export
		FormalismManager fm = new FormalismManager();
		
		// Exportation
		fm.exportModel(modeleCourant, filePath);

	}

}
