package fr.lip6.move.coloane.extension.exportToCAMI;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.interfaces.IExportTo;
import fr.lip6.move.coloane.core.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public class ExportToImpl implements IExportTo {

	/**
	 * Constructeur
	 */
	public ExportToImpl() {	
		// Rien a faire dans le constructeur
	}

	/**
	 * Methode chargee de l'export du modele courant vers un fichier CAMI
	 * @param modelAdapter Le modele adapte a traduire en CAMI
	 * @param filePath Le nom du fichier de sortie
	 */
	public void export(IModelImpl modelAdapter, String filePath) throws ColoaneException {
		// Creation du gestionnaire de formalism en charge de l'export
		FormalismManager fm = new FormalismManager();
		
		// Exportation
		fm.exportModel(modelAdapter, filePath);
		
	}

}
