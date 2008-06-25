package fr.lip6.move.coloane.apiws.session;

import java.util.HashMap;

import fr.lip6.move.coloane.apiws.evenements.AnswerChangeSession;
import fr.lip6.move.coloane.apiws.evenements.AnswerCloseSession;
import fr.lip6.move.coloane.apiws.evenements.AnswerOpenSession;
import fr.lip6.move.coloane.apiws.interfaces.objects.IModel;
import fr.lip6.move.coloane.apiws.interfaces.observables.IChangeSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.ICloseSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IObservables;
import fr.lip6.move.coloane.apiws.interfaces.observables.IOpenSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.session.IApiSession;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;
import fr.lip6.move.wrapper.ws.CException;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;

public class ApiSession implements IApiSession{

	private String sessionDate;
	
	private String sessionFormalism;
	
	private String sessionName;
	
	private String interlocutor;
	
	private int mode;
	
	private String idSession;
	
	private Session sessionOpened;
	
	private Session sessionClosed;
	
	private Session sessionChanged;
	
	private ISessionController sessionController;
	
	private ISpeaker speaker;
	
	private ISessionStateMachine automate;
	
	private HashMap<Integer, Object> listObservables;
	
	
	public ApiSession(ISessionController sessionController,ISpeaker speaker, HashMap<Integer, Object> listObservables){
		this.sessionDate = null;
		this.sessionFormalism = null;
		this.sessionName = null;
		this.interlocutor = null;
		this.mode = -1;
		this.idSession = null;
		this.sessionOpened = null;
		this.sessionClosed = null;
		this.sessionChanged = null;
		this.sessionController = sessionController;
		this.speaker = speaker;
		this.automate = SessionFactory.getNewSessionStateMachine();
		this.listObservables = listObservables;
	}

	public String getInterlocutor() {
		return interlocutor;
	}

	public int getMode() {
		return mode;
	}
	
	public String getIdSession(){
		return idSession;
	}

	public ISessionController getSessionController() {
		return sessionController;
	}

	public String getSessionDate() {
		return sessionDate;
	}

	public String getSessionFormalism() {
		return sessionFormalism;
	}

	public String getSessionName() {
		return sessionName;
	}

	public ISessionStateMachine getSessionStateMachine() {
		return automate;
	}


	public void openSession(String sessionDate, String sessionFormalism, String sessionName, String interlocutor, int mode) throws CException{
		this.sessionDate = sessionDate;
		this.sessionFormalism = sessionFormalism;
		this.sessionName = sessionName;
		this.interlocutor = interlocutor;
		this.mode = mode;
		
		if (sessionController.openSession(this)){
			if (!automate.goToWaitingForUpdatesAndMenusState()){
				throw new IllegalStateException("Impossible d'aller a l'etat WAITING_FOR_MENUS_AND_UPDATES_STATE");
			}
			
			this.sessionOpened = speaker.openSession(sessionFormalism);
			this.idSession = sessionOpened.getSessionId();
			
			sessionController.notifyEndOpenSession(this);
		}
		else {
			// throw new Exception();
		}
		
	}

	public void openSession(String sessionDate, String sessionFormalism, String sessionName) throws CException {
		// TODO Auto-generated method stub
		
	}

	public void closeSession()  throws CException{
		if (sessionController.closeSession(this)){
			if (!automate.goToWaitingForCloseSessionState()){
				throw new IllegalStateException("Impossible d'aller a l'etat WAITING_FOR_CLOSE_SESSION_STATE");
			}
			
			this.sessionClosed = speaker.closeSession(idSession);
			
			sessionController.notifyEndCloseSession(this,sessionClosed.getSessionId());
			
		}
		else{
			// throw new Exception();
		}
	}
	
	public void changeSession(IApiSession s) throws CException{
		if (sessionController.suspendSession(this) && sessionController.resumeSession(s)){

			this.sessionChanged = speaker.changeSession(s.getIdSession());

			sessionController.notifyEndChangeSession(this,sessionChanged.getSessionId());

		}
	}
	
	public void notifyEndOpenSession() {
		if (!automate.goToIdleState()){
			throw new IllegalStateException("Impossible d'aller vers a l'etat IDLE_STATE");
		}
		AnswerOpenSession answerOpenSession = new AnswerOpenSession(sessionOpened);
		((IOpenSessionObservable) listObservables.get(IObservables.OPEN_SESSION)).notifyObservers(answerOpenSession);
	}

	public void notifyEndSuspendSession() {
		if (!automate.goToSuspendSessionState()){
			throw new IllegalStateException("Impossible d'aller vers a l'etat SUSPEND_SESSION_STATE");
		}
	}

	public void notifyEndResumeSession() {
		if (!automate.goToIdleState()){
			throw new IllegalStateException("Impossible d'aller vers a l'etat IDLE_STATE");
		}
	}
	

	public void notifyEndCloseSession() {
		if (!automate.goToCloseSessionState()){
			throw new IllegalStateException("Impossible d'aller vers a l'etat CLOSE_SESSION_STATE");
		}
		AnswerCloseSession answerCloseSession = new AnswerCloseSession(sessionClosed);
		((ICloseSessionObservable) listObservables.get(IObservables.CLOSE_SESSION)).notifyObservers(answerCloseSession);
	}
	
	public void notifyEndChangeSession() {
		if (!automate.goToSuspendSessionState()){
			throw new IllegalStateException("Impossible d'aller vers a l'etat SUSPEND_SESSION_STATE");
		}
		AnswerChangeSession answerChangeSession = new AnswerChangeSession(sessionChanged);
		((IChangeSessionObservable) listObservables.get(IObservables.CHANGE_SESSION)).notifyObservers(answerChangeSession);
	}

	


	

	public void askForService(String rootName, String menuName, String serviceName) {
		// TODO Auto-generated method stub
		
	}

	public void askForService(String rootName, String menuName,
			String serviceName, String date) {
		// TODO Auto-generated method stub
		
	}
	
	public void invalidModel() {
		// TODO Auto-generated method stub
		
	}
	
	public void sendModel(IModel model) {
		// TODO Auto-generated method stub
		
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
