package fr.lip6.move.coloane.apiws.session;

import java.util.HashMap;

import fr.lip6.move.coloane.apiws.interfaces.session.IApiSession;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;

public class SessionFactory {
	
	public static IApiSession getNewApiSession(ISessionController sessionController, ISpeaker speaker, HashMap<Integer, Object> listObservables){
		return (IApiSession) new ApiSession(sessionController,speaker,listObservables);
	}
	
	public static ISessionController getNewSessionController(){
		return (ISessionController) new SessionController();
	}
	
	public static ISessionStateMachine getNewSessionStateMachine(){
		return (ISessionStateMachine) new SessionStateMachine();
	}
}
