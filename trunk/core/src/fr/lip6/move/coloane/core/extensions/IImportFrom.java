package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public interface IImportFrom {
	/**
	 * Permet de recuperer un modele correspendant a la represention interne de Coloane
	 * @param filePath chemin du fichier a importer
	 * @return un modele correspendant a la represention interne de Coloane
	 * @throws ColoaneException toutes les exceptions susceptiblent d'etre engendrees et a afficher
	 */
	public IModelImpl importFrom(String filePath) throws ColoaneException;
}
