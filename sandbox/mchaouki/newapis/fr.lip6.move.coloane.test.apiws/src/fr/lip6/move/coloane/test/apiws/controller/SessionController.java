package fr.lip6.move.coloane.test.apiws.controller;

import java.util.Hashtable;

import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

public class SessionController {
	
	private Hashtable<String, IApiSession> listSessions;
	
	private IApiSession activeSession;
	
	private boolean connectionOpened;
	
	public SessionController(){
		this.listSessions = new Hashtable<String, IApiSession>();
		this.connectionOpened = false;
	}
	
	public void addSession(String nameSession,IApiSession session){
		this.listSessions.put(nameSession, session);
	}
	
	public void removeSession(String nameSession){
		this.listSessions.remove(nameSession);
	}
	
	public IApiSession getActiveSession(){
		return activeSession;
	}
	
	public void setActiveSession(String nameSession){
		this.activeSession = listSessions.get(nameSession);
	}
	
	public boolean isOpened(){
		return connectionOpened;
	}
	
	public void setConnectionOpened(boolean c){
		this.connectionOpened = c;
	}
	
	public IApiSession getSession(String nameSession){
		return listSessions.get(nameSession);
	}
}
