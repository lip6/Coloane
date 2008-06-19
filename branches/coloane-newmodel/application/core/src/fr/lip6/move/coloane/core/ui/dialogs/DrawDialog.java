package fr.lip6.move.coloane.core.ui.dialogs;

import fr.lip6.move.coloane.core.exceptions.UIException;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.UserInterface;
import fr.lip6.move.coloane.interfaces.objects.IDialogCom;

/**
 * Classe chargee de l'affichage d'une boite de dialogue.
 * Cette classe est executee dans un thread dediee (affichage oblige)
 */
public class DrawDialog implements Runnable {

	private IDialogCom dialog;

	/**
	 * Constructeur.
	 * @param toDraw Le boite de dialogue a afficher
	 */
	public DrawDialog(IDialogCom toDraw) {
		this.dialog = toDraw;
	}

	/**
	 * Corps du thread
	 */
	public final void run() {
		try {
			UserInterface.drawDialog(this.dialog);
		} catch (UIException e) {
			Coloane.getLogger().warning("Erreur lors de la creation de la boite de dialogue : " + e.getMessage()); //$NON-NLS-1$
			Coloane.showErrorMsg(e.getMessage());
		}
	}

}
