package fr.lip6.move.coloane.extension.importExportPNML.exportToPNML;

import java.util.Vector;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.pnml.cpnami.cami.CamiException;
import fr.lip6.move.pnml.cpnami.cami.CamiPackage;
import fr.lip6.move.pnml.cpnami.cami.Runner;

public class ExportToImpl implements IExportTo {

	public ExportToImpl() {}

	public void export(IModelImpl modeleCourant, String filePath)
			throws ColoaneException {
		
		// Creation du Runner
		Runner myRunner = CamiPackage.eINSTANCE.getCamiFactory().createRunner();
		// Reinisiatlisation du Runner
		myRunner.resetIdsRepository();
		
		// Recuperation du model sous forme d'un vector<Sting>
		Vector<String> cami = modeleCourant.getGenericModel().translate();
		// Ajout de FB() a la fin du vecteur
		cami.add("FB()");
		// Ajout de DB() au debut du vecteur
		cami.add(0,"DB()");
		
		try {
			myRunner.cami2p(cami, filePath);
		} catch (CamiException e){
			e.printStackTrace();
			throw new ColoaneException(e.getMessage());
		}


	}

}
