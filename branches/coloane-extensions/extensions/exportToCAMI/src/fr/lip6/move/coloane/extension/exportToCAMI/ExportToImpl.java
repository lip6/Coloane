package fr.lip6.move.coloane.extension.exportToCAMI;

import fr.lip6.move.coloane.core.interfaces.IExportTo;
import fr.lip6.move.coloane.core.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public class ExportToImpl implements IExportTo {

	public ExportToImpl() {
		// TODO Auto-generated constructor stub
	}

	public void export(IModelImpl modelAdapter, String fileName) throws Exception {
		// TODO Auto-generated method stub
		
		//Creation d'un FormalismManager ???
		FormalismManager fm = new FormalismManager();
		
		//Appel la methode exportModel qui export le model courant au format CAMI
		fm.exportModel(modelAdapter, fileName);
		
	}

}
