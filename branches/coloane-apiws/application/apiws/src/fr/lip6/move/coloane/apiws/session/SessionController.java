package fr.lip6.move.coloane.apiws.session;

import java.util.Hashtable;

import fr.lip6.move.coloane.apiws.exceptions.ApiSessionException;
import fr.lip6.move.coloane.apiws.interfaces.session.IApiSession;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;

public class SessionController implements ISessionController{
	
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
	 * @throws ApiSessionException 
	 */
	public boolean openSession(IApiSession s) throws ApiSessionException{
		try{
			return  this.suspendSession(activeSession);
		}catch (ApiSessionException e){
			throw new ApiSessionException("Impossible d'ouvrire une session -> "+e.getMessage());
		}
	}

	/**
	 * Verifie si on peut suspendre une session
	 * @throws ApiSessionException 
	 */
	public boolean suspendSession(IApiSession s) throws ApiSessionException {
		if (s ==null){
			return true;
		}
		if (isActivateSession(s) && s.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE){
			return true;
		}
		throw new ApiSessionException("Impossible de suspendre la session: idSession="+s.getIdSession()+" etat="+s.getSessionStateMachine().getState()+" activeSession="+isActivateSession(s));
	}

	/**
	 * Verifie si on peut restaurer une session
	 * @throws ApiSessionException 
	 */
	public boolean resumeSession(IApiSession s) throws ApiSessionException {
		if (s.getSessionStateMachine().getState() == ISessionStateMachine.SUSPEND_SESSION_STATE){
			return true;
		}
		throw new ApiSessionException("Impossible de reprondre la session: idSession="+s.getIdSession()+" etat="+s.getSessionStateMachine().getState()+" activeSession="+isActivateSession(s));
	}

	/**
	 * Verifie  si on peut fermer une session
	 * @throws ApiSessionException 
	 */
	public boolean closeSession(IApiSession s) throws ApiSessionException {
		if (isActivateSession(s) && s.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE){
			return true;
		}
		throw new ApiSessionException("Impossible de fermer la session: idSession="+s.getIdSession()+" etat="+s.getSessionStateMachine().getState()+" activeSession="+isActivateSession(s));
	}
	
	/**
	 * Verifie si on peut demander un service a la session
	 * @throws ApiSessionException 
	 */
	public boolean askForService(IApiSession s) throws ApiSessionException {
		if (isActivateSession(s) && s.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE){
			return true;
		}
		throw new ApiSessionException("Impossible de demander un service sur la session: idSession="+s.getIdSession()+" etat="+s.getSessionStateMachine().getState()+" activeSession="+isActivateSession(s));
	}

	public void notifyEndOpenSession(IApiSession opened) {
		if (activeSession != null){
			activeSession.notifyEndSuspendSession();
		}
		this.activeSession = opened;
		this.addSession(opened);
		activeSession.notifyEndOpenSession();
	}

	public void notifyEndCloseSession(IApiSession closed,String idSessionToResumed) {
		if (idSessionToResumed.equals(closed.getIdSession())){
			this.activeSession = null;
			this.removeSession(closed);
			closed.notifyEndCloseSession();
		}
		else {
			this.activeSession = listSessions.get(idSessionToResumed);
			System.out.println(activeSession.getSessionStateMachine().getState());
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

	public void notifyEndChangeSession(IApiSession suspended, String idSessionToReloaded){
		activeSession = listSessions.get(idSessionToReloaded);
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
