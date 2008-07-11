package fr.lip6.move.coloane.apiws.session;

import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;

public class SessionStateMachine implements ISessionStateMachine {
	
	/**
	 * Etat courrant de l'automate representant une session
	 */
	private int state;
	
	/**
	 * Constructeur de l'automate
	 */
	public SessionStateMachine(){
		this.state = INITIAL_STATE;
	}

	public int getState() {
		return state;
	}

	public boolean goToCloseSessionState() {
		if (state == WAITING_FOR_CLOSE_SESSION_STATE){
			state = CLOSE_SESSION_STATE;
			return true;
		}
		
		return false;
	}

	public boolean goToIdleState() {
		if (state == WAITING_FOR_MENUS_AND_UPDATES_STATE || state == WAITING_FOR_RESULT_STATE || state == SUSPEND_SESSION_STATE){
			state = IDLE_STATE;
			return true;
		}
		return false;
	}

	public boolean goToSuspendSessionState() {
		if (state == IDLE_STATE){
			state = SUSPEND_SESSION_STATE;
			return true;
		}
		return false;
	}

	public boolean goToWaitingForCloseSessionState() {
		if (state == IDLE_STATE || state == SUSPEND_SESSION_STATE){
			state = WAITING_FOR_CLOSE_SESSION_STATE;
			return true;
		}
		return false;
	}

	public boolean goToWaitingForResultState() {
		if (state == IDLE_STATE ){
			state = WAITING_FOR_RESULT_STATE;
			return true;
		}
		return false;
	}

	public boolean goToWaitingForUpdatesAndMenusState() {
		if (state == INITIAL_STATE || state == SUSPEND_SESSION_STATE){
			state = WAITING_FOR_MENUS_AND_UPDATES_STATE;
			return true;
		}
		return false;
	}

}
