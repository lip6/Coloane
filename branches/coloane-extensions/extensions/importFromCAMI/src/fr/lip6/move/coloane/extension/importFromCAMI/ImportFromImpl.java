package fr.lip6.move.coloane.extension.importFromCAMI;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.interfaces.IImportFrom;
import fr.lip6.move.coloane.core.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public class ImportFromImpl implements IImportFrom {

	/**
	 * Constructeur
	 */
	public ImportFromImpl() {
		// Rien a faire dans le constructeur
	}

	/**
	 * Retourne un modele adapte lu depuis un fichier CAMI
	 * @param filePath Le nom du fichier contenant les commande CAMI
	 */
	public IModelImpl importFrom(String filePath) throws ColoaneException {

		// Chargement du gestionnaire de formalismes
		FormalismManager fm = new FormalismManager();
		
		// Importation
		return fm.importModel(filePath);
	}
}
