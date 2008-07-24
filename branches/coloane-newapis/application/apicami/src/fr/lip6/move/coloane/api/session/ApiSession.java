package fr.lip6.move.coloane.api.session;

import java.io.IOException;
import java.util.List;

import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.ISessionStateMachine;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
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
	private int sessionDate;

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
		this.sessionDate = 0;
		this.sessionFormalism = null;
		this.sessionName = null;
		this.model = null;
		this.sessionControl = SessionController.getInstance();
		this.speaker = speaker;
		this.automate = new SessionStateMachine();
	}

	/**
	 * {@inheritDoc}
	 */
	public final ISessionInfo openSession(int date, String formalism, String name) throws ApiException {
		this.sessionDate = date;
		this.sessionFormalism = formalism;
		this.sessionName = name;

		synchronized (this) {
			if (this.sessionControl.openSession(this)) {
				try {
					this.speaker.openSession(this.sessionName, this.sessionDate, this.sessionFormalism, "FrameKit Environment", 1);
				} catch (IOException ioe) {
					throw new ApiException("Error while speakig to the platform: " + ioe.getMessage());
				}
			}

			// Attente de la fin de l'ouverture de session
			try {
				this.wait();
			} catch (InterruptedException e) {
				throw new ApiException("Interrupted while openning session");
			}

			// On vérifie qu'on est dans un état compatible avec unne ouverture de session
			if (!this.automate.setWaitingForUpdatesAndMenusState()) {
				throw new ApiException("Session cannot be opened correctly. It cannot receive service menus.");
			}
		}
		// TODO : A changer...
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ISessionInfo openSession(int sessionDate, String sessionFormalism, String sessionName, String interlocutor, int mode) throws ApiException {
		return openSession(sessionDate, sessionFormalism, sessionName);
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean closeSession() throws ApiException {
		if (this.sessionControl.closeSession(this)) {
			synchronized (this) {
				try {
					speaker.closeSession(false);
				} catch (IOException ioe) {
					throw new ApiException("Error while speakig to the platform: " + ioe.getMessage());
				}
				
				// On vérifie qu'on est dans un état compatible avec une fermeture de session
				if (!this.automate.setWaitingForCloseSessionState()){
					throw new ApiException("The session cannot be closed");
				}

				// Attente de la fermeture de session
				try {
					this.wait();
				} catch (InterruptedException e) {
					throw new ApiException("Interrupted while closing session");
				}

				// Tout est OK
				return true;
			}
		} else {
			throw new ApiException("This session is not active. So it cannot be closed.");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean resumeSession() throws ApiException {
		if (this.sessionControl.resumeSession(this)){
			synchronized(this){
				try {
					speaker.resumeSession(this.getSessionName());
				} catch (IOException ioe) {
					throw new ApiException("Error while speakig to the platform: " + ioe.getMessage());
				}
				if (!this.automate.setWaitingForResumeSessionState()){
					throw new ApiException("The session cannot be resumed");
				}
				
				// Attente de la reprise effective
				try {
					this.wait();
				} catch (InterruptedException e) {
					throw new ApiException("Interrupted while resuming session");
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean suspendSession() throws ApiException {
		if ( this.sessionControl.suspendSession(this)){
			synchronized(this){
				try {
					speaker.suspendSession();
				} catch (IOException ioe) {
					throw new ApiException("Error while speakig to the platform: " + ioe.getMessage());
				}
				if (!this.automate.setWaitingForSuspendSessionState()){
					throw new IllegalStateException("The session cannot be suspended");
				}
				
				// Attente de la suspension effective
				try {
					this.wait();
				} catch (InterruptedException e) {
					throw new ApiException("Interrupted while suspending session");
				}
			}
			return true;
		}
		return false;
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

	public boolean askForService(String rootName, String menuName,
			String serviceName, List<String> options, IGraph model)
	throws ApiException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean askForService(String rootName, String menuName,
			String serviceName, List<String> options, IGraph model, String date)
	throws ApiException {
		// TODO Auto-generated method stub
		return false;
	}

	public String getIdSession() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getInterlocutor() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getMode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getSessionDate() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getSessionFormalism() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean sendDialogAnswer(IDialogAnswer dialogAnswer)
	throws ApiException {
		// TODO Auto-generated method stub
		return false;
	}

	public void sendModel(IGraph model) throws ApiException {
		// TODO Auto-generated method stub

	}
}
