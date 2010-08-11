package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.core.session.MessageType;
import fr.lip6.move.coloane.core.ui.UserInterfaceManager;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptMessageObserver;

import java.util.logging.Logger;

/**
 * Permet d'être notifié des messages que renvois Framekit
 */
public class ReceptMessageObserver implements IReceptMessageObserver {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** {@inheritDoc} */
	public final void update(IReceptMessage e) {
		LOGGER.finer("Réception d'un message : " + e.getMessage()); //$NON-NLS-1$
		UserInterfaceManager.getInstance().printConsoleMessage(e.getMessage(), MessageType.getAssociatedType(e.getTypeMessage()));
	}
}
