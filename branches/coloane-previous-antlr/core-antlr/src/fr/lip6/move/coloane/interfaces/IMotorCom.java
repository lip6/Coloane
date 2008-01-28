package fr.lip6.move.coloane.interfaces;

import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.motor.session.SessionManager;

public interface IMotorCom {

	SessionManager getSessionManager();

	void setNewModel(IModel model);

}
