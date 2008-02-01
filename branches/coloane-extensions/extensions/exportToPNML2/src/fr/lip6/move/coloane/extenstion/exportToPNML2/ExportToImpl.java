package fr.lip6.move.coloane.extenstion.exportToPNML2;

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
		
		// Recuperation du model sous forme d'un vector<Sting>
		Vector<String> cami = modeleCourant.getGenericModel().translate();
		System.out.println("Vector<String> cami");
		for (int i=0; i<cami.size();i++){
			System.out.println(cami.get(i));
		}
		System.out.println("Taille de Vector<String> cami:"+cami.size());
		// Convertion du model Vector<String> vers String[]
		Object[] camiModelObjet = cami.toArray();
		String[] camiModelString = new String[cami.size()+2];
		camiModelString[0] = "DB()";
		for (int i=0; i< cami.size();i++){
			camiModelString[i+1] = camiModelObjet[i].toString();
		}
		camiModelString[cami.size()+1] = "FB()";
		
		// Affichage du model
		System.out.println("String[] camiModelString");
		for (int i=0; i<cami.size()+2;i++){
			System.out.println(camiModelString[i]);
		}
		System.out.println("Taille de String[] camiModelString:"+camiModelString.length);
		
		try {
			System.out.println(filePath);
			myRunner.cami2p(camiModelString, filePath);
		} catch (CamiException e){
			e.printStackTrace();
			throw new ColoaneException(e.getMessage());
		}

	}

}
