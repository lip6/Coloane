package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.ui.model.interfaces.IGraph;

public interface IExportTo {
	/**
	 * Export le modele courant, dans un format precis, dans le fichier passe en parametre.
	 * @param modeleCourant modele courant a exporter
	 * @param filePath chemin du fichier dans lequel exporter le modele courant
	 * @throws ColoaneException toutes les exceptions susceptiblent d'etre engendrees et a afficher
	 */
	void export(IGraph graph, String filePath) throws ColoaneException;
}
