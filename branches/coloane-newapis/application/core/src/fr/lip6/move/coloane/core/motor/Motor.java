package fr.lip6.move.coloane.core.motor;

import fr.lip6.move.coloane.core.communications.Com;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.ISessionManager;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.UserInterface;
import fr.lip6.move.coloane.core.ui.dialogs.AuthenticationInformation;
import fr.lip6.move.coloane.core.ui.dialogs.SaveReceivedModel;
import fr.lip6.move.coloane.core.ui.panels.HistoryView;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

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
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Le gestionnaire de formalismes */
	private static FormalismManager formalismManager;

	/** Le gestionnaire de session */
	private static ISessionManager sessionManager;

	/** L'instance du singleton : Motor */
	private static Motor instance;

	/** L'operation courante */
	private ColoaneProgress currentProgress;

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

		// Affichage dans la zone d'historique
		HistoryView.getInstance().addText(Messages.Motor_15);

		IWorkbench workbench = PlatformUI.getWorkbench();
		IRunnableContext context = workbench.getProgressService();

		// Definition de l'operation d'authentification
		ColoaneProgress runnable = new ColoaneProgress(sessionManager.getCurrentSession()) {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				setMonitor(monitor);
				try {
					setResults(Com.getInstance().authentication(authInformation, monitor));
				} catch (ApiException e) {
					e.printStackTrace();
					throw new InvocationTargetException(e);
				}
			}
		};

		try {
			context.run(false, true, runnable);
		} catch (InvocationTargetException e) {
			LOGGER.warning("Echec de l'authentification : " + e.getTargetException()); //$NON-NLS-1$
			HistoryView.getInstance().addLine(Messages.Motor_4);
			sessionManager.setAuthenticated(false);
			return;
		} catch (InterruptedException e) {
			LOGGER.warning("Annulation de l'authentification : " + e.getMessage()); //$NON-NLS-1$
			sessionManager.setAuthenticated(false);
			return;
		}

		// Recupere le resultat de l'operation
		IConnectionInfo connectionInfo = (IConnectionInfo) runnable.getResults();

		HistoryView.getInstance().addLine(Messages.Motor_3 + " " + connectionInfo.getFkName() //$NON-NLS-1$
				+ "-" + connectionInfo.getFkMajor() //$NON-NLS-1$
				+ "." + connectionInfo.getFkMinor()); //$NON-NLS-1$
		sessionManager.setAuthenticated(true);

		// Mise a jour des boutons et menus de connexion
		UserInterface.getInstance().platformState(sessionManager.isAuthenticated(), ISession.CLOSED);
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

		IWorkbench workbench = PlatformUI.getWorkbench();
		IRunnableContext context = workbench.getProgressService();

		final ISession session = sessionManager.getCurrentSession();
		if (session == null) {
			LOGGER.warning("Tentative d'ouverture de session alors qu'il n'y a aucune session courrante"); //$NON-NLS-1$
			return;
		}

		ColoaneProgress runnable = new ColoaneProgress(sessionManager.getCurrentSession()) {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				setMonitor(monitor);
				setResults(session.connect());
//				waitUntilEnd(); // Attente de la fin de l'operation
			}
		};

		// Doit etre positionne avant le run !!
		currentProgress = runnable;

		// Affichage dans la zone d'historique
		HistoryView.getInstance().addText(Messages.Motor_7);

		// Le modele existe... On peut essayer de le connecter
		HistoryView.getInstance().addLine(session.getName());
		LOGGER.fine("Session courante : " + session.getName()); //$NON-NLS-1$

		try {
			context.run(true, false, runnable);
		} catch (InvocationTargetException e) {
			LOGGER.warning("Echec de la connexion du modele: " + e.getTargetException()); //$NON-NLS-1$
		} catch (InterruptedException e) {
			LOGGER.warning("Annulation de la connexion du modele: " + e.getMessage()); //$NON-NLS-1$
		}

		// Recupere le resultat de l'ouverture de session de la com
		boolean res = (Boolean) runnable.getResults();

		// Si l'ouverture de connexion echoue, on supprime la session existante
		if (!res) {
			LOGGER.warning("Echec de l'ouverture de session sur FK : Destruction de moignon"); //$NON-NLS-1$
			sessionManager.destroySession(session.getName());

			// Si l'ouverture reussie
		} else {
			UserInterface.getInstance().platformState(sessionManager.isAuthenticated(), sessionManager.getCurrentSession().getStatus());
		}
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

		IWorkbench workbench = PlatformUI.getWorkbench();
		IRunnableContext context = workbench.getProgressService();

		ColoaneProgress runnable = new ColoaneProgress(sessionManager.getCurrentSession()) {
			@Override
			public void run(IProgressMonitor monitor)
			throws InvocationTargetException, InterruptedException {
				setMonitor(monitor);
				setResults(session.disconnect());
//				waitUntilEnd(); // Attente de la fin de l'operation
			}
		};

		// Doit etre positionne avant le run !!
		currentProgress = runnable;

		try {
			context.run(true, false, runnable);
		} catch (InvocationTargetException e) {
			LOGGER.warning("Echec de la déconnexion du modele: " + e.getTargetException()); //$NON-NLS-1$
		} catch (InterruptedException e) {
			LOGGER.warning("Annulation de la déconnexion du modele: " + e.getMessage()); //$NON-NLS-1$
		}

		if ((Boolean) runnable.getResults()) {
			UserInterface.getInstance().platformState(sessionManager.isAuthenticated(), sessionManager.getCurrentSession().getStatus());
			UserInterface.getInstance().redrawMenus();
		}
	}


	/** {@inheritDoc} */
	public void askForService(final String rootMenuName, final String referenceName, final String serviceName) {
		// Verification de l'existence du module de communications
		LOGGER.warning("Module de communication non instanciee"); //$NON-NLS-1$
		Coloane.showErrorMsg(Messages.Motor_13);
		return;

		// On verifie que le modele courant est bien connecte avant de le deconnecter
		if (sessionManager.getCurrentSession().getStatus() != ISession.CONNECTED) {
			LOGGER.warning("Le modele courant n'est pas connecte"); //$NON-NLS-1$
			Coloane.showWarningMsg(Messages.Motor_14);
			return;
		}

		IWorkbench workbench = PlatformUI.getWorkbench();
		IRunnableContext context = workbench.getProgressService();

		ColoaneProgress runnable = new ColoaneProgress(sessionManager.getCurrentSession()) {
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				setMonitor(monitor);
//				Com.getInstance().askForService(rootMenuName, referenceName, serviceName, monitor);
				waitUntilEnd(); // Attente de la fin de l'operation
			}
		};

		// Doit etre positionne avant le run !!
		currentProgress = runnable;

		// Grisage du menu de services
		UserInterface.getInstance().changeRootMenuStatus(rootMenuName, false);

		try {
			context.run(true, false, runnable);
		} catch (InvocationTargetException e) {
			LOGGER.warning("Echec de l'invocation de service (" + serviceName + ") " + e.getTargetException()); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (InterruptedException e) {
			LOGGER.warning("Annulation du service : " + serviceName + " [" + e.getMessage() + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}

		// Au retour d'un service, le modele est toujours propre
		sessionManager.getCurrentSession().getGraph().setDirty(false);
	}


	/**
	 * Detruit la session designee
	 * @param sessionName Le nom de la session a detruire
	 */
	public void destroySession(String sessionName) {
		LOGGER.finer("Destruction de la session : " + sessionName); //$NON-NLS-1$
		sessionManager.destroySession(sessionName);
		UserInterface.getInstance().platformState(sessionManager.isAuthenticated(), ISession.ERROR);
		UserInterface.getInstance().redrawMenus();

		if (sessionManager.getCurrentSession() != null) {
			LOGGER.finer("Session courante : " + sessionManager.getCurrentSession().getName()); //$NON-NLS-1$
		} else {
			LOGGER.fine("Pas de session courante..."); //$NON-NLS-1$
		}
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
	public void resumeSession(String name) {
		if (sessionManager.resumeSession(name)) {
			LOGGER.finer("OK pour la reprise de session " + name); //$NON-NLS-1$
			UserInterface.getInstance().platformState(sessionManager.isAuthenticated(), sessionManager.getSession(name).getStatus());
			UserInterface.getInstance().redrawMenus();
		} else {
			LOGGER.fine("Echec lors de la reprise de session " + name); //$NON-NLS-1$
		}

		if (sessionManager.getCurrentSession() != null) {
			LOGGER.finer("Session courante : " + sessionManager.getCurrentSession().getName()); //$NON-NLS-1$
		} else {
			LOGGER.fine("Pas de session courante"); //$NON-NLS-1$
		}
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
	 * Retourne la session concernee par les oparations en cours
	 * @return La session attachee a l'operation
	 */
	public ISession getConcernedSession() {
		if (currentProgress != null) {
			LOGGER.finer("Recuperation de la session attachee"); //$NON-NLS-1$
			return currentProgress.getAttachedSession();
		} else {
			LOGGER.warning("Aucun service en cours..."); //$NON-NLS-1$
			return null;
		}
	}

	/** {@inheritDoc} */
	public void endService() {
		if (currentProgress != null) {
			LOGGER.finer("Demande de liberation de moniteur"); //$NON-NLS-1$
			currentProgress.freeMonitor();
			//currentProgress = null;
		} else {
			LOGGER.warning("Aucun service en cours..."); //$NON-NLS-1$
		}
	}

	/** {@inheritDoc} */
	public void setTaskDescription(String service, String description) {
		if (currentProgress != null) {
			LOGGER.finer("Demande d'affichage de precision sur la tache en cours"); //$NON-NLS-1$
			currentProgress.getMonitor().worked(1);
			if (!description.equals("")) { currentProgress.getMonitor().setTaskName(description); } //$NON-NLS-1$
		} else {
			LOGGER.warning("Aucun service en cours..."); //$NON-NLS-1$
		}
	}

	/**
	 * Demande de deconnexion brutale (initiee par le client)
	 */
	public void breakConnection() {
		LOGGER.fine("Demmande de déconnexion"); //$NON-NLS-1$
		Com.getInstance().breakConnection();
		sessionManager.destroyAllSessions();
		sessionManager.setAuthenticated(false);
		UserInterface.getInstance().redrawMenus();
		UserInterface.getInstance().platformState(false, ISession.CLOSED);
	}
}
