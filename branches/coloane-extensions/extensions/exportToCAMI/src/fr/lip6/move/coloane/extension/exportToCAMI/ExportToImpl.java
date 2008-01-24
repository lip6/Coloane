package fr.lip6.move.coloane.extension.exportToCAMI;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.interfaces.IExportTo;
import fr.lip6.move.coloane.core.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public class ExportToImpl implements IExportTo {

	public ExportToImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Export le modele courant modelAdapter (selon sa représentation interne) vers le format CAMI
	 * dans le fichier qui lui est passé en paramétre
	 */
	public void export(IModelImpl modelAdapter, String filePath) throws ColoaneException {
		// TODO Auto-generated method stub
		
		//Creation d'un FormalismManager ???
		FormalismManager fm = new FormalismManager();
		
		//Appel la methode exportModel (qui existe déjà) qui export le model courant au format CAMI
		fm.exportModel(modelAdapter, filePath);
		
	}

}
