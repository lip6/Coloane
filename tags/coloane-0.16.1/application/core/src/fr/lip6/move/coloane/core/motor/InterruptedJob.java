package fr.lip6.move.coloane.core.motor;

import java.util.logging.Logger;

import org.eclipse.core.runtime.jobs.Job;

/**
 * Job qui pourra être iterrompu lorsque la méthode cancel est appelé.
 */
public abstract class InterruptedJob extends Job {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * @param name nom
	 * @see Job
	 */
	public InterruptedJob(String name) {
		super(name);
	}

	/** {@inheritDoc} */
	@Override
	protected final void canceling() {
		LOGGER.warning("Envoi d'une interruption sur le Thread du job : " + getName()); //$NON-NLS-1$
		getThread().interrupt();
	}

}
