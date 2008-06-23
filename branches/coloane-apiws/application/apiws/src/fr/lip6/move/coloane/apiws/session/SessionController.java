package fr.lip6.move.coloane.apiws.session;

import java.util.Hashtable;

import fr.lip6.move.coloane.apiws.interfaces.session.IApiSession;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;

public class SessionController implements ISessionController{
	
	/**
	 * ATTENTION
	 * 
	 * Determine le nombre maximum de session
	 * Il Faudra le supprimer plus tard et utiliser la valeur fournie par le wapper
	 * 
	 * ATTENTION
	 */
	private  int MAX_SESSION = 5;
	
	/**
	 * Represent la session active
	 */
	private IApiSession activeSession;
	

	/**
	 * Represent l'ensemle des sessions
	 */
	private Hashtable<String, IApiSession> listSessions;
	
	
	/**
	 * Constructeur du controller de sessions
	 */
	public SessionController(){
		this.activeSession = null;
		this.listSessions = new Hashtable<String, IApiSession>();
	}
	
	/**
	 * Retourne la session active
	 */
	public IApiSession getActiveSession() {
		return activeSession;
	}
	
	
	/**
	 * Determine si la session est active, ou pas
	 */
	public boolean isActivateSession(IApiSession s) {
		return activeSession.equals(s);
	}
	
	/**
	 * Ajoute une session a la liste des session ouvert
	 */
	public boolean addSession(IApiSession s) {
		/**
		 *ATTENTION C'EST MAL FAIT: VOIR SI IL Y A UNE AUTRE SOLUTION POUR LA VALEUR DE RETOUR
		 */
		listSessions.put(s.getIdSession(),s);
		return listSessions.containsKey(s.getIdSession());
	}
	
	/**
	 * Supprime une session de la liste des session ouvert
	 */
	public boolean removeSession(IApiSession s) {
		/**
		 *ATTENTION C'EST MAL FAIT: VOIR SI IL Y A UNE AUTRE SOLUTION POUR LA VALEUR DE RETOUR
		 */
		listSessions.remove(s.getIdSession());
		return !listSessions.containsKey(s.getIdSession());
	}

	/**
	 * Verifie si on peut ouvrir une session
	 */
	public boolean openSession(IApiSession s){
		if (listSessions.size() <= MAX_SESSION && !listSessions.containsKey(s.getIdSession())){
			return  this.suspendSession(activeSession);
		}
		else{
			return false;
		}	
	}

	/**
	 * Verifie si on peut suspendre une session
	 */
	public boolean suspendSession(IApiSession s) {
		if (isActivateSession(s) && s.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE){
			return true;
		}
		return false;
	}

	/**
	 * Verifie si on peut restaurer une session
	 */
	public boolean resumeSession(IApiSession s) {
		if (s.getSessionStateMachine().getState() == ISessionStateMachine.SUSPEND_SESSION_STATE){
			return this.suspendSession(activeSession);
		}
		return false;
	}

	/**
	 * Verifie  si on peut fermer une session
	 */
	public boolean closeSession(IApiSession s) {
		if (isActivateSession(s) && s.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE){
			return true;
		}
		return false;
	}
	
	/**
	 * Verifie si on peut demander un service a la session
	 */
	public boolean askForService(IApiSession s) {
		if (isActivateSession(s) && s.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE){
			return true;
		}
		return false;
	}

	public void notifyEndOpenSession(IApiSession opened) {
		if (activeSession != null){
			activeSession.notifyEndSuspendSession();
		}
		this.activeSession = opened;
		this.addSession(opened);
		activeSession.notifyEndOpenSession();
	}

	public void notifyEndCloseSession(IApiSession closed,IApiSession resumed) {
		if (closed.getIdSession() == resumed.getIdSession()){
			this.activeSession = null;
			this.removeSession(closed);
			closed.notifyEndCloseSession();
		}
		else {
			this.activeSession = listSessions.get(resumed.getIdSession());
			this.activeSession.notifyEndResumeSession();
			this.removeSession(closed);
			closed.notifyEndCloseSession();
		}
	}
	public void notifyEndResumeSession(IApiSession resumed) {
		activeSession = listSessions.get(resumed.getIdSession());
		activeSession.notifyEndResumeSession();
	}

	public void notifyEndSuspendSession(IApiSession suspended ) {
		suspended.notifyEndSuspendSession();
	}

	public void notifyEndChangeSession(IApiSession suspended, IApiSession reloaded){
		activeSession = listSessions.get(reloaded.getIdSession());
		activeSession.notifyEndResumeSession();
		suspended.notifyEndChangeSession();
	}
	


	public void notifyEndResult() {
		// TODO Auto-generated method stub
		
	}

	public void notifyWaitingForModel() {
		// TODO Auto-generated method stub
		
	}

	public void notifyWaitingForResult() {
		// TODO Auto-generated method stub
		
	}

}
