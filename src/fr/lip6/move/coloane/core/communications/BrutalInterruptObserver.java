package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.core.motor.Motor;
import fr.lip6.move.coloane.interfaces.api.observers.IBrutalInterruptObserver;

import java.util.logging.Logger;

import org.eclipse.core.runtime.jobs.Job;

/**
 * Permet d'être notifié des demandes de déconnexion.
 */
public class BrutalInterruptObserver implements IBrutalInterruptObserver {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** {@inheritDoc} */
	public final void update(String e) {
		LOGGER.warning("Réception d'un demande de déconnexion forcée : " + e); //$NON-NLS-1$
		Job.getJobManager().cancel(null);
		Motor.getInstance().breakConnection(true);
	}
}
