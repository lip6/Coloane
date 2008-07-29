package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.UserInterface;
import fr.lip6.move.coloane.interfaces.api.observers.IBrutalInterruptObserver;

import java.util.logging.Logger;

/**
 * Permet d'être notifié des demandes de déconnexion.
 */
public class BrutalInterruptObserver implements IBrutalInterruptObserver {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** {@inheritDoc} */
	public final void update(String e) {
		LOGGER.warning("Réception d'un demande de déconnexion forcée : " + e); //$NON-NLS-1$
		Com.getInstance().breakConnection(true);
		SessionManager.getInstance().disconnectAllSessions();
		SessionManager.getInstance().setAuthenticated(false);
		UserInterface.getInstance().redrawMenus();
		UserInterface.getInstance().platformState(false, ISession.CLOSED);
	}
}
