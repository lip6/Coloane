package fr.lip6.move.coloane.apiws.session;

import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;

/**
 * Cette classe représent l'automate d'une session
 *
 * @author Monir CHAOUKI
 */
public class SessionStateMachine implements ISessionStateMachine {

	/**
	 * Etat courrant de l'automate representant une session
	 */
	private int state;

	/**
	 * Constructeur de l'automate
	 */
	public SessionStateMachine() {
		this.state = INITIAL_STATE;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getState() {
		return state;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setState(int state) {
		this.state = state;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean goToCloseSessionState() {
		if (state == WAITING_FOR_CLOSE_SESSION_STATE) {
			state = CLOSE_SESSION_STATE;
			return true;
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean goToIdleState() {
		if (state == WAITING_FOR_MENUS_AND_UPDATES_STATE || state == WAITING_FOR_RESULT_STATE || state == SUSPEND_SESSION_STATE) {
			state = IDLE_STATE;
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean goToSuspendSessionState() {
		if (state == IDLE_STATE) {
			state = SUSPEND_SESSION_STATE;
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean goToWaitingForCloseSessionState() {
		if (state == IDLE_STATE || state == SUSPEND_SESSION_STATE) {
			state = WAITING_FOR_CLOSE_SESSION_STATE;
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean goToWaitingForResultState() {
		if (state == IDLE_STATE) {
			state = WAITING_FOR_RESULT_STATE;
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean goToWaitingForUpdatesAndMenusState() {
		if (state == INITIAL_STATE || state == SUSPEND_SESSION_STATE) {
			state = WAITING_FOR_MENUS_AND_UPDATES_STATE;
			return true;
		}
		return false;
	}

}
