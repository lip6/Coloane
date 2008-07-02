package fr.lip6.move.coloane.apiws.session;

import fr.lip6.move.coloane.apiws.exceptions.ApiSessionException;
import fr.lip6.move.coloane.apiws.exceptions.WrapperException;
import fr.lip6.move.coloane.apiws.interfaces.objects.IModel;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IMMenu;
import fr.lip6.move.coloane.apiws.interfaces.session.IApiSession;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;
import fr.lip6.move.coloane.apiws.objects.menu.MMenuImpl;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;

public class ApiSession implements IApiSession{

	private String sessionDate;
	
	private String sessionFormalism;
	
	private String sessionName;
	
	private String interlocutor;
	
	private int mode;
	
	private String idSession;
	
	private Session sessionOpened;
	
	private Session sessionToResumeAfterClose;
	
	private Session sessionToResumeAfterChange;
	
	private ISessionController sessionController;
	
	private ISpeaker speaker;
	
	private ISessionStateMachine automate;
	
	private IMMenu menus;
	
	
	public ApiSession(ISessionController sessionController,ISpeaker speaker){
		this.sessionDate = null;
		this.sessionFormalism = null;
		this.sessionName = null;
		this.interlocutor = null;
		this.mode = -1;
		this.idSession = null;
		this.sessionOpened = null;
		this.sessionToResumeAfterClose = null;
		this.sessionToResumeAfterChange = null;
		this.sessionController = sessionController;
		this.speaker = speaker;
		this.automate = SessionFactory.getNewSessionStateMachine();
		this.menus = null;
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
	
	public IMMenu getMenus(){
		return menus;
	}
	
	public void updateSession(Session s){
		// TODO Auto-generated method stub
		this.menus = new MMenuImpl(s.getMenu());
	}
	
	public void openSession(String sessionDate, String sessionFormalism, String sessionName, String interlocutor, int mode) throws WrapperException, ApiSessionException{
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
			this.menus = new MMenuImpl(sessionOpened.getMenu());
			
			sessionController.notifyEndOpenSession(this);
			
		}
		
	}

	public void openSession(String sessionDate, String sessionFormalism, String sessionName) throws WrapperException , ApiSessionException{
		// TODO Auto-generated method stub
		openSession( sessionDate,  sessionFormalism, sessionName,"FrameKit Environment", 1);
	}

	public void closeSession()  throws WrapperException, ApiSessionException{
		if (sessionController.closeSession(this)){
			if (!automate.goToWaitingForCloseSessionState()){
				throw new IllegalStateException("Impossible d'aller a l'etat WAITING_FOR_CLOSE_SESSION_STATE");
			}
			
			this.sessionToResumeAfterClose = speaker.closeSession(idSession);
			
			sessionController.notifyEndCloseSession(this,sessionToResumeAfterClose);
			
		}
	}
	
	public void changeSession(IApiSession s) throws WrapperException, ApiSessionException{
		if (sessionController.suspendSession(this) && sessionController.resumeSession(s)){

			this.sessionToResumeAfterChange = speaker.changeSession(s.getIdSession());

			sessionController.notifyEndChangeSession(this,sessionToResumeAfterChange);

		}
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

}
