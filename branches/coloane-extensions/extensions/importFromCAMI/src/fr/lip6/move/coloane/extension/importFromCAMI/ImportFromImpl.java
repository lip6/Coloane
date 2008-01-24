package fr.lip6.move.coloane.extension.importFromCAMI;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.interfaces.IImportFrom;
import fr.lip6.move.coloane.core.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public class ImportFromImpl implements IImportFrom {

	public ImportFromImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retourne un modele (selon la représentation interne de Coloane), depuis un ficier au format CAMI
	 */
	public IModelImpl importFrom(String filePath) throws ColoaneException {
		// TODO Auto-generated method stub
		FormalismManager fm = new FormalismManager();
		return fm.importModel(filePath);
	}

}
