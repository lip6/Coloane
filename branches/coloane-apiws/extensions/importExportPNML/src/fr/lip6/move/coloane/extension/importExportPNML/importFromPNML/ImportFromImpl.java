package fr.lip6.move.coloane.extension.importExportPNML.importFromPNML;

import java.util.Vector;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalism.Messages;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.ModelImplAdapter;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.Model;
import fr.lip6.move.coloane.interfaces.translators.CamiTranslator;
import fr.lip6.move.pnml.cpnami.cami.CamiException;
import fr.lip6.move.pnml.cpnami.cami.CamiPackage;
import fr.lip6.move.pnml.cpnami.cami.Runner;

public class ImportFromImpl implements IImportFrom {

	public ImportFromImpl() {}

	public IModelImpl importFrom(String filePath, String formalism) throws ColoaneException {
		
		// Creation d'un model
		IModel genericModel = null;
		
		// Creation d'un Runner
		Runner myRunner = CamiPackage.eINSTANCE.getCamiFactory().createRunner();
		
		// Recuperation d'un model CAMI en vector<String>
		Vector<String> camiModel;
		try{
			camiModel = myRunner.p2cami(filePath);
			
			// Suppresion du dernier element :FE
			camiModel.remove(camiModel.size()-1);
			// Suppresion du premier element :DE
			camiModel.remove(0);
		} catch (CamiException e){
			e.printStackTrace();
			throw new ColoaneException(e.getMessage());
		}
		
		// ??
		try {
			genericModel = new Model(camiModel, new CamiTranslator());
			genericModel.setFormalism(formalism);
		} catch (SyntaxErrorException e){
			e.printStackTrace();
			throw new ColoaneException(Messages.FormalismManager_1);
		}
		
		// On indique au modele l'identifiant maximal
		// Cette etape est importante, si on souhaite ajouter de nouveaux noeuds et arcs au modele
		genericModel.setMaxId(genericModel.getMaxId());
		
		try{
			return new ModelImplAdapter(genericModel);
		} catch (BuildException e){
			e.printStackTrace();
			Coloane.getLogger().warning("Erreur lors de la construction du modele");
			return null;
		}
		
	}

}
