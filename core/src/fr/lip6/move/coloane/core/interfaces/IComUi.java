package fr.lip6.move.coloane.core.interfaces;

import fr.lip6.move.coloane.interfaces.IDialogResult;

/**
 * Toutes les methodes qui doivent etre implementees par la COM pour L'UI
 */
public interface IComUi {

	/**
	 * Demande d'un service a la plateforme
	 * @param rootMenuName Le menu racine
	 * @param refernceName Le menu de reference (qui n'est pas forcement le parent)
	 * @param serviceName Le service a executer
	 */
	void askForService(String rootMenuName, String refernceName, String serviceName);

	/**
	 * Transmission des resultats d'une boite de dialogue a la plateforme
	 * @param results Les resultats sous la forme d'un {@link IDialogResult}
	 */
	void sendDialogAnswers(IDialogResult results);

}
