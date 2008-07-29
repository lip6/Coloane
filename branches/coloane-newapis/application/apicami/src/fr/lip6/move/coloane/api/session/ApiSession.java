package fr.lip6.move.coloane.api.session;

import fr.lip6.move.coloane.api.ApiConnection;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.ISessionStateMachine;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;
import fr.lip6.move.coloane.api.observables.BrutalInterruptObservable;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;
import fr.lip6.move.coloane.interfaces.objects.service.IService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Définition d'une session
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 * @author Jean-Baptiste Voron
 */
public class ApiSession implements IApiSession {
	/** Le Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

	/** Le gestionnaire de connexion */
	private ApiConnection apiConnection;

	/** La date de la session */
	private int date;

	/** Le formalisme de la session */
	private String formalism;

	/** Le nom de la session */
	private String name;

	/** Le contrôleur de sessions */
	private ISessionController sessionControl;

	/** Le speaker */
	private ISpeaker speaker;

	/** L'automate d'état */
	private ISessionStateMachine stateMachine;

	/** Le modèle attaché à la session */
	private IGraph model;

	/** Les informations sur la session */
	private ISessionInfo sessionInfo;

	/** modele sale*/
	private boolean mustSendModel = false;

	/**
	 * Constructeur d'une session
	 * @param apiConnection Le gestionnaire de la connexion
	 * @param speaker Le speaker attaché à cette session
	 */
	public ApiSession(ApiConnection apiConnection, ISpeaker speaker) {
		this.apiConnection = apiConnection;
		this.date = 0;
		this.formalism = null;
		this.name = null;
		this.model = null;
		this.sessionControl = SessionController.getInstance();
		this.speaker = speaker;
		this.stateMachine = new SessionStateMachine();
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getSessionName() {
		return this.name;
	}

	/**
	 * @return l'automate d'états pour la session courante
	 */
	public final ISessionStateMachine getSessionStateMachine() {
		return this.stateMachine;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getIdSession() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getSessionDate() {
		return this.date;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getSessionFormalism() {
		return this.formalism;
	}

	/**
	 * {@inheritDoc}
	 */
	public final ISessionInfo open(int date, String formalism, String name) throws ApiException {
		this.date = date;
		this.formalism = formalism;
		this.name = name;

		// La session active avant l'ouverture de celle-là
		ApiSession activeSession = null;

		// Contrôles de rigueur

		// Est-ce que la session n'est pas deja ouverte ?
		if (this.getSessionStateMachine().getState() != ISessionStateMachine.INITIAL_STATE) {
			LOGGER.fine("La session " + this.name + " est deja ouverte");
			return this.sessionInfo;
		}

		// TODO : Est-ce qu'un session homonyme n'existe pas déjà ?

		synchronized (this) {
			LOGGER.fine("Demande d'ouverture de session: " + this.name);
			// On essaye de faire passer cette session en session active
			this.resume();

			// On met a jour le contrôleur de sessions
			this.sessionControl.addSession(this);
			this.sessionControl.setActiveSession(this);

			// Dialogue avec la plate-forme
			try {
				this.speaker.openSession(this.name, this.date, this.formalism, "FrameKit Environment", 1);
			} catch (IOException ioe) {
				// Il faut reprendre la session précédemment suspendue (si nécessaire)
				if (activeSession != null) {
					activeSession.resume();
					this.sessionControl.setActiveSession(activeSession);
				}
				throw new ApiException("Error while speaking to the platform: " + ioe.getMessage());
			}

			// Attente de la fin de l'ouverture de session
			try {
				this.wait();
			} catch (InterruptedException e) {
				throw new ApiException("Interrupted while openning session");
			}

			// On vérifie qu'on est dans un état compatible avec unne ouverture de session
			if (!this.stateMachine.setWaitingForUpdatesAndMenusState()) {
				throw new ApiException("Session cannot be opened correctly. It cannot receive service menus.");
			}
		}
		return this.sessionInfo;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void close() throws ApiException {
		// La session active avant l'ouverture de celle-là
		ApiSession activeSession = null;

		if (this.getSessionStateMachine().getState() == ISessionStateMachine.CLOSE_SESSION_STATE) {
			LOGGER.fine("La session " + this.name + " est deja fermee");
			return;
		}

		synchronized (this) {
			LOGGER.fine("Demande de fermeture de la session " + this.name);
			// On essaye de faire passer cette session en session active
			this.resume();

			// Envoi des commandes CAMI
			try {
				speaker.closeSession(false);
			} catch (IOException ioe) {
				// Il faut reprendre la session précédemment suspendue (si nécessaire)
				if (activeSession != null) {
					activeSession.resume();
					this.sessionControl.setActiveSession(activeSession);
				}
				throw new ApiException("Error while speaking to the platform: " + ioe.getMessage());
			}

			// On vérifie qu'on est dans un état compatible avec une fermeture de session
			if (!this.stateMachine.setWaitingForCloseSessionState()) {
				throw new ApiException("The session cannot be closed");
			}

			// Attente de la fermeture de session
			try {
				this.wait();
			} catch (InterruptedException e) {
				throw new ApiException("Interrupted while closing session");
			}

			// Suppression de la liste des sessions contrôlées
			this.sessionControl.removeSession(this);
			LOGGER.finer("La session vient d'être supprimee de la liste des sessions controlees");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void resume() throws ApiException {
		// La session active avant l'ouverture de celle-là
		ApiSession activeSession = null;

		synchronized (this) {
			// Pas de session active... Il faut reprendre la session avant de la fermer
			if (sessionControl.getActiveSession() == null) {
				LOGGER.warning("Pas de session active...");

				// Si la session est déjà active
			} else if (this.equals(this.sessionControl.getActiveSession())) {
				LOGGER.finest("La session est deja active");
				return;

				// Sinon : Une autre session est déjà active
			} else {
				activeSession = this.sessionControl.getActiveSession();
				LOGGER.finest("La session " + activeSession.getName() + " est deja active");

				// Si la session active ne fait rien... alors on la suspend et on prend sa place
				if (activeSession.getSessionStateMachine().getState() == ISessionStateMachine.IDLE_STATE) {
					LOGGER.finest("Demande de la suspension de " + activeSession.getSessionName());
					activeSession.suspend();

					// Sinon l'ouverture n'est pas autorisée
				} else {
					LOGGER.warning("Impossible de suspendre la session active " + activeSession.getSessionName() + " (Etat : " + activeSession.getSessionStateMachine().getState() + ")");
					throw new ApiException("Cannot open this session. Another one is under execution...");
				}
			}

			// Si la session n'est pas encore enregistrée... on retourne
			// Sinon la session active est modifiée
			if (!this.sessionControl.setActiveSession(this)) {
				return;
			}

			// Dialogue en CAMI
			try {
				speaker.resumeSession(this.getSessionName());
			} catch (IOException ioe) {
				// Il faut reprendre la session précédemment suspendue (si nécessaire)
				if (activeSession != null) {
					activeSession.resume();
					this.sessionControl.setActiveSession(activeSession);
				}
				throw new ApiException("Error while speaking to the platform: " + ioe.getMessage());
			}
			if (!this.stateMachine.setWaitingForResumeSessionState()) {
				throw new ApiException("The session " + this.name + " cannot be resumed");
			}

			// Attente de la reprise effective
			try {
				this.wait();
			} catch (InterruptedException e) {
				throw new ApiException("Interrupted while resuming session");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void suspend() throws ApiException {
		if (this.equals(this.sessionControl.getActiveSession())) {
			synchronized (this) {
				// Dialogue CAMI pour suspendra la session
				try {
					speaker.suspendSession();
				} catch (IOException ioe) {
					throw new ApiException("Error while speakig to the platform: " + ioe.getMessage());
				}

				// Vérification et Positionnement du nouvel état
				if (!this.stateMachine.setWaitingForSuspendSessionState()) {
					throw new IllegalStateException("The session cannot be suspended");
				}

				// Attente de la suspension effective
				try {
					this.wait();
				} catch (InterruptedException e) {
					throw new ApiException("Interrupted while suspending session");
				}
			}
		} else {
			LOGGER.warning("Impossible de suspendre la session " + this.name + ". Elle n'est pas active");
			throw new ApiException("Cannot suspend the session... It is not active (active one is " + this.sessionControl.getActiveSession().getSessionName());
		}
	}

	/**
	 * Actions à entreprendre lors de l'ouverture de la session
	 */
	public final void notifyEndOpenSession() {
		LOGGER.fine("La session " + this.name + " vient d'etre connectee...");
		if (!this.stateMachine.setIdleState()) {
			LOGGER.warning("L'etat de la session " + this.name + " est incompatible avec l'ouverture de session");
			// TODO: Faire quelque chose
		}
	}

	/**
	 * Actions à entreprendre lors de la fermeture de la session
	 */
	public final void notifyEndCloseSession() {
		LOGGER.fine("La session " + this.name + " vient d'etre deconnectee...");
		synchronized (this) {
			this.notify();
		}
		if (!this.stateMachine.closeSessionState()) {
			LOGGER.warning("L'etat de la session " + this.name + " est incompatible avec la deconnexion de session");
			// TODO: Faire quelque chose
		}

	}

	/**
	 * Actions à entreprendre lors de la suspension de la session
	 */
	public final void notifyEndSuspendSession() {
		LOGGER.fine("La session " + this.name + " vient d'etre suspendue...");
		synchronized (this) {
			this.notify();
		}
		if (!this.stateMachine.setSuspendSessionState()) {
			LOGGER.warning("L'etat de la session " + this.name + " est incompatible avec la suspension de session");
			// TODO: Faire quelque chose
		}
	}

	/**
	 * Actions à entreprendre lors de la reprise de la session
	 */
	public final void notifyEndResumeSession() {
		LOGGER.fine("La session " + this.name + " vient d'etre reprise...");
		synchronized (this) {
			this.notify();
		}
		//System.out.println("mon etat apré le notifyEndResumeSession   "+ this.automate.getState());
		if (!this.stateMachine.setIdleState()) {
			LOGGER.warning("L'etat de la session " + this.name + " est incompatible avec la reprise de session");
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
	 * {@inheritDoc}
	 */
	public final void askForService(IService service, List<String> options, IGraph model) throws ApiException {
		this.model = model;
		if ((mustSendModel) & (this.stateMachine.getState() == ISessionStateMachine.MODELE_SALE_STATE)) {
			try {
				speaker.sendDate(model.getDate());
			} catch (IOException ioe) {
				throw new ApiException("Error while speaking to the platform: " + ioe.getMessage());
			}
		}

		// Maintenant que les update ont été envoyé... On réinitialise le booleen
		this.mustSendModel = false;

		synchronized (this) {
			LOGGER.fine("Demande de service sur la session " + this.name);
			// On essaye de faire passer cette session en session active
			this.resume();

			try {
				speaker.askForService(service.getRoot(), service.getParent(), service.getName());
			} catch (IOException ioe) {
				throw new ApiException("Error while speaking to the platform: " + ioe.getMessage());
			}

			if (!this.stateMachine.setWaitingForResultState()) {
				throw new IllegalStateException("je doit attendre qque chose de chez FK");
			}
		}
	}


	/**
	 * {@inheritDoc}
	 */
	public final void invalidModel() {
		this.mustSendModel = true;
		if (!this.stateMachine.setWaitingForUpdatesState()) {
			throw new IllegalStateException("je peux pas me mettre dans cette etat");
		} else {
			try {
				speaker.invalidModel();
			} catch (IOException ioe) {
				((BrutalInterruptObservable) this.apiConnection.getObservablesList().get("IBrutalInterrupt")).notifyObservers("Error while speaking to the platform: " + ioe.getMessage());
			}
		}
	}








	/**
	 * {@inheritDoc}
	 */
	public void notifyWaitingForModel() throws IOException {
		if(!this.stateMachine.setWaitingForModelState())
			throw new IllegalStateException("j'etais pas en attente de model");

		speaker.sendModel(this.model);
		// pas trés utile car on remonte pas par observable la demande de modele 
		if(!this.stateMachine.setWaitingForResultState())
			throw new IllegalStateException("j'etais pas en attente de model");
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyWaitingForResult() {
		System.out.println(stateMachine.getState());
		if(!this.stateMachine.setWaitingForResultState())
			throw new IllegalStateException("j'etais pas en attente de reponse");
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyEndResult() {
		if(!this.stateMachine.setIdleState()){
			throw new IllegalStateException("je peux pas me mettre dans cet etat");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean sendDialogAnswer(IDialogAnswer dialogAnswer) throws ApiException {
		try {
			speaker.sendDialogResponse(dialogAnswer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void sendModel(IGraph model) throws ApiException {
		try {
			speaker.sendModel(model);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getDate() {
		return this.date;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getFormalism() {
		return this.formalism;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getId() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setNewGraph(IGraph newGraph) {
		LOGGER.fine("Le core envoie un nouveau graph");
	}

	/**
	 * fin des updates aprés une invalidation de modele
	 */
	public final void notifyEndUpdates() {
		this.stateMachine.setModeleSaleState();
	}
}
