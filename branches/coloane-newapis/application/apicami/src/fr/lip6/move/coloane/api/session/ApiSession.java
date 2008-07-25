package fr.lip6.move.coloane.api.session;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.ISessionStateMachine;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;
import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;

/**
 * Définition d'une session
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 */
public class ApiSession implements IApiSession {
	/** Le Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

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

	/** Les informations sur la session */
	private ISessionInfo sessionInfo;

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
	public final String getSessionName() {
		return this.sessionName;
	}

	/**
	 * @return l'automate d'états pour la session courante
	 */
	public final ISessionStateMachine getSessionStateMachine() {
		return this.automate;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getIdSession() {
		return this.sessionName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getSessionDate() {
		return this.sessionDate;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getSessionFormalism() {
		return this.sessionFormalism;
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
		return this.sessionInfo;
	}

	/**
	 * {@inheritDoc}
	 */
	public final ISessionInfo openSession(int sessionDate, String sessionFormalism, String sessionName, String interlocutor, int mode) throws ApiException {
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
					throw new ApiException("Error while speaking to the platform: " + ioe.getMessage());
				}

				// On vérifie qu'on est dans un état compatible avec une fermeture de session
				if (!this.automate.setWaitingForCloseSessionState()) {
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
		if (this.sessionControl.resumeSession(this)) {
			synchronized (this) {
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
		if (this.sessionControl.suspendSession(this)) {
			synchronized (this) {
				try {
					speaker.suspendSession();
				} catch (IOException ioe) {
					throw new ApiException("Error while speakig to the platform: " + ioe.getMessage());
				}
				if (!this.automate.setWaitingForSuspendSessionState()) {
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
	 * Actions à entreprendre lors de l'ouverture de la session
	 */
	public final void notifyEndOpenSession() {
		LOGGER.fine("La session " + this.sessionName + "vient d'etre connectee...");
		if (!this.automate.setIdleState()) {
			LOGGER.warning("L'etat de la session " + this.sessionName + "est incompatible avec l'ouverture de session");
			// TODO: Faire quelque chose
		}
	}

	/**
	 * Actions à entreprendre lors de la fermeture de la session
	 */
	public final void notifyEndCloseSession() {
		LOGGER.fine("La session " + this.sessionName + "vient d'etre deconnectee...");
		synchronized (this) {
			this.notify();
		}
		if (!this.automate.closeSessionState()) {
			LOGGER.warning("L'etat de la session " + this.sessionName + "est incompatible avec la deconnexion de session");
			// TODO: Faire quelque chose
		}

	}

	/**
	 * Actions à entreprendre lors de la suspension de la session
	 */
	public final void notifyEndSuspendSession() {
		LOGGER.fine("La session " + this.sessionName + "vient d'etre suspendue...");
		synchronized (this) {
			this.notify();
		}
		if (!this.automate.setSuspendSessionState()) {
			LOGGER.warning("L'etat de la session " + this.sessionName + "est incompatible avec la suspension de session");
			// TODO: Faire quelque chose
		}
	}

	/**
	 * Actions à entreprendre lors de la reprise de la session
	 */
	public final void notifyEndResumeSession() {
		LOGGER.fine("La session " + this.sessionName + "vient d'etre reprise...");
		synchronized (this) {
			this.notify();
		}
		//System.out.println("mon etat apré le notifyEndResumeSession   "+ this.automate.getState());
		if (!this.automate.setIdleState()) {
			LOGGER.warning("L'etat de la session " + this.sessionName + "est incompatible avec la reprise de session");
			// TODO: Faire quelque chose
		}
	}

	/**
	 * Réception des informations sur une nouvelle session
	 * @param o Les informations
	 */
	public final void notifyReceptSessionInfo(ISessionInfo o) {
		this.sessionInfo = o;
		synchronized (this) {
			this.notify();
		}
	}





























	/**
	 *
	 * @param rootName Le nom da la racine du menu qui contient ce service
	 * @param serviceName Le nom du service invoqué
	 * @param options La liste des options active dans le menu
	 * @param model Le modèle sur lequel est invoqué le service
	 * @throws IOException
	 */
	public final void askForService(String rootName, String serviceName, List<String> options, IGraph model) throws IOException {
		this.model = model;
		if (this.sessionControl.askForService(this)) {
			// TODO trouver comment on calcule menuName
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


//	public void sendModel(IModel model) throws IOException {


	//	if (!this.automate.setWaitingForResultState()){
	//		throw new IllegalStateException("j'etais pas en attente de resultat");
	//	}
//	speaker.sendModel(model);
//	}

	public final void invalidModel() {
		if(!this.automate.setWaitingForUpdatesState()){
			throw new IllegalStateException("je peux pas me mettre dans cette etat");
		} else {
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

	public boolean askForService(String rootName, String menuName, String serviceName, List<String> options, IGraph model)
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

	public boolean sendDialogAnswer(IDialogAnswer dialogAnswer)
	throws ApiException {
		// TODO Auto-generated method stub
		return false;
	}

	public void sendModel(IGraph model) throws ApiException {
		// TODO Auto-generated method stub
	}
}
