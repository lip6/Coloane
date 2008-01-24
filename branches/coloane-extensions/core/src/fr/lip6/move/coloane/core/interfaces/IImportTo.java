package fr.lip6.move.coloane.core.interfaces;

import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public interface IImportTo {
	/**
	 * Permet de récupérer un modele correspendant à la représention interne de Coloane
	 * @param filePath chemin du fichier à importer
	 * @return un modele correspendant à la représention interne de Coloane
	 * @throws Exception toutes les exceptions susceptiblent d'étre engendrées et à afficher
	 */
	public IModelImpl importTo(String filePath) throws Exception;
}
