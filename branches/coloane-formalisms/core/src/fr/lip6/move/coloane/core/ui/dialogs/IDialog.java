package fr.lip6.move.coloane.core.ui.dialogs;

import fr.lip6.move.coloane.interfaces.IDialogResult;

/**
 * Interface pour les objet boites de dialogue
 */
public interface IDialog {

	/** Pour les boites de dialogue simples */
	int TERMINATED_OK = 1;
	int TERMINATED_CANCEL = 2;

	/** Pour les boites de dialogues interactives */
	int TERMINATED_ABORT = 1;
	int TERMINATED_QUIT = 2;

	/**
	 * Ouverture et affichage d'une boite de dialogue
	 * @return OK ou CANCEL
	 */
	int open();

	/**
	 * Ajoute un item a la liste de la boite de dialogue
	 * Valable seulement pour les boites de dialogue a liste
	 * @param choice La nouveau choix
	 */
	void addChoice(String choice);

	/**
	 * Retourne les resultat de la boite de dialogue
	 * @return
	 */
	IDialogResult getDialogResult();
}
