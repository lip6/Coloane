package fr.lip6.move.coloane.core.motor;

import fr.lip6.move.coloane.core.communications.Com;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.ISessionManager;
import fr.lip6.move.coloane.core.motor.session.Session;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.UserInterface;
import fr.lip6.move.coloane.core.ui.dialogs.AuthenticationInformation;
import fr.lip6.move.coloane.core.ui.dialogs.SaveReceivedModel;
import fr.lip6.move.coloane.core.ui.panels.HistoryView;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptServiceState;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptServiceStateObserver;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.service.IService;

import java.util.logging.Logger;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Gestionnaire de Sessions/Formalismes<br>
 * Le moteur est charge de faire le lien entre le module com et l'interface graphique<br>
 * Il doit etre tenu au courant des changements de sessions.
 */
public final class Motor {
	public static final String SERVICE_JOB = "Ask for service"; //$NON-NLS-1$
	public static final String OPEN_SESSION_JOB = "Open session"; //$NON-NLS-1$

	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Le gestionnaire de formalismes */
	private static FormalismManager formalismManager;

	/** Le gestionnaire de session */
	private static ISessionManager sessionManager;

	/** L'instance du singleton : Motor */
	private static Motor instance;

	/** La fenetre graphique actuelle */
	private IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

	/**
	 * Constructeur du module moteur en prive pour eviter les doublons<br>
	 * Pattern singleton<br>
	 * Instanciation des 2 managers : formalismes et sessions
	 */
	private Motor() {
		formalismManager = FormalismManager.getInstance();
		sessionManager = SessionManager.getInstance();
	}

	/**
	 * Retourne le module moteur
	 * @return Motor Le module moteur
	 */
	public static synchronized Motor getInstance() {
		if (instance == null) { instance = new Motor(); }
		return instance;
	}

	/**
	 * Demande l'authentification aupres de la plateforme
	 * @param authInformation Les informations recues de la boite de dialogue
	 */
	public void authentication(final AuthenticationInformation authInformation) {
		// Verification que toutes les donnees sont fournies
		if (authInformation == null) {
			Coloane.showErrorMsg(Messages.Motor_0);
			return;
		}


		Job job = new Job("Authentication") { //$NON-NLS-1$
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					IConnectionInfo connectionInfo = Com.getInstance().authentication(authInformation, monitor);

					// Affichage dans la zone d'historique
					HistoryView.getInstance().addLine(Messages.Motor_15 + Messages.Motor_3);
					HistoryView.getInstance().addLine("You are connected on " + connectionInfo.getFkName() + " - " + connectionInfo.getFkMajor() + "." + connectionInfo.getFkMinor()); //$NON-NLS-1$ //$NON-NLS-2$
					sessionManager.setAuthenticated(true);
				} catch (ApiException e) {
					return new Status(IStatus.ERROR, "coloane", e.getMessage()); //$NON-NLS-1$
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
				return Status.OK_STATUS;
			}
		};

		job.setPriority(Job.SHORT);
		job.setRule(ResourcesPlugin.getWorkspace().getRoot());
		job.schedule();
	}

	/**
	 * Creation d'une session
	 * @param graph Le modele qui doit etre attache a la session
	 * @param name Le nom de la session
	 * @return boolean Resultat de l'operation
	 */
	public boolean createSession(IGraph graph, String name) {
		// On doit controller si une session ne se nomme deja pas pareil
		if (sessionManager.getSession(name) != null) {
			LOGGER.warning("Une session homonyme existe deja"); //$NON-NLS-1$
			return false;
		}

		LOGGER.fine("Creation de la session : " + name); //$NON-NLS-1$

		// Creation d'une nouvelle session
		sessionManager.newSession(name); // On ajoute la session au moteur de sessions
		sessionManager.getSession(name).setModel(graph); // On associe le modele a la session

		return true;
	}

	/**
	 * Ouvre une connexion pour un modele (connect model)
	 */
	public void openSession() {
		// On verifie que l'utilisateur est authentifie avant de connecter le modele
		if (!sessionManager.isAuthenticated()) {
			LOGGER.warning("Aucune authentification prealable"); //$NON-NLS-1$
			Coloane.showWarningMsg(Messages.Motor_6);
			return;
		}

		final ISession session = sessionManager.getCurrentSession();
		if (session == null) {
			LOGGER.warning("Tentative d'ouverture de session alors qu'il n'y a aucune session courrante"); //$NON-NLS-1$
			return;
		}

		Job job = new Job(OPEN_SESSION_JOB) {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask(Messages.Motor_16, 3);
				try {
					ISessionInfo info = ((Session) session).connect(monitor);
					monitor.subTask(Messages.Motor_17);

					// Affichage dans la zone d'historique
					HistoryView.getInstance().addText(info.getNameService());
				} catch (ApiException e) {
					return new Status(IStatus.ERROR, "coloane", "Connect model failed", e); //$NON-NLS-1$ //$NON-NLS-2$
				}
				return ASYNC_FINISH;
			}
		};

		job.setPriority(Job.SHORT);
		job.setUser(true);
		job.setRule(ResourcesPlugin.getWorkspace().getRoot());
		job.schedule();
	}

	/**
	 * Fermeture de la session courante
	 */
	public void closeSession() {
		final ISession session = sessionManager.getCurrentSession();
		if (session == null) {
			LOGGER.warning("Impossible de fermer une connexion sur un model null"); //$NON-NLS-1$
			return;
		}

		// On verifie que le modele courant est bien connecte avant de le deconnecter
		if (session.getStatus() != ISession.CONNECTED) {
			LOGGER.warning("Le modele courant n'est pas connecte"); //$NON-NLS-1$
			Coloane.showWarningMsg(Messages.Motor_11);
			return;
		}

		Job job = new Job("Close session") { //$NON-NLS-1$
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask(Messages.Motor_18, 3);
				try {
					((Session) session).disconnect(monitor);
				} catch (ApiException e) {
					return new Status(IStatus.ERROR, "coloane", "Close session failed", e); //$NON-NLS-1$ //$NON-NLS-2$
				}
				monitor.subTask(Messages.Motor_19);
				UserInterface.getInstance().cleanMenu();
				UserInterface.getInstance().redrawMenus();
				monitor.worked(1);
				monitor.done();
				return Status.OK_STATUS;
			}
		};

		job.setPriority(Job.SHORT);
		job.setUser(true);
		job.setRule(ResourcesPlugin.getWorkspace().getRoot());
		job.schedule();
	}


	/** {@inheritDoc} */
	public void askForService(final IService service) {
		final ISession session = SessionManager.getInstance().getCurrentSession();
		if (session == null) {
			return;
		}

		final IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (editor.isDirty()) {
			Job save = new Job("save editor") { //$NON-NLS-1$
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					editor.doSave(monitor);
					return Status.OK_STATUS;
				}
			};
			save.setPriority(Job.SHORT);
			save.setRule(ResourcesPlugin.getWorkspace().getRoot());
			save.setSystem(true);
			save.schedule();
		}

		Job job = new Job(SERVICE_JOB) {
			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				monitor.beginTask(service.getName(), IProgressMonitor.UNKNOWN);
				IReceptServiceStateObserver observer = new IReceptServiceStateObserver() {
					public void update(IReceptServiceState e) {
						monitor.subTask(e.getMessage());
					}
				};
				Com.getInstance().setReceptServiceStateObserver(observer);
				try {
					((Session) session).askForService(service);
				} catch (ApiException e) {
					return new Status(IStatus.ERROR, "coloane", "Service " + service.getName() + " failed", e); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
				return ASYNC_FINISH;
			}

			@Override
			protected void canceling() {
				done(new Status(IStatus.ERROR, "coloane", "Service was interrupted"));
			}
		};

		job.setPriority(Job.LONG);
		job.setUser(true);
		job.setRule(ResourcesPlugin.getWorkspace().getRoot());
		job.schedule();
	}


	/**
	 * Detruit la session designee
	 * @param sessionName Le nom de la session a detruire
	 */
	public void deleteSession(final String sessionName) {
		Job job = new Job("Resume session") { //$NON-NLS-1$
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				LOGGER.finer("Destruction de la session : " + sessionName); //$NON-NLS-1$
				try {
					((SessionManager) sessionManager).deleteSession(sessionName);
				} catch (ApiException e) {
					LOGGER.warning("Impossible de fermer la session : " + e); //$NON-NLS-1$
					return new Status(IStatus.ERROR, "coloane", "Close session failed", e); //$NON-NLS-1$ //$NON-NLS-2$
				}
				UserInterface.getInstance().redrawMenus();

				ISession currentSession = sessionManager.getCurrentSession();
				if (currentSession != null) {
					LOGGER.finer("Session courante : " + currentSession.getName()); //$NON-NLS-1$
				} else {
					LOGGER.fine("Pas de session courante"); //$NON-NLS-1$
				}
				return Status.OK_STATUS;
			}
		};
		job.setPriority(Job.SHORT);
		job.setSystem(true);
		job.setRule(ResourcesPlugin.getWorkspace().getRoot());
		job.schedule();
	}

	/**
	 * Creation d'un nouveau modele et affichage dans l'editeur
	 * Cette creation implique la creation d'un nouveau fichier dans le workspace.
	 * Cette action est particulierement utile lors de la generation d'un modele par FK
	 * @param graph le model brut
	 */
	public void setNewModel(IGraph graph) {
		LOGGER.fine("Sauvegarde du modele en provenance de la plateforme"); //$NON-NLS-1$

		// Affichage de la boite de dialogue pour demander la sauvegarde du modele
		Display.getDefault().asyncExec(new SaveReceivedModel(graph, window));
	}

	/**
	 * Suspend la session designee
	 * @param name Le nom de la session a suspendre
	 */
	public void resumeSession(final String name) {
		Job job = new Job("Resume session") { //$NON-NLS-1$
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				if (((SessionManager) sessionManager).resumeSession(name)) {
					LOGGER.finer("OK pour la reprise de session " + name); //$NON-NLS-1$
					UserInterface.getInstance().redrawMenus();

					ISession currentSession = sessionManager.getCurrentSession();
					if (currentSession != null) {
						LOGGER.finer("Session courante : " + currentSession.getName()); //$NON-NLS-1$
					} else {
						LOGGER.fine("Pas de session courante"); //$NON-NLS-1$
					}
				}
				return Status.OK_STATUS;
			}
		};
		job.setPriority(Job.SHORT);
		job.setSystem(true);
		job.setRule(ResourcesPlugin.getWorkspace().getRoot());
		job.schedule();
	}

	/**
	 * Donne la main sur le SessionManager
	 * @return SessionManager Le gestionnaire de sessions
	 */
	public ISessionManager getSessionManager() {
		return sessionManager;
	}

	/**
	 * Donne la main sur le FormalismManager
	 * @return FormalismManager Le gestionnaire de formalismes
	 */
	public FormalismManager getFormalismManager() {
		return formalismManager;
	}

	/**
	 * Demande de deconnexion brutale (initiee par le client)
	 */
	public void breakConnection() {
		LOGGER.fine("Demmande de déconnexion"); //$NON-NLS-1$

		Job job = new Job("Close connection") { //$NON-NLS-1$
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					((SessionManager) sessionManager).disconnectAllSessions();
					Com.getInstance().breakConnection(true);
				} catch (ApiException e) {
					Com.getInstance().breakConnection(false);
				}
				sessionManager.setAuthenticated(false);
				UserInterface.getInstance().redrawMenus();
				return Status.OK_STATUS;
			}
		};

		job.setPriority(Job.SHORT);
		job.setUser(true);
		job.setRule(ResourcesPlugin.getWorkspace().getRoot());
		job.schedule();
	}

	/**
	 * Notifier le changement du modele de la session courrante
	 */
	public void notifyModelChange() {
		Job job = new Job("Invalid model") { //$NON-NLS-1$
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				ISession currentSession = SessionManager.getInstance().getCurrentSession();
				if (currentSession != null) {
					LOGGER.fine("Demande de mise a jour du modele sur la plateforme"); //$NON-NLS-1$
					currentSession.getGraph().modifyDate();
					try {
						((Session) currentSession).invalidModel();
					} catch (ApiException e) {
						return new Status(IStatus.ERROR, "coloane", e.getMessage()); //$NON-NLS-1$
					}
					return Status.OK_STATUS;
				} else {
					return new Status(IStatus.ERROR, "coloane", "Current session not found"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		};

		job.setPriority(Job.SHORT);
		job.setSystem(true);
		job.setRule(ResourcesPlugin.getWorkspace().getRoot());
		job.schedule();
	}

	/**
	 * Envoi d'une réponse à une boite de dialogue
	 * @param dialogAnswer réponse à envoyer
	 */
	public void sendDialogAnswer(final IDialogAnswer dialogAnswer) {
		Job job = new Job("Send DialogAnswer") { //$NON-NLS-1$
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				ISession currentSession = SessionManager.getInstance().getCurrentSession();
				if (currentSession != null) {
					LOGGER.fine("Demande de mise a jour du modele sur la plateforme"); //$NON-NLS-1$
					try {
						((Session) currentSession).sendDialogAnswer(dialogAnswer);
					} catch (ApiException e) {
						return new Status(IStatus.ERROR, "coloane", e.getMessage()); //$NON-NLS-1$
					}
					return Status.OK_STATUS;
				} else {
					return new Status(IStatus.ERROR, "coloane", "Current session not found"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		};

		job.setPriority(Job.SHORT);
		job.setSystem(true);
		job.schedule();
	}

	/**
	 * Ajoute un résultat pour la session courrante
	 * @param result résultat à ajouter
	 */
	public void addResult(final IResult result) {
		Job job = new Job("Add result") { //$NON-NLS-1$
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				ISession currentSession = SessionManager.getInstance().getCurrentSession();
				if (currentSession != null) {
					LOGGER.fine("Ajout d'un résultat"); //$NON-NLS-1$
					currentSession.getServiceResults().add(result.getServiceName(), result);
					return Status.OK_STATUS;
				} else {
					return new Status(IStatus.ERROR, "coloane", "Current session not found");
				}
			}
		};
		job.setPriority(Job.SHORT);
		job.setSystem(true);
		job.schedule();
	}
}
