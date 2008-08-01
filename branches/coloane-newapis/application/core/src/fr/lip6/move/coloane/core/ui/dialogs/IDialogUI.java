package fr.lip6.move.coloane.core.ui.dialogs;

import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;

/**
 * Interface pour les objet boites de dialogue
 */
public interface IDialogUI {

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
	 * @return les resultats de la boite de dialogue
	 */
	IDialogAnswer getDialogResult();
}
