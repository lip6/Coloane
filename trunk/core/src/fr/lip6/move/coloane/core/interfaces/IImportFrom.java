package fr.lip6.move.coloane.core.interfaces;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public interface IImportFrom {
	/**
	 * Permet de récupérer un modele correspendant à la représention interne de Coloane
	 * @param filePath chemin du fichier à importer
	 * @return un modele correspendant à la représention interne de Coloane
	 * @throws ColoaneException toutes les exceptions susceptiblent d'étre engendrées et à afficher
	 */
	public IModelImpl importFrom(String filePath) throws ColoaneException;
}
