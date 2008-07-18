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
	
	/** notre modele*/
	private IModel model;
	/**
	 * le constructeur de notre session.
	 */
	public ApiSession(ISessionController se, ISpeaker speaker) {
		this.interlocutor = null;
		this.mode = -1;
		this.sessionDate = null;
		this.sessionFormalism = null;
		this.sessionName = null;
		this.model=null;
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
System.out.println("session open");
		if (this.sessionCont.openSession(this))
			// TODO lexeption

			this.speaker.openSession(this.sessionName, this.sessionDate,
					this.sessionFormalism, this.interlocutor, this.mode);
			if( !this.automate.setWaitingForUpdatesAndMenusState() ){
		 throw new IllegalStateException("je suis pas dans un etat qui me permette dattendre les menus et updates");
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

	public void askForService(String rootName,String menuName, String serviceName,IModel model) throws IOException {
		this.model = model;
		if (this.sessionCont.askForService(this)){

			   speaker.askForService(rootName, menuName, serviceName);
          System.out.println(this.automate.getState());
		   if (!this.automate.setWaitingForResponseState()){
				throw new IllegalStateException("je doit attendre qque chose de chez FK");
		   }
		   }
		   else {
			   throw new IllegalStateException("je peux pas faire demander de service sur cette session");
		   }
		// System.out.println("askk for service222 " + this.getSessionName());
	}

	public void askForService(String rootName,String menuName, String serviceName, String date,IModel model) throws IOException {
		this.model=model; 
		if (this.sessionCont.askForService(this)){

			   speaker.askForService(rootName, menuName, serviceName,date);

		   if (!this.automate.setWaitingForResponseState()){
				throw new IllegalStateException("je doit attendre qque chose de chez FK");
		   }
		   }
		   else {
			   throw new IllegalStateException("je peux pas faire demander de service sur cette session");
		   }
		
	}
	

	public final boolean closeSession() throws IOException, InterruptedException {
	   if (this.sessionCont.closeSession(this)){
		   synchronized(this){
	   
		   speaker.closeSession(false);

	   if (!this.automate.setWaitingForCloseSessionState()){
			throw new IllegalStateException("je suis pas dans un etat qui me permet de me fermer");
	   }
	   this.wait();
	   return true;
		   }
	   }
	   else {
		   throw new IllegalStateException("je peux pas faire close session sur cette session");
	   }
	}

	public boolean resumeSession() throws IOException, InterruptedException {
        if (this.sessionCont.resumeSession(this)){
     //  System.out.println("je  resume la session " + this.getSessionName());
        	synchronized(this){
    			speaker.resumeSession(this.getSessionName());
    			 if (!this.automate.setWaitingForResumeSessionState()){
    				throw new IllegalStateException("je suis pas dans un etat qui me permet de reprendre mon execution");
    			 }
    			  this.wait();
    		}
    			// System.out.println("letat de la session a resumer  " + this.automate.getState());
    		return true;
    		}
    		return false;
    	}


	public boolean suspendSession() throws InterruptedException, IOException {
		if ( this.sessionCont.suspendSession(this)){
		synchronized(this){
			speaker.suspendSession();
			 if (!this.automate.setWaitingForSuspendSessionState()){
				throw new IllegalStateException("je suis pas dans un etat qui me permet de me suspendre");
			 }
		    this.wait();
		}
		return true;
		}
		return false;
	}


	public ISessionStateMachine getSessionStateMachine() {
      return this.automate;
	}


	public void notifyEndOpenSession() {
		System.out.println("jai recu un notifyEndOpenSession");
      if (! this.automate.setIdleState()){
    	  throw new IllegalStateException("je suis pas dans un etat qui me permette detre idle");
      }

    //   System.out.println("jai recu un notifyEndOpenSession  " + this.automate.getState());
	}


	public void notifyEndSuspendSession() {
		System.out.println("jai recu un notifyEndSuspendSession");
		synchronized(this){
        this.notify();
		}
		if (!this.automate.setSuspendSessionState()){
		throw new IllegalStateException("je suis pas en attente dune suspension de session");
		}

	}


	public String getSessionName() {

		return this.sessionName;
	}


	public void notifyEndResumeSession(String nameSession) {
		System.out.println("jai recu un notifyEndResumeSession");
		synchronized(this){
	        this.notify();
			}
		//System.out.println("mon etat apré le notifyEndResumeSession   "+ this.automate.getState());
		if (!this.automate.setIdleState()){
		throw new IllegalStateException("etat pas coherent");
		}


	}


	public void notifyEndCloseSession() {
		System.out.println("jai recu un notifyEndCloseSession");
		synchronized(this){
	        this.notify();
		}
	if(!this.automate.CloseSessionState()){
		throw new IllegalStateException("j'étais pas en attente dune fermeture de session");

	}

	}



//	public void sendModel(IModel model) throws IOException {

	
	//	if (!this.automate.setWaitingForResultState()){
	//		throw new IllegalStateException("j'etais pas en attente de resultat");
	//	}
//		speaker.sendModel(model);
//}

	public void invalidModel() throws IOException {
		 if(!this.automate.setWaitingForUpdatesState()){
			 throw new IllegalStateException("je peux pas me mettre dans cette etat"); 
		 }
		 else{
			 speaker.invalidModel();
		 }
		
	}

	public void notifyWaitingForModel() throws IOException {
		
		if(!this.automate.setWaitingForModelState())
	throw new IllegalStateException("j'etais pas en attente de model");
		
		speaker.sendModel(this.model);
	}

	public void notifyWaitingForResult() {
		System.out.println(automate.getState());
		if(!this.automate.setWaitingForResultState())
	 throw new IllegalStateException("j'etais pas en attente de reponse");
	}

	public void notifyEndResult() {
	 
		if(!this.automate.setIdleState()){
			 throw new IllegalStateException("je peux pas me mettre dans cet etat");
			}
	}

	public boolean sendDilaogAnswer(IDialogAnswer dialogAnswer) throws IOException {
		speaker.sendDialogResponse(dialogAnswer);
		return true;
	}
	
	

}