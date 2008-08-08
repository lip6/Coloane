package fr.lip6.move.coloane.core.motor;

import fr.lip6.move.coloane.core.motor.session.Session;
import fr.lip6.move.coloane.core.motor.session.SessionManager;

import java.util.logging.Logger;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Job asynchrone qui pourrait être arrété par la méthode cancel.
 */
public abstract class AsyncJob extends Job {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * @param name nom
	 * @see Job
	 */
	public AsyncJob(String name) {
		super(name);
	}

	/** {@inheritDoc} */
	@Override
	protected final void canceling() {
		LOGGER.warning("Arret du job asynchrone : " + getName()); //$NON-NLS-1$
		((Session) SessionManager.getInstance().getCurrentSession()).interruptService();
		done(new Status(IStatus.ERROR, "coloane", Messages.CancelableJob_1 + getName() + Messages.CancelableJob_2)); //$NON-NLS-1$
	}

}
