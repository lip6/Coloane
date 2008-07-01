package fr.lip6.move.coloane.apiws.session;

import java.util.HashMap;
import java.util.Hashtable;

import fr.lip6.move.coloane.apiws.evenements.AnswerCloseSession;
import fr.lip6.move.coloane.apiws.evenements.AnswerOpenSession;
import fr.lip6.move.coloane.apiws.evenements.AnswerResumeSession;
import fr.lip6.move.coloane.apiws.evenements.AnswerSuspendSession;
import fr.lip6.move.coloane.apiws.exceptions.ApiSessionException;
import fr.lip6.move.coloane.apiws.interfaces.observables.ICloseSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IObservables;
import fr.lip6.move.coloane.apiws.interfaces.observables.IOpenSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IResumeSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.ISuspendSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.session.IApiSession;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;

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
	 * Represent la liste des observables
	 */
	private HashMap<Integer, Object> listObservables;
	
	/**
	 * Constructeur du controller de sessions
	 */
	public SessionController(HashMap<Integer, Object> listObservables){
		this.activeSession = null;
		this.listSessions = new Hashtable<String, IApiSession>();
		this.listObservables = listObservables;
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
		return activeSession.getIdSession().equals(s.getIdSession());
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
		if (s.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE){
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
		if (s.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE || s.getSessionStateMachine().getState() == ISessionStateMachine.SUSPEND_SESSION_STATE){
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
			notifyEndSuspendSession(activeSession);
		}
		this.activeSession = opened;
		this.addSession(opened);
		
		if (!activeSession.getSessionStateMachine().goToIdleState()){
			throw new IllegalStateException("Impossible d'aller vers a l'etat IDLE_STATE");
		}
		AnswerOpenSession answerOpenSession = new AnswerOpenSession(opened);
		((IOpenSessionObservable) listObservables.get(IObservables.OPEN_SESSION)).notifyObservers(answerOpenSession);
	
	}

	public void notifyEndCloseSession(IApiSession closed,Session sessionToResumed) {
		
		if (!closed.getSessionStateMachine().goToCloseSessionState()){
			throw new IllegalStateException("Impossible d'aller vers a l'etat CLOSE_SESSION_STATE");
		}
		this.removeSession(closed);
		AnswerCloseSession answerCloseSession = new AnswerCloseSession(closed);
		((ICloseSessionObservable) listObservables.get(IObservables.CLOSE_SESSION)).notifyObservers(answerCloseSession);
	
		// Si il n'y a plus de session en cours
		if (sessionToResumed.getSessionId().equals(closed.getIdSession())){
			this.activeSession = null;
			return;
		}
		// Si on ferme la session en cours
		if (isActivateSession(closed)){
			listSessions.get(sessionToResumed.getSessionId()).updateSession(sessionToResumed);
			notifyEndResumeSession(listSessions.get(sessionToResumed.getSessionId()));
			return;
		}

		
	}
	
	public void notifyEndResumeSession(IApiSession resumed) {
		
		activeSession = listSessions.get(resumed.getIdSession());
		
		if (!activeSession.getSessionStateMachine().goToIdleState()){
			throw new IllegalStateException("Impossible d'aller vers a l'etat IDLE_STATE");
		}
		
		AnswerResumeSession answerResumeSession = new AnswerResumeSession(resumed);
		((IResumeSessionObservable) listObservables.get(IObservables.RESUME_SESSION)).notifyObservers(answerResumeSession);
		
	}

	public void notifyEndSuspendSession(IApiSession suspended ) {
		if (!suspended.getSessionStateMachine().goToSuspendSessionState()){
			throw new IllegalStateException("Impossible d'aller vers a l'etat SUSPEND_SESSION_STATE");
		}
		
		AnswerSuspendSession answerSuspendSession = new AnswerSuspendSession(suspended);
		((ISuspendSessionObservable) listObservables.get(IObservables.SUSPEND_SESSION)).notifyObservers(answerSuspendSession);
		
	}

	public void notifyEndChangeSession(IApiSession suspended, Session sessionToResumed){
		notifyEndSuspendSession(suspended);
		listSessions.get(sessionToResumed.getSessionId()).updateSession(sessionToResumed);
		notifyEndResumeSession(listSessions.get(sessionToResumed.getSessionId()));
		
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
