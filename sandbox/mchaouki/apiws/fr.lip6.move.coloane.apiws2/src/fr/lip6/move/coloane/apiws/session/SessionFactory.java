package fr.lip6.move.coloane.apiws.session;

import java.util.HashMap;

import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

public class SessionFactory {
	
	public static IApiSession getNewApiSession(ISessionController sessionController, ISpeaker speaker){
		return (IApiSession) new ApiSession(sessionController,speaker);
	}
	
	public static ISessionController getNewSessionController( HashMap<Integer, Object> listObservables){
		return (ISessionController) new SessionController(listObservables);
	}
	
	public static ISessionStateMachine getNewSessionStateMachine(){
		return (ISessionStateMachine) new SessionStateMachine();
	}
}
