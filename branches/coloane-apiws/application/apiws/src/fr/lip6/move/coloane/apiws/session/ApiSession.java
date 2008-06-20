package fr.lip6.move.coloane.apiws.session;

import java.util.HashMap;

import fr.lip6.move.coloane.apiws.evenements.AnswerCloseSession;
import fr.lip6.move.coloane.apiws.evenements.AnswerOpenSession;
import fr.lip6.move.coloane.apiws.interfaces.objects.IModel;
import fr.lip6.move.coloane.apiws.interfaces.observables.ICloseSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.observables.IObservables;
import fr.lip6.move.coloane.apiws.interfaces.observables.IOpenSessionObservable;
import fr.lip6.move.coloane.apiws.interfaces.session.IApiSession;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;

public class ApiSession implements IApiSession{

	private String sessionDate;
	
	private String sessionFormalism;
	
	private String sessionName;
	
	private String interlocutor;
	
	private int mode;
	
	private String idSession;
	
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


	public void openSession(String sessionDate, String sessionFormalism, String sessionName, String interlocutor, int mode){
		this.sessionDate = sessionDate;
		this.sessionFormalism = sessionFormalism;
		this.sessionName = sessionName;
		this.interlocutor = interlocutor;
		this.mode = mode;
		
		if (sessionController.openSession(this)){
			
			
			// La session change d'etat : Attend une reponse du wrapper pour l'ouverture de session
			if (!automate.goToWaitingForUpdatesAndMenusState()){
				//throw new IllegalStateException();
			}
			
			// Ouverture d'une session -> Reception de la reponse 
			Session sessionOpened = speaker.openSession(sessionFormalism);

			idSession = sessionOpened.getSessionId();
			
			// Reception de la reponse -> Notification de l'evenement : ouverture se session
			AnswerOpenSession answerOpenSession = new AnswerOpenSession(/* sessionOpened */);
			((IOpenSessionObservable) listObservables.get(IObservables.OPEN_SESSION)).notifyObservers(answerOpenSession);
			
			sessionController.notifyEndOpenSession();
			
		}
		else {
			// throw new Exception();
		}
		
	}

	public void openSession(String sessionDate, String sessionFormalism, String sessionName) {
		// TODO Auto-generated method stub
		
	}

	public boolean suspendSession() {
		return sessionController.suspendSession(this);
	}


	public boolean resumeSession() {
		return sessionController.resumeSession(this);
	}

	public void closeSession() {
		if (sessionController.closeSession(this)){
			
			if (!automate.goToWaitingForCloseSessionState()){
				// throw new IllegalStateException();
			}
			
			// Fermeture d'une session -> Reception de la reponse 
			Session sessionClosed = speaker.closeSession(idSession);

			// Reception de la reponse -> Notification de l'evenement : ouverture se session
			AnswerCloseSession answerCloseSession = new AnswerCloseSession();
			((ICloseSessionObservable) listObservables.get(IObservables.CLOSE_SESSION)).notifyObservers(answerCloseSession);
			
			sessionController.notifyEndCloseSession(this);
			
		}
		else{
			// throw new Exception();
		}
		
	}

	public void invalidModel() {
		// TODO Auto-generated method stub
		
	}


	
	public void askForService(String rootName, String menuName, String serviceName) {
		// TODO Auto-generated method stub
		
	}

	public void askForService(String rootName, String menuName,
			String serviceName, String date) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void sendModel(IModel model) {
		// TODO Auto-generated method stub
		
	}

	public void notifyEndOpenSession() {
		// La session change d'etat : attend menu -> repos
		if (!automate.goToIdleState()){
			//throw new IllegalStateException();
		}
	}
	

	public void notifyEndCloseSession() {
		// La session change d'etat : attend fermeture -> fermeture
		if (!automate.goToCloseSessionState()){
			//throw new IllegalStateException();
		}
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
