package fr.lip6.move.coloane.extension.exportToPNML;

import java.util.Vector;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.interfaces.IExportTo;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.pnml.cpnami.cami.CamiException;
import fr.lip6.move.pnml.cpnami.cami.CamiPackage;
import fr.lip6.move.pnml.cpnami.cami.Runner;

public class ExportToImpl implements IExportTo {

	public ExportToImpl() {
		// TODO Auto-generated constructor stub
	}

	public void export(IModelImpl modeleCourant, String filePath)
			throws ColoaneException {
		// TODO Auto-generated method stub
		
		// Creation du Runner
		Runner myRunner = CamiPackage.eINSTANCE.getCamiFactory().createRunner();
		
		
		// Recuperation du model au format CAMI
		Vector<String> cami = modeleCourant.getGenericModel().translate();
		
		// Convertion du model de Vector<String> vers String[]
		String[] camiModel = new String[cami.size()];
		for (int i =0; i< cami.size(); i++){
			camiModel[i]=cami.get(i);
		}
		
		try {
			System.out.println(filePath);
			myRunner.cami2p(camiModel, filePath);
		} catch (CamiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERRORRRRR:"+e.getMessage());
			//throw new ColoaneException(e.getMessage());
		}
		
	}

}
