package fr.lip6.move.coloane.api.session;

import java.io.IOException;

import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.ISessionInfo;
import fr.lip6.move.coloane.api.interfaces.ISessionStateMachine;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;

/**
 * Définition d'une session
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 */
public class ApiSession implements IApiSession {

	/** La date de la session */
	private String sessionDate;

	/** Le formalisme de la session */
	private String sessionFormalism;

	/** Le nom de la session */
	private String sessionName;

	/** Le contrôleur de sessions */
	private ISessionController sessionControl;

	/** Le speaker */
	private ISpeaker speaker;

	/** L'automate d'état */
	private ISessionStateMachine automate;

	/** Le modèle attaché à la session */
	private IGraph model;

	/**
	 * Constructeur d'une session
	 * @param speaker Le speaker attaché à cette session
	 */
	public ApiSession(ISpeaker speaker) {
		this.sessionDate = null;
		this.sessionFormalism = null;
		this.sessionName = null;
		this.model = null;
		this.sessionControl = SessionController.getInstance();
		this.speaker = speaker;
		this.automate = SessionFactory.getNewSessionStateMachine();
	}

	/**
	 *
	 * @param date
	 * @param formalism
	 * @param name
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public final ISessionInfo openSession(String date, String formalism, String name) throws IOException, InterruptedException {
		this.sessionDate = date;
		this.sessionFormalism = formalism;
		this.sessionName = name;

		synchronized (this) {
			if (this.sessionControl.openSession(this)) {
				this.speaker.openSession(this.sessionName, this.sessionDate, this.sessionFormalism, "FrameKit Environment", 1);
			}

			// On se met en attente de ISessionInfo
			this.wait();
			if (!this.automate.setWaitingForUpdatesAndMenusState()) {
				throw new IllegalStateException("je suis pas dans un etat qui me permette dattendre les menus et updates");
			}
		}
		// TODO : A changer...
		return null;
	}

	/**
	 *
	 * @param rootName
	 * @param menuName
	 * @param serviceName
	 * @param model
	 * @throws IOException
	 */
	public final void askForService(String rootName, String menuName, String serviceName, IGraph model) throws IOException {
		this.model = model;
		if (this.sessionControl.askForService(this)) {

			speaker.askForService(rootName, menuName, serviceName);
			System.out.println(this.automate.getState());
			if (!this.automate.setWaitingForResponseState()){
				throw new IllegalStateException("je doit attendre qque chose de chez FK");
			}
		}
		else {
			throw new IllegalStateException("je peux pas faire demander de service sur cette session");
		}
	}

	/**
	 *
	 * @param rootName
	 * @param menuName
	 * @param serviceName
	 * @param date
	 * @param model
	 * @throws IOException
	 */
	public void askForService(String rootName,String menuName, String serviceName, String date,IGraph model) throws IOException {
		this.model = model;
		if (this.sessionControl.askForService(this)){

			speaker.askForService(rootName, menuName, serviceName, date);

			if (!this.automate.setWaitingForResponseState()) {
				throw new IllegalStateException("je doit attendre qque chose de chez FK");
			}
		}
		else {
			throw new IllegalStateException("je peux pas faire demander de service sur cette session");
		}

	}

	/**
	 * 
	 */
	public final boolean closeSession() {
		if (this.sessionControl.closeSession(this)) {
			synchronized (this) {
				speaker.closeSession(false);

				if (!this.automate.setWaitingForCloseSessionState()){
					throw new IllegalStateException("je suis pas dans un etat qui me permet de me fermer");
				}
				this.wait();
				return true;
			}
		} else {
			throw new IllegalStateException("je peux pas faire close session sur cette session");
		}
	}

	/**
	 *
	 */
	public final boolean resumeSession() {
		if (this.sessionControl.resumeSession(this)){
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


	public final boolean suspendSession() throws InterruptedException, IOException {
		if ( this.sessionControl.suspendSession(this)){
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
		if(!this.automate.closeSessionState()){
			throw new IllegalStateException("j'étais pas en attente dune fermeture de session");

		}

	}



//	public void sendModel(IModel model) throws IOException {


	//	if (!this.automate.setWaitingForResultState()){
	//		throw new IllegalStateException("j'etais pas en attente de resultat");
	//	}
//	speaker.sendModel(model);
//	}

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

	public void notifyReceptSessionInfo(ISessionInfo o) {
		this.sessionInfo = o;
		synchronized(this){
			this.notify();
		}
	}
}
