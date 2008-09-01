package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Interface que doivent implémenter les extensions d'import de modèles
 */
public interface IImportFrom {
	/**
	 * Permet de recuperer un modele correspendant a la represention interne de Coloane
	 * @param filePath chemin du fichier a importer
	 * @param formalism Le formalisme du modèle à importer
	 * @return un modele correspendant a la represention interne de Coloane
	 * @throws ColoaneException toutes les exceptions susceptiblent d'etre engendrees et a afficher
	 */
	IGraph importFrom(String filePath, String formalism) throws ColoaneException;
}
