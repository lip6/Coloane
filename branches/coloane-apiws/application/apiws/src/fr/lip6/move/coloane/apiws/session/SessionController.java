package fr.lip6.move.coloane.apiws.session;

import java.util.ArrayList;

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
	private ArrayList<IApiSession> listSessions;
	
	
	/**
	 * Constructeur du controller de sessions
	 */
	public SessionController(){
		this.activeSession = null;
		this.listSessions = new ArrayList<IApiSession>();
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
		return listSessions.add(s);
	}
	
	/**
	 * Supprime une session de la liste des session ouvert
	 */
	public boolean removeSession(IApiSession s) {
		/**
		 * Verifier si la session supprimer n'est pas active : si active ????
		 */
		return listSessions.remove(s);
	}

	/**
	 * Verifie si on peut ouvrir une session
	 */
	public boolean openSession(IApiSession s) {
		if (listSessions.size() <= MAX_SESSION){
			if (activeSession == null){
				this.activeSession = s;
				this.addSession(s);
				return true;
			}
			else{
				/**
				 * Ajouter plus tard la verification suivante :
				 * Que faire si on ouvre une session deja ouvert ??? 
				 */
				if (activeSession.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE){
					if (activeSession.suspendSession()){
						this.activeSession = s;
						this.addSession(s);
						return true;
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}

			}
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
			if (activeSession.suspendSession()){
				this.activeSession = s;
				return true;
			}
			else{
				return false;
			}
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

	public void notifyEndOpenSession() {
		activeSession.notifyEndOpenSession();	
	}

	public void notifyEndCloseSession(IApiSession s) {
		
		/**
		 * A COMPLETER
		 */
	}

	public void notifyEndResult() {
		// TODO Auto-generated method stub
		
	}

	public void notifyEndResumeSession(String nameSession) {
		// TODO Auto-generated method stub
		
	}

	public void notifyEndSuspendSession() {
		// TODO Auto-generated method stub
		
	}

	public void notifyWaitingForModel() {
		// TODO Auto-generated method stub
		
	}

	public void notifyWaitingForResult() {
		// TODO Auto-generated method stub
		
	}

}
