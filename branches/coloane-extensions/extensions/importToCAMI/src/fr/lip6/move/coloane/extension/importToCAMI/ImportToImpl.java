package fr.lip6.move.coloane.extension.importToCAMI;


import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.interfaces.IImportTo;
import fr.lip6.move.coloane.core.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public class ImportToImpl implements IImportTo {

	public ImportToImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retourne un modele (selon la représentation interne de Coloane), depuis un ficier au format CAMI
	 */
	public IModelImpl importTo(String filePath) throws ColoaneException {
		// TODO Auto-generated method stub
		FormalismManager fm = new FormalismManager();
		return fm.importModel(filePath);
	}

}
