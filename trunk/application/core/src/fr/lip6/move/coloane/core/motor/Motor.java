package fr.lip6.move.coloane.core.motor;

import fr.lip6.move.coloane.core.communications.Com;
import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.core.motor.session.Session;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.UserInterface;
import fr.lip6.move.coloane.core.ui.dialogs.AuthenticationInformation;
import fr.lip6.move.coloane.core.ui.dialogs.SaveReceivedModel;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.ModelImplAdapter;
import fr.lip6.move.coloane.core.ui.panels.HistoryView;
import fr.lip6.move.coloane.interfaces.model.IModel;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Gestionnaire de Sessions/Formalismes<br>
 * Le moteur est charge de faire le lien entre le module com et l'interface graphique<br>
 * Il doit etre tenu au courant des changements de sessions.
 */
public final class Motor {

	/** Le gestionnaire de formalismes */
	private static FormalismManager formalismManager;

	/** Le gestionnaire de session */
	private static SessionManager sessionManager;

	/** L'operation courante */
	private ColoaneProgress currentProgress;

	/** Le module de communications */
	private Com com = null;

	/** L'interface utilisateur */
	private UserInterface ui = null;

	/** La fenetre graphique actuelle */
	private IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

	/** L'instance du singleton : Motor */
	private static Motor instance;

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
	 * Recupere une poignee sur le moteur
	 * @param com le module de communication
	 */
	public void setCom(Com moduleCom) {
		Coloane.getLogger().config("Attachement du module de communication avec le moteur"); //$NON-NLS-1$
		this.com = moduleCom;
	}

	/**
	 * Recupere une poignee sur l'interface utilisateur
	 * @param ui L'interface utilisateur
	 */
	public void setUi(UserInterface moduleUi) {
		Coloane.getLogger().config("Attachement de l'interface utilisateur avec le moteur"); //$NON-NLS-1$
		this.ui = moduleUi;
	}

	/**
	 * Demande l'authentification aupres de la plateforme
	 * @param authInformation Les informations recues de la boite de dialogue
	 */
	public void authentication(final AuthenticationInformation authInformation) {
		Boolean res = Boolean.FALSE;

		// Verification que toutes les donnees sont fournies
		if (authInformation == null) {
			Coloane.showErrorMsg(Messages.Motor_0);
			return;
		}

		// Affichage dans la zone d'historique
		HistoryView.getInstance().addText(Messages.Motor_15);

		IWorkbench workbench = PlatformUI.getWorkbench();
		IRunnableContext context = workbench.getProgressService();

		// Definition de l'operation d'authentification
		ColoaneProgress runnable = new ColoaneProgress(sessionManager.getCurrentSession(), res) {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				setMonitor(monitor);
				setResults(com.authentication(authInformation, monitor));
			}
		};

		try {
			context.run(false, false, runnable);
		} catch (Exception e) {
			Coloane.getLogger().warning("Echec de l'authentification: " + e.getMessage()); //$NON-NLS-1$
		}

		// Recupere le resultat de l'operation
		res = (Boolean) runnable.getResults();

		// Si l'authentification s'est bien passee
		if (res.booleanValue()) {
			HistoryView.getInstance().addLine(Messages.Motor_3);
			sessionManager.setAuthenticated(true);
		} else {
			HistoryView.getInstance().addLine(Messages.Motor_4);
			sessionManager.setAuthenticated(false);
		}

		// Mise a jour des boutons et menus de connexion
		ui.platformState(sessionManager.isAuthenticated(), SessionManager.CLOSED);
	}

	/**
	 * Creation d'une session
	 * @param model Le modele qui doit etre attache a la session
	 * @param name Le nom de la session
	 * @return boolean Resultat de l'operation
	 */
	public boolean createSession(IModelImpl model, String name) {
		// On doit controller si une session ne se nomme deja pas pareil
		if (sessionManager.getSession(name) != null) {
			Coloane.getLogger().warning("Une session homonyme existe deja"); //$NON-NLS-1$
			return false;
		}

		Coloane.getLogger().fine("Creation de la session : " + name); //$NON-NLS-1$
		// Creation d'une nouvelle session
		Session session = new Session(name);
		session.setModel(model); // On associe le modele a la session
		sessionManager.attachSession(session); // On ajoute la session au moteur de sessions

		return true;
	}

	/**
	 * Ouvre une connexion pour un modele (connect model)
	 * @param model Le modele adapte
	 * @param sessionName Le nom de la session eclipse
	 * @return booleen Le resultat de l'operation
	 * @throws ColoaneException
	 */
	public void openSession() {
		// Verification de l'existence du module de communications
		if (com == null) {
			Coloane.getLogger().warning("Module de communication non instanciee"); //$NON-NLS-1$
			Coloane.showErrorMsg(Messages.Motor_5);
			return;
		}

		// On verifie que l'utilisateur est authentifie avant de connecter le modele
		if (!sessionManager.isAuthenticated()) {
			Coloane.getLogger().warning("Aucune authentification prealable"); //$NON-NLS-1$
			Coloane.showWarningMsg(Messages.Motor_6);
			return;
		}

		Boolean res = Boolean.FALSE;
		IWorkbench workbench = PlatformUI.getWorkbench();
		IRunnableContext context = workbench.getProgressService();

		ColoaneProgress runnable = new ColoaneProgress(sessionManager.getCurrentSession(), res) {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				setMonitor(monitor);
				setResults(com.openSession(sessionManager.getCurrentSessionModel(), monitor));
				waitUntilEnd(); // Attente de la fin de l'operation
			}
		};

		// Doit etre positionne avant le run !!
		currentProgress = runnable;

		// Affichage dans la zone d'historique
		HistoryView.getInstance().addText(Messages.Motor_7);

		// Recuperation de la session courante
		Session current = Motor.getInstance().getSessionManager().getCurrentSession();
		if (current != null) {
			// Le modele existe... On peut essayer de le connecter
			HistoryView.getInstance().addLine(current.getName());
			Coloane.getLogger().fine("Session courante : " + current.getName()); //$NON-NLS-1$

			try {
				context.run(true, false, runnable);
			} catch (Exception e) {
				Coloane.getLogger().warning("Echec de la connexion du modele: " + e.getMessage()); //$NON-NLS-1$
			}

			// Recupere le resultat de l'ouverture de session de la com
			res = (Boolean) runnable.getResults();

			// Si l'ouverture de connexion echoue, on supprime la session existante
			if (!res.booleanValue()) {
				Coloane.getLogger().warning("Echec de l'ouverture de session sur FK : Destruction de moignon"); //$NON-NLS-1$
				sessionManager.destroySession(current.getName());

			// Si l'ouverture reussie
			} else {
				sessionManager.setCurrentSessionConnected();
				ui.platformState(sessionManager.isAuthenticated(), sessionManager.getCurrentSessionStatus());
			}
		} else {
			Coloane.getLogger().warning("Aucun modele actif"); //$NON-NLS-1$
			HistoryView.getInstance().addLine(Messages.Motor_8);
			Coloane.showWarningMsg(Messages.Motor_9);
		}
	}

	/**
	 * Fermeture de la session courante
	 * @return boolean Le resultat de l'operation
	 */
	public void closeSession() {
		// Verification de l'existence du module de communications
		if (com == null) {
			Coloane.getLogger().warning("Module de communication non instanciee"); //$NON-NLS-1$
			Coloane.showErrorMsg(Messages.Motor_10);
			return;
		}

		// On verifie que le modele courant est bien connecte avant de le deconnecter
		if (sessionManager.getCurrentSessionStatus() != SessionManager.CONNECTED) {
			Coloane.getLogger().warning("Le modele courant n'est pas connecte"); //$NON-NLS-1$
			Coloane.showWarningMsg(Messages.Motor_11);
			return;
		}

		Boolean res = Boolean.FALSE;
		IWorkbench workbench = PlatformUI.getWorkbench();
		IRunnableContext context = workbench.getProgressService();

		ColoaneProgress runnable = new ColoaneProgress(sessionManager.getCurrentSession(), res) {
			@Override
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				setMonitor(monitor);
				setResults(com.closeSession(monitor));
				waitUntilEnd(); // Attente de la fin de l'operation
			}
		};

		// Doit etre positionne avant le run !!
		currentProgress = runnable;

		try {
			context.run(true, false, runnable);
		} catch (Exception e) {
			Coloane.getLogger().warning("Echec de la deconnexion du modele: " + e.getMessage()); //$NON-NLS-1$
		}

		// Recupere le resultat de la fermeture de session
		res = (Boolean) runnable.getResults();

		// Si la fermeture de session echoue
		if (res.booleanValue()) {
			sessionManager.setCurrentSessionDisconnected();
			ui.platformState(sessionManager.isAuthenticated(), sessionManager.getCurrentSessionStatus());
			ui.redrawMenus();
		} else {
			Coloane.getLogger().warning("La deconnexion de la session courante a echouee"); //$NON-NLS-1$
			Coloane.showErrorMsg(Messages.Motor_12);
		}
	}


	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.interfaces.IMotorUi#askForService(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void askForService(final String rootMenuName, final String referenceName, final String serviceName) {
		// Verification de l'existence du module de communications
		if (com == null) {
			Coloane.getLogger().warning("Module de communication non instanciee"); //$NON-NLS-1$
			Coloane.showErrorMsg(Messages.Motor_13);
			return;
		}

		// On verifie que le modele courant est bien connecte avant de le deconnecter
		if (sessionManager.getCurrentSessionStatus() != SessionManager.CONNECTED) {
			Coloane.getLogger().warning("Le modele courant n'est pas connecte"); //$NON-NLS-1$
			Coloane.showWarningMsg(Messages.Motor_14);
			return;
		}

		Boolean res = Boolean.FALSE;
		IWorkbench workbench = PlatformUI.getWorkbench();
		IRunnableContext context = workbench.getProgressService();

		ColoaneProgress runnable = new ColoaneProgress(sessionManager.getCurrentSession(), res) {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				setMonitor(monitor);
				com.askForService(rootMenuName, referenceName, serviceName, monitor);
				waitUntilEnd(); // Attente de la fin de l'operation
			}
		};

		// Doit etre positionne avant le run !!
		currentProgress = runnable;

		// Grisage du menu de services
		this.ui.changeRootMenuStatus(rootMenuName, false);

		try {
			context.run(true, false, runnable);
		} catch (Exception e) {
			Coloane.getLogger().warning("Echec de l'invocation de service (" + serviceName + ") " + e.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// Au retour d'un service, le modele est toujours propre
		sessionManager.getCurrentSessionModel().setDirty(false);
	}


	/**
	 * Detruit la session designee
	 * @param sessionName Le nom de la session a detruire
	 */
	public void destroySession(String sessionName) {
		if (sessionManager.destroySession(sessionName)) {
			Coloane.getLogger().finer("OK pour la destruction de la session"); //$NON-NLS-1$
			ui.platformState(sessionManager.isAuthenticated(), SessionManager.ERROR);
			ui.redrawMenus();
			Coloane.getLogger().finer("Session courante : " + sessionManager.getCurrentSessionName()); //$NON-NLS-1$
		}
	}

	/**
	 * Creation d'un nouveau modele et affichage dans l'editeur
	 * Cette creation implique la creation d'un nouveau fichier dans le workspace.
	 * Cette action est particulierement utile lors de la generation d'un modele par FK
	 * TODO: Rendre cette methode generique
	 * @param model le model brut
	 */
	public void setNewModel(IModel model) {
		Coloane.getLogger().fine("Sauvegarde du modele en provenance de la plateforme"); //$NON-NLS-1$

		// Construit le modele en memoire a partir du modele generique recu
		IModelImpl modelImpl;
		try {
			modelImpl = new ModelImplAdapter(model);
		} catch (BuildException e) {
			Coloane.getLogger().warning("Erreur lors de la construction du modele : " + e.getMessage()); //$NON-NLS-1$
			Coloane.showErrorMsg(Messages.Motor_2 + e.getMessage());
			return;
		}
		// Affichage de la boite de dialogue pour demander la sauvegarde du modele
		Display.getDefault().asyncExec(new SaveReceivedModel(modelImpl, window));
	}

	/**
	 * Suspend la session designee
	 * @param name Le nom de la session a suspendre
	 * @return un booleen qui indique si l'operation s'est bien passee
	 */
	public void resumeSession(String name) {
		if (sessionManager.resumeSession(name)) {
			Coloane.getLogger().finer("OK pour la reprise de session " + name); //$NON-NLS-1$
			ui.platformState(sessionManager.isAuthenticated(), sessionManager.getSessionStatus(name));
			ui.redrawMenus();
		} else {
			Coloane.getLogger().fine("Echec lors de la reprise de session " + name); //$NON-NLS-1$
		}
		Coloane.getLogger().finer("Session courante : " + sessionManager.getCurrentSessionName()); //$NON-NLS-1$
	}

	/**
	 * Donne la main sur le SessionManager
	 * @return SessionManager Le gestionnaire de sessions
	 */
	public SessionManager getSessionManager() {
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
	 * Retourne la session concernee par les oparations en cours
	 * @return La session attachee a l'operation
	 */
	public Session getConcernedSession() {
		if (currentProgress != null) {
			Coloane.getLogger().finer("Recuperation de la session attachee"); //$NON-NLS-1$
			return currentProgress.getAttachedSession();
		} else {
			Coloane.getLogger().warning("Aucun service en cours..."); //$NON-NLS-1$
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.interfaces.IMotorCom#endService()
	 */
	public void endService() {
		if (currentProgress != null) {
			Coloane.getLogger().finer("Demande de liberation de moniteur"); //$NON-NLS-1$
			currentProgress.freeMonitor();
			//currentProgress = null;
		} else {
			Coloane.getLogger().warning("Aucun service en cours..."); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.interfaces.IMotorCom#setTaskDescription(java.lang.String, java.lang.String)
	 */
	public void setTaskDescription(String service, String description) {
		if (currentProgress != null) {
			Coloane.getLogger().finer("Demande d'affichage de precision sur la tache en cours"); //$NON-NLS-1$
			currentProgress.getMonitor().worked(1);
			if (description != "") { currentProgress.getMonitor().setTaskName(description); } //$NON-NLS-1$
		} else {
			Coloane.getLogger().warning("Aucun service en cours..."); //$NON-NLS-1$
		}
	}

	/**
	 * Demande de deconnexion brutale (initiee par le client)
	 */
	public void breakConnection() {
		Coloane.getLogger().fine("Deconnexion brutale initiee par le client"); //$NON-NLS-1$
		sessionManager.destroyAllSessions();
		sessionManager.setAuthenticated(false);
		this.com.breakConnection();
		ui.redrawMenus();
		ui.platformState(sessionManager.isAuthenticated(), sessionManager.getCurrentSessionStatus());
	}
}
