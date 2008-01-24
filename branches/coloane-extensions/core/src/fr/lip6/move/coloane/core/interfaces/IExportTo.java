package fr.lip6.move.coloane.core.interfaces;

import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public interface IExportTo {
	/**
	 * Export le modele courant, dans un format précis, dans le fichier passé en paramétre.
	 * @param modeleCourant modele courant, à exporter
	 * @param filePath chemin du fichier dans lequel exporter le modele courant
	 * @throws Exception toutes les exceptions susceptiblent d'étre engendrées et à afficher
	 */
	public void export(IModelImpl modeleCourant,String filePath) throws Exception;
}
