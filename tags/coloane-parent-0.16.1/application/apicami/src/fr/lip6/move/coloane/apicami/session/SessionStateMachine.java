package fr.lip6.move.coloane.apicami.session;

import fr.lip6.move.coloane.apicami.interfaces.ISessionStateMachine;

import java.util.logging.Logger;

/**
 * Cette classe décrit le comportement d'une session<br>
 * Ce comportement peut être représenté comme une machine à états
 */
public class SessionStateMachine implements ISessionStateMachine {
	/** Le Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

	/** Etat courant */
	private int state;

	/**
	 * Constructeur
	 */
	public SessionStateMachine() {
		this.state = INITIAL_STATE;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getCurrentState() {
		return this.state;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setBusy() {
		LOGGER.finest("Etat precedent : " + this.state + " -> " + BUSY_STATE);
		this.state = BUSY_STATE;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setIdle() {
		LOGGER.finest("Etat precedent : " + this.state + " -> " + IDLE_STATE);
		this.state = IDLE_STATE;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setInitial() {
		LOGGER.finest("Etat precedent : " + this.state + " -> " + INITIAL_STATE);
		this.state = INITIAL_STATE;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setSuspend() {
		LOGGER.finest("Etat precedent : " + this.state + " -> " + SUSPEND_STATE);
		this.state = SUSPEND_STATE;
	}
}


