package fr.lip6.move.coloane.apicami.session;

import fr.lip6.move.coloane.apicami.ApiConnection;
import fr.lip6.move.coloane.apicami.interfaces.ISessionController;
import fr.lip6.move.coloane.apicami.interfaces.ISessionStateMachine;
import fr.lip6.move.coloane.apicami.interfaces.ISpeaker;
import fr.lip6.move.coloane.apicami.observables.BrutalInterruptObservable;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;
import fr.lip6.move.coloane.interfaces.objects.service.IService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private IGraph inputModel;

	/** Le modèle attaché à la session */
	private IGraph outputModel;

	/** Les informations sur la session */
	private ISessionInfo sessionInfo;

	/** Modele sale*/
	private boolean mustSendModel = true;

	/** Liste des boites de dialogues reçues */
	private Map<Integer, IDialog> dialogs;

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
		this.inputModel = null;
		this.sessionControl = SessionController.getInstance();
		this.speaker = speaker;
		this.stateMachine = new SessionStateMachine();
		this.dialogs = new HashMap<Integer, IDialog>();
	}

	/**
	 * @return Retourne l'automate d'états de la session
	 */
	public final ISessionStateMachine getStateMachine() {
		return this.stateMachine;
	}

	/**
	 * {@inheritDoc}
	 */
	public final ISessionInfo open(int date, String formalism, String name) throws ApiException {
		this.date = date;
		this.formalism = formalism;
		this.name = name.replaceAll("/", "_");

		// La session active avant l'ouverture de celle-là
		ApiSession activeSession = null;

		// Est-ce que la session n'est pas deja ouverte ?
		if (this.stateMachine.getCurrentState() != ISessionStateMachine.INITIAL_STATE) {
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
				this.crashRecover("Error while speaking to the platform " + ioe.getMessage());
			}

			// Attente de la fin de l'ouverture de session
			try {
				this.wait();
			} catch (InterruptedException ioe) {
				this.crashRecover("Error while speaking to the platform " + ioe.getMessage());
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

		// On ne peut pas fermer une session qui l'est déjà
		if (this.stateMachine.getCurrentState() == ISessionStateMachine.INITIAL_STATE) {
			LOGGER.fine("La session " + this.name + " est deja fermee");
			return;
		}

		synchronized (this) {
			LOGGER.fine("Demande de fermeture de la session " + this.name);
			// On essaye de faire passer cette session en session active
			this.resume();

			// On vérifie l'état de la session. Si elle n'est pas IDLE... On abandonne
			if (this.stateMachine.getCurrentState() != ISessionStateMachine.IDLE_STATE) {
				LOGGER.warning("Impossible de fermer la session " + this.name + " (Etat : " + this.stateMachine.getCurrentState() + ")");
				throw new ApiException("Cannot close this session.");
			}

			// Envoi des commandes CAMI
			try {
				speaker.closeSession(false);
			} catch (IOException ioe) {
				// Il faut reprendre la session précédemment suspendue (si nécessaire)
				if (activeSession != null) {
					activeSession.resume();
					this.sessionControl.setActiveSession(activeSession);
				}
				this.crashRecover("Error while speaking to the platform " + ioe.getMessage());
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
				if (activeSession.stateMachine.getCurrentState() == ISessionStateMachine.IDLE_STATE) {
					LOGGER.finest("Demande de la suspension de " + activeSession.getName());
					activeSession.suspend();

				// Sinon l'ouverture n'est pas autorisée
				} else {
					LOGGER.warning("Impossible de suspendre la session active " + activeSession.getName() + " (Etat : " + activeSession.getStateMachine().getCurrentState() + ")");
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
				speaker.resumeSession(this.getName());
			} catch (IOException ioe) {
				// Il faut reprendre la session précédemment suspendue (si nécessaire)
				if (activeSession != null) {
					activeSession.resume();
					this.sessionControl.setActiveSession(activeSession);
				}
				this.crashRecover("Error while speaking to the platform " + ioe.getMessage());
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
		if (this.stateMachine.getCurrentState() != ISessionStateMachine.IDLE_STATE) {
			LOGGER.warning("Impossible de suspendre la session " + this.name + " (Etat : " + this.stateMachine.getCurrentState() + ")");
			throw new ApiException("Cannot suspend this session.");
		}

		if (this.equals(this.sessionControl.getActiveSession())) {
			synchronized (this) {
				try {
					// Dialogue CAMI pour suspendre la session
					speaker.suspendSession();
				} catch (IOException ioe) {
					this.crashRecover("Error while speaking to the platform " + ioe.getMessage());
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
			throw new ApiException("Cannot suspend the session... It is not active (active one is " + this.sessionControl.getActiveSession().getName());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void askForService(IService service, List<String> options, List<IElement> objects, List<String> texts, IGraph inputModel, IGraph outputModel) throws ApiException {
		this.outputModel = null;
		this.inputModel = inputModel;
		if (mustSendModel & (this.stateMachine.getCurrentState() == ISessionStateMachine.IDLE_STATE)) {
			try {
				this.stateMachine.setBusy();
				speaker.sendDate(inputModel.getDate());
			} catch (IOException ioe) {
				this.crashRecover("Error while speaking to the platform " + ioe.getMessage());
			}
		}

		// Maintenant que les updates ont été envoyé... On réinitialise le booleen
		this.mustSendModel = false;

		synchronized (this) {
			LOGGER.fine("Demande de service sur la session " + this.name);
			// On essaye de faire passer cette session en session active
			this.resume();

			try {
				if (outputModel != null) { this.outputModel = outputModel; }
				speaker.askForService(service.getRoot(), service.getParent(), service.getName());
			} catch (IOException ioe) {
				this.crashRecover("Error while speaking to the platform " + ioe.getMessage());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void invalidModel() throws ApiException {
		this.mustSendModel = true;
		if (this.stateMachine.getCurrentState() != ISessionStateMachine.IDLE_STATE) {
			LOGGER.warning("Impossible d'invalider le modele de la session " + this.name + " (Etat : " + this.stateMachine.getCurrentState() + ")");
			throw new ApiException("Cannot invalidate the model.");
		}

		try {
			this.stateMachine.setBusy();
			speaker.invalidModel();
		} catch (IOException ioe) {
			this.crashRecover("Error while speaking to the platform " + ioe.getMessage());
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
	 * Que faire en cas de problème ? Tout déconnecter...
	 * @param message Le message à transmettre à Coloane
	 */
	private void crashRecover(String message) {
		((BrutalInterruptObservable) this.apiConnection.getObservablesList().get("IBrutalInterrupt")).notifyObservers(message);
	}

	/**
	 * Actions à entreprendre lors de l'ouverture de session
	 */
	public final void notifyEndOpenSession() {
		LOGGER.fine("La session " + this.name + " vient d'etre connectee...");
		this.stateMachine.setIdle();
	}

	/**
	 * Actions à entreprendre lors de la fermeture de la session
	 */
	public final void notifyEndCloseSession() {
		LOGGER.fine("La session " + this.name + " vient d'etre deconnectee...");
		synchronized (this) { this.notify(); }
		this.stateMachine.setInitial();
	}

	/**
	 * Actions à entreprendre lors de la suspension de la session
	 */
	public final void notifyEndSuspendSession() {
		LOGGER.fine("La session " + this.name + " vient d'etre suspendue...");
		synchronized (this) { this.notify(); }
		this.stateMachine.setSuspend();
	}

	/**
	 * Actions à entreprendre lors de la reprise de la session
	 */
	public final void notifyEndResumeSession() {
		LOGGER.fine("La session " + this.name + " vient d'etre reprise...");
		synchronized (this) { this.notify(); }
		this.stateMachine.setIdle();
	}

	/**
	 * Réception des informations sur une nouvelle session
	 * @param sessionInfos Les informations de session
	 */
	public final void notifyReceptSessionInfo(ISessionInfo sessionInfos) {
		LOGGER.finest("Reception des informations de session");
		this.sessionInfo = sessionInfos;
		synchronized (this) { this.notify(); }
		this.stateMachine.setIdle();
	}

	/**
	 * Actions à entreprendre lors de la fin de l'invalidation du modele de la session
	 */
	public final void notifyEndInvalidatedSession() {
		LOGGER.fine("La session " + this.name + " vient d'etre invalidee...");
		this.stateMachine.setIdle();
	}

	/**
	 * Actions à entreprendre lors de la réception des premiers résultats
	 */
	public final void notifyWaitingForResult() {
		LOGGER.fine("La session " + this.name + " a commence a recevoir des resultats");
	}

	/**
	 * Actions à entreprendre lors de la notification de l'attente d'un modèle<br>
	 * Envoi du modèle et changements des états de la session
	 */
	public final void notifyWaitingForModel() {
		LOGGER.fine("La session " + this.name + " doit envoyer son modele");
		if (this.stateMachine.getCurrentState() != ISessionStateMachine.BUSY_STATE) {
			LOGGER.warning("Impossible d'envoyer le modele de la session " + this.name + " (Etat : " + this.stateMachine.getCurrentState() + ")");
			// TODO : Break ?
		}

		try {
			speaker.sendModel(this.inputModel);
		} catch (IOException ioe) {
			this.crashRecover("Error while speaking to the platform " + ioe.getMessage());
		}
	}

	/**
	 * Actions à entreprendre lors de la réception de tous les résultats
	 */
	public final void notifyEndResult() {
		LOGGER.fine("La session " + this.name + " a terminee de recevoir les resultats");
		this.stateMachine.setIdle();
	}

	/**
	 * Stocakge de la description d'une boite de dialogue pour pouvoir s'y référer plus tard
	 * @param dialog La boite de dialogue
	 */
	public final void storeDialog(IDialog dialog) {
		LOGGER.finer("Enregistrement d'une boite de dialogue " + dialog.getId());
		this.dialogs.put(dialog.getId(), dialog);
	}

	/**
	 * Récupération des information concernant une boite de dialogue
	 * @param id L'identifiat dans le boite de dialogue
	 * @return La boite de dialogue si elle existe ou <code>null</code> sinon.
	 */
	public final IDialog getDialog(int id) {
		LOGGER.finer("Demande d'informations sur une boite de dialogue " + id);
		return this.dialogs.get(id);
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
	 * @return Le modèle qui peut être reçu en retour de service
	 */
	public final IGraph getOutputModel() {
		return this.outputModel;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void stopService() {
		// Envoi des commandes CAMI
		try {
			speaker.closeSession(false);
		} catch (IOException ioe) {
			this.crashRecover("Error while speaking to the platform " + ioe.getMessage());
		}
	}
}
