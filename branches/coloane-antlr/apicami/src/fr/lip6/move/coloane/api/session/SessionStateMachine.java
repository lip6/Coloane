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
	 this.state= INITIALE_STATE;
 }
	public int getState() {
		return this.state;
	}


	public boolean setWaitingForUpdatesAndMenusState() {
		if (this.state == 0){
	this.state = WAITING_FOR_MENUS_AND_UPDATES_STATE;
		return true;
		}
		return false;
	}





	public boolean setIdleState() {
		if (this.state == 2){
		this.state = IDLE_STATE;
		return true;
		}
		return false;
	}

}
