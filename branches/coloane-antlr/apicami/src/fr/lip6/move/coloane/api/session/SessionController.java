package fr.lip6.move.coloane.api.session;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IApiSession;
import fr.lip6.move.coloane.api.interfaces.ISessionController;

public class SessionController implements ISessionController {

	private ArrayList<IApiSession> liste;
	private IApiSession activeSession;

	public IApiSession getActiveSession(){
		return null;
	}

	public boolean addSession(IApiSession s){
		return false;
	}

	public boolean removeSession(IApiSession s ){
		return false;
	}

	public boolean ifActivateSession(IApiSession s ){
		return false;
	}

}