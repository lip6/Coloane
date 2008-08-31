package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Interface que doivent implémenter les extensions d'export de modèles
 */
public interface IExportTo {
	/**
	 * Export le modele courant, dans un format precis, dans le fichier passe en parametre.
	 * @param graph modèle courant a exporter
	 * @param filePath chemin du fichier dans lequel exporter le modele courant
	 * @throws ColoaneException toutes les exceptions susceptiblent d'etre engendrees et a afficher
	 */
	void export(IGraph graph, String filePath) throws ColoaneException;
}
