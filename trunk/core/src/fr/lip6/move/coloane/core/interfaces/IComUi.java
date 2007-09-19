package fr.lip6.move.coloane.core.interfaces;

import fr.lip6.move.coloane.interfaces.IDialogResult;

/**
 * Toutes les methodes qui doivent etre implementees par la COM pour l'UI
 */
public interface IComUi {

	/**
	 * Transmission des resultats d'une boite de dialogue a la plateforme
	 * @param results Les resultats sous la forme d'un {@link IDialogResult}
	 */
	void sendDialogAnswers(IDialogResult results);

}
