package fr.lip6.move.coloane.apiws.session;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;
import fr.lip6.move.coloane.apiws.objects.api.SessionInfo;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;
import fr.lip6.move.coloane.interfaces.objects.menu.IOption;
import fr.lip6.move.coloane.interfaces.objects.model.IModel;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;

public class ApiSession implements IApiSession {

	private String sessionDate;
	
	private String sessionFormalism;
	
	private String sessionName;
	
	private String interlocutor;
	
	private int mode;
	
	private ISessionController sessionController;
	
	private ISpeaker speaker;
	
	private ISessionStateMachine automate;
	
	private String idSession;
	
	public ApiSession(ISessionController sessionController, ISpeaker speaker){
		// TODO Completer le constructeur de ApiSession
		this.sessionDate = null;
		this.sessionFormalism = null;
		this.sessionName = null;
		this.interlocutor = null;
		this.mode = -1;
		
		this.sessionController = sessionController;
		this.speaker = speaker;
		this.automate = SessionFactory.getNewSessionStateMachine();
		
		this.idSession = null;
	}

	public String getInterlocutor() {
		return interlocutor;
	}

	public int getMode() {
		return mode;
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

	public String getIdSession() {
		return idSession;
	}
	
	public ISessionStateMachine getSessionStateMachine(){
		return automate;
	}

	public ISessionInfo openSession(String sessionDate, String sessionFormalism, String sessionName, String interlocutor, int mode) throws ApiException {
		this.sessionDate = sessionDate;
		this.sessionFormalism = sessionFormalism;
		this.sessionName = sessionName;
		this.interlocutor = interlocutor;
		this.mode = mode;
		
		Session sessionOpened = null;
		
		if (sessionController.openSession(this)){
			if (!automate.goToWaitingForUpdatesAndMenusState()){
				throw new ApiException("Impossible d'aller a l'etat WAITING_FOR_MENUS_AND_UPDATES_STATE");
			}
			
			sessionOpened = speaker.openSession(sessionFormalism);
			this.idSession = sessionOpened.getSessionId();
			
			sessionController.notifyEndOpenSession(this);
			
		}
		return new SessionInfo(sessionOpened);
	}

	public ISessionInfo openSession(String sessionDate, String sessionFormalism, String sessionName) throws ApiException {
		// TODO Auto-generated method stub
		return openSession( sessionDate,  sessionFormalism, sessionName,"FrameKit Environment", 1);
	}

	public boolean suspendSession() throws ApiException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean resumeSession() throws ApiException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean closeSession() throws ApiException {
		if (sessionController.closeSession(this)){
			if (!automate.goToWaitingForCloseSessionState()){
				throw new ApiException("Impossible d'aller a l'etat WAITING_FOR_CLOSE_SESSION_STATE");
			}

			Session sessionToResumeAfterClose = speaker.closeSession(idSession);
			sessionController.notifyEndCloseSession(this,sessionToResumeAfterClose);

		}
		return true;
	}
	
	public boolean askForService(String rootName, String menuName, String serviceName, ArrayList<IOption> options, IModel model) throws ApiException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean askForService(String rootName, String menuName, String serviceName, ArrayList<IOption> options, IModel model, String date) throws ApiException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean sendDialogAnswer(IDialogAnswer dialogAnswer) throws ApiException {
		// TODO Auto-generated method stub
		return false;
	}

	public void invalidModel() throws ApiException {
		// TODO Auto-generated method stub
		
	}

	public void sendModel(IModel model) throws ApiException {
		// TODO Auto-generated method stub
		
	}

}
