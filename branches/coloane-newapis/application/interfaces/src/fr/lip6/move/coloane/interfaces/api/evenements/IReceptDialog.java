package fr.lip6.move.coloane.interfaces.api.evenements;

import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

public interface IReceptDialog {
	
	/**
	 * Recupere la boite de dialogue recu
	 * @return la boite de dialogue recu
	 */
	public IDialog getDialog();
}