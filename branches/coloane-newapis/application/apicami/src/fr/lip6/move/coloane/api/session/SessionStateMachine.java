package fr.lip6.move.coloane.api.session;

import fr.lip6.move.coloane.api.interfaces.ISessionStateMachine;

/**
 * Cette classe décrit le comportement d'une session<br>
 * Ce comportement peut être représenté comme une machine à états
 */
public class SessionStateMachine implements ISessionStateMachine {

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
	public final int getState() {
		return this.state;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean setWaitingForUpdatesAndMenusState() {
		if (this.state == INITIAL_STATE) {
			this.state = WAITING_FOR_MENUS_AND_UPDATES_STATE;
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean setIdleState() {
		if (this.state == WAITING_FOR_MENUS_AND_UPDATES_STATE) {
			this.state = IDLE_STATE;
			return true;
		}

		if (this.state == WAITING_FOR_RESUME_SESSION_STATE) {
			this.state = IDLE_STATE;
			return true;
		}

		if (this.state == WAITING_FOR_RESULT_STATE) {
			this.state = IDLE_STATE;
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean setWaitingForSuspendSessionState() {
		if (this.state == IDLE_STATE) {
			this.state = WAITING_FOR_SUSPEND_SESSION_STATE;
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean setSuspendSessionState() {
		if (this.state == WAITING_FOR_SUSPEND_SESSION_STATE) {
			this.state = SUSPEND_SESSION_STATE;
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean setWaitingForResumeSessionState() {
		if (this.state == SUSPEND_SESSION_STATE) {
			this.state = WAITING_FOR_RESUME_SESSION_STATE;
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean setWaitingForCloseSessionState() {
		if (this.state == IDLE_STATE) {
			this.state = WAITING_FOR_CLOSE_SESSION_STATE;
			return true;
		}

		if (this.state == MODELE_SALE_STATE) {
			this.state = WAITING_FOR_CLOSE_SESSION_STATE;
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean closeSessionState() {
		if (this.state == WAITING_FOR_CLOSE_SESSION_STATE) {
			this.state = CLOSE_SESSION_STATE;
			// équivalent à this.state = INITIAL_STATE;
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean setWaitingForModelState() {
		if (this.state == WAITING_FOR_RESPONSE_STATE) {
			this.state = WAITING_FOR_MODEL_STATE;
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean setWaitingForResultState() {
		if (this.state == WAITING_FOR_MODEL_STATE) {
			this.state = WAITING_FOR_RESULT_STATE;
			return true;
		}

		if (this.state == WAITING_FOR_RESPONSE_STATE) {
			this.state = WAITING_FOR_RESULT_STATE;
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean setWaitingForResponseState() {
		if (this.state == IDLE_STATE) {
			this.state = WAITING_FOR_RESPONSE_STATE;
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean setWaitingForUpdatesState() {
		if (this.state == IDLE_STATE) {
			this.state = WAITING_FOR_UPDATES_STATE;
			return true;
		}
		return false;
	}
}
