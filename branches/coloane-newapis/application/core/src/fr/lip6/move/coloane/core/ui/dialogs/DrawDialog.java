package fr.lip6.move.coloane.core.ui.dialogs;

import fr.lip6.move.coloane.core.ui.UserInterface;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

import java.util.logging.Logger;

/**
 * Classe chargee de l'affichage d'une boite de dialogue.
 * Cette classe est executee dans un thread dediee (affichage oblige)
 */
public class DrawDialog implements Runnable {
	/** Logger 'fr.lip6.move.coloane.core'. */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

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
		UserInterface.getInstance().drawDialog(this.dialog);
	}

}
