package fr.lip6.move.coloane.core.ui.dialogs;

import fr.lip6.move.coloane.core.ui.UserInterfaceManager;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

/**
 * Classe chargee de l'affichage d'une boite de dialogue.
 * Cette classe est executee dans un thread dediee (affichage oblige)
 */
public class DrawDialog implements Runnable {
	private IDialog dialog;

	/**
	 * Constructeur.
	 * @param toDraw Le boite de dialogue a afficher
	 */
	public DrawDialog(IDialog toDraw) {
		this.dialog = toDraw;
	}

	/**
	 * Corps du thread
	 */
	public final void run() {
		UserInterfaceManager.getInstance().drawDialog(this.dialog);
	}

}
