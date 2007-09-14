package fr.lip6.move.coloane.core.interfaces;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.interfaces.model.IModel;

public interface IMotorCom {

	SessionManager getSessionManager();

	void setNewModel(IModel model);

}
