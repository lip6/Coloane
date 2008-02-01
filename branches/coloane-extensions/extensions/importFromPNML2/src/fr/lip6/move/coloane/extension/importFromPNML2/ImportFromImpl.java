package fr.lip6.move.coloane.extension.importFromPNML2;

import java.util.StringTokenizer;
import java.util.Vector;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.interfaces.IImportFrom;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalism.Formalism;
import fr.lip6.move.coloane.core.motor.formalism.FormalismManager;
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

	public ImportFromImpl() {
		// TODO Auto-generated constructor stub
	}

	public IModelImpl importFrom(String filePath) throws ColoaneException {
		// TODO Auto-generated method stub
		
		// Determination du formalism avec l'extension
		StringTokenizer file = new StringTokenizer(filePath, ".");
		String fext = file.nextToken(); // Debut du nom
		fext = file.nextToken(); // Extension
		
		FormalismManager fm = new FormalismManager();
		Formalism formalism = fm.getFormalismByExtension(fext);
		
		// On verifie qu'un formalisme existe bien pour cette extension
		if (formalism == null){
			throw new ColoaneException(Messages.FormalismManager_0);
		}
		
		// Creation du model
		IModel genericModel = null;
		
		// Creation du Runner
		Runner myRunner = CamiPackage.eINSTANCE.getCamiFactory().createRunner();
		String[] camiModel;
		
		try{
			camiModel = myRunner.p2cami(filePath);
		} catch (CamiException e){
			e.printStackTrace();
			throw new ColoaneException(e.getMessage());
		}
		
		Vector<String> cami = new Vector<String>();
		for (int i=0; i<camiModel.length; i++){
			cami.add(camiModel[i]);
		}
		try {
			genericModel = new Model(cami, new CamiTranslator());
		} catch(SyntaxErrorException e){
			e.printStackTrace();
			throw new ColoaneException(Messages.FormalismManager_1);
		}
		
		// On indique au modele l'identifiant maximal
		// Cette etape est importante, si on souhaite ajouter de nouveaux noeuds et arcs au modele
		genericModel.setMaxId(genericModel.getMaxId());
		
		try{
			return new ModelImplAdapter(genericModel, formalism);
		} catch (BuildException e){
			e.printStackTrace();
			Coloane.getLogger().warning("Erreur lors de la construction du modele");
			return null;
		}
		
	}

}
