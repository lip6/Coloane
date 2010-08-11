package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.core.ui.UserInterfaceManager;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

/**
 * Permet d'être notifier des demande de boite de dialogue de framekit
 */
public class ReceptDialogObserver implements IReceptDialogObserver {

	/** {@inheritDoc} */
	public final void update(IDialog dialog) {
		UserInterfaceManager.getInstance().drawDialog(dialog);
	}

}
