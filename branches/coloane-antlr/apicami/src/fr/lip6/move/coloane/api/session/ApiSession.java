package fr.lip6.move.coloane.api.session;

import java.io.IOException;

import fr.lip6.move.coloane.api.interfaces.*;

/**
 * cette classe represente une session, elle implemente l'interface IApiSession
 *
 * @author Kahoo & UU
 *
 */
public class ApiSession implements IApiSession {

	/** la date de la session */
	private String sessionDate;

	/** le formalisme de la session */
	private String sessionFormalism;

	/** le nom de la session */
	private String sessionName;

	/** l'outil avec le quel on communique */
	private String interlocutor;

	/** le mode */
	private int mode;

	/** le sessionController */
	private ISessionController sessionCont;

	/** notre speaker */
	private ISpeaker speaker;

	/** notre automate*/
	private ISessionStateMachine automate;
	/**
	 * le constructeur de notre session.
	 */
	public ApiSession(ISessionController se, ISpeaker speaker) {
		this.interlocutor = null;
		this.mode = -1;
		this.sessionDate = null;
		this.sessionFormalism = null;
		this.sessionName = null;
		this.sessionCont = se;
		this.speaker = speaker;
		this.automate = SessionFactory.getNewSessionStateMachine();

	}

	/**
	 * la methode openSession Ã invoquer sur la session.
	 *
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void openSession(String sessionDate, String sessionFormalism,
			String sessionName, String interlocutor, int mode)
			throws IOException, InterruptedException {
		this.interlocutor = interlocutor;
		this.mode = mode;
		this.sessionDate = sessionDate;
		this.sessionFormalism = sessionFormalism;
		this.sessionName = sessionName;
		if (this.sessionCont.openSession(this))
			// TODO lexeption

			this.speaker.openSession(this.sessionName, this.sessionDate,
					this.sessionFormalism, this.interlocutor, this.mode);
			if( !this.automate.setWaitingForUpdatesAndMenusState() ){
		//		throw new InvalidStateException("impossible de passer de l'etat initialState a ");
	}
	}

	/**
	 * la methode openSession Ã invoquer sur la session.
	 *
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void openSession(String sessionDate, String sessionFormalism,
			String sessionName) throws IOException, InterruptedException {
		openSession(sessionDate, sessionFormalism, sessionName,
				"KrameKit Environment", 1);

	}

	public void askForService(String rootName, String serviceName) {

	}

	public void askForService(String rootName, String serviceName, String date) {
		// TODO Auto-generated method stub

	}

	public void closeSession() {
		// TODO Auto-generated method stub

	}

	public boolean resumeSession() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean suspendSession() {
		speaker.suspendSession(this.sessionName);
		return true;
	}


	public ISessionStateMachine getSessionStateMachine() {
      return this.automate;
	}


	public void notifyEndOpenSession() {
     this.automate.setIdleState();

	}





}