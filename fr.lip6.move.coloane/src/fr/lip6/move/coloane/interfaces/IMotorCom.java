package fr.lip6.move.coloane.interfaces;

import fr.lip6.move.coloane.model.IModel;
import fr.lip6.move.coloane.motor.session.SessionManager;

public interface IMotorCom {
	
	public SessionManager getSessionManager();
	
	public void setNewModel(IModel model);

}
