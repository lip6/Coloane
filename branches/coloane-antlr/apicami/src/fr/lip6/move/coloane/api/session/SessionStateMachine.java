package fr.lip6.move.coloane.api.session;

import fr.lip6.move.coloane.api.interfaces.ISessionStateMachine;

/**
 * cette classe implemente l'interface ISessionStateMachine.
 * @author kahoo && uu
 *
 */
public class SessionStateMachine implements ISessionStateMachine{

	private int state;

 public SessionStateMachine (){
	 this.state= INITIAL_STATE;
 }
	public int getState() {
		return this.state;
	}


	public boolean setWaitingForUpdatesAndMenusState() {
		if (this.state == INITIAL_STATE){
	this.state = WAITING_FOR_MENUS_AND_UPDATES_STATE;
		return true;
		}
		return false;
	}





	public boolean setIdleState() {
		if ((this.state == WAITING_FOR_MENUS_AND_UPDATES_STATE) ||
		   (this.state == WAITING_FOR_RESUME_SESSION_STATE)){
		this.state = IDLE_STATE;
		return true;
		}
		return false;
	}


	public boolean setWaitingForSuspendSessionState() {
		if (this.state == IDLE_STATE){
			this.state = WAITING_FOR_SUSPEND_SESSION_STATE;
			return true;
			}
		return false;
	}


	public boolean setSuspendSessionState() {
		if (this.state == WAITING_FOR_SUSPEND_SESSION_STATE){
			this.state = SUSPEND_SESSION_STATE;
			return true;
			}
		return false;
	}



	public boolean setWaitingForResumeSessionState() {
		if (this.state == SUSPEND_SESSION_STATE){
			this.state = WAITING_FOR_RESUME_SESSION_STATE;
			return true;
			}
		return false;
	}

	public boolean setWaitingForCloseSessionState() {

		if (this.state == IDLE_STATE){
			this.state = WAITING_FOR_CLOSE_SESSION_STATE;
			return true;
			}
		return false;
	}


	public boolean CloseSessionState() {
		if (this.state == WAITING_FOR_CLOSE_SESSION_STATE){
			this.state = CLOSE_SESSION_STATE;
			return true;
			}
		return false;

	}

}
