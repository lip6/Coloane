package fr.lip6.move.coloane.interfaces.extensions;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Interface que doivent implémenter les extensions d'export de modèles
 */
public interface IExportTo {
	/**
	 * Export le modele courant, dans un format precis, dans le fichier passe en parametre.
	 * @param graph modèle courant a exporter
	 * @param filePath chemin du fichier dans lequel exporter le modele courant
	 * @param monitor Moniteur qui gère la barre de progression, si l'extension n'utilise
	 * pas le moniteur, aucune barre de progression n'apparaitra.
	 * @throws ExtensionException toutes les exceptions susceptiblent d'etre engendrees et a afficher
	 */
	void export(IGraph graph, String filePath, IProgressMonitor monitor) throws ExtensionException;
}
