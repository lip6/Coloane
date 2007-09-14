package fr.lip6.move.coloane.core.motor;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.interfaces.IComMotor;
import fr.lip6.move.coloane.core.interfaces.IMotorCom;
import fr.lip6.move.coloane.core.interfaces.IMotorUi;
import fr.lip6.move.coloane.core.interfaces.IUiMotor;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.core.motor.session.Session;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.dialogs.SaveReceivedModel;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.ModelImplAdapter;
import fr.lip6.move.coloane.interfaces.model.IModel;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Gestionnaire de Sessions/Formalismes<br>
 * Le moteur est charge de faire le lien entre le module com et l'interface graphique<br>
 * Il doit etre tenu au courant des changements de sessions.
 */
public final class Motor implements IMotorCom, IMotorUi {
	private static FormalismManager formalismManager;
	private static SessionManager sessionManager;

	/** Le module de communications */
	private IComMotor com = null;

	/** L'interface utilisateur */
	private IUiMotor ui = null;

	/** La fenetre graphique actuelle */
	private IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

	/** L'instance du singleton : Motor */
	private static Motor instance;

	/** Constructeur du module moteur */
	private Motor() {
		formalismManager = new FormalismManager();
		sessionManager = new SessionManager();
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
	public void setCom(IComMotor moduleCom) {
		Coloane.getLogger().config("Attachement du module de communication avec le moteur"); //$NON-NLS-1$
		this.com = moduleCom;
	}

	/**
	 * Recupere une poignee sur l'interface utilisateur
	 * @param ui L'interface utilisateur
	 */
	public void setUi(IUiMotor moduleUi) {
		Coloane.getLogger().config("Attachement de l'interface utilisateur avec le moteur"); //$NON-NLS-1$
		this.ui = moduleUi;
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
	public boolean openSession() throws ColoaneException {
		// Verification de l'existence du module de communications
		if (com == null) { throw new ColoaneException(Messages.Motor_0); }
		// Demande de connexion du modele au module de communications
		boolean result = com.openSession(sessionManager.getCurrentSessionModel());
		// Si l'ouverture de connexion echoue, on supprime la session existante
		if (!result) {
			Coloane.getLogger().warning("Echec de l'ouverture de session sur FK : Destruction de moignon"); //$NON-NLS-1$
			sessionManager.destroySession(sessionManager.getCurrentSessionName());
		} else {
			sessionManager.setCurrentSessionConnected();
			ui.platformState(com.isAuthenticated(), sessionManager.getCurrentSessionStatus());
		}
		return result;
	}

	/**
	 * Fermeture de la session courante
	 * @return boolean Le resultat de l'operation
	 */
	public boolean closeSession() {
		if (com.closeSession()) {
			sessionManager.setCurrentSessionDisconnected();
			ui.platformState(com.isAuthenticated(), sessionManager.getCurrentSessionStatus());
			ui.redrawMenus();
			return true;
		}
		return false;
	}

	/**
	 * Detruit la session designee
	 * @param sessionName Le nom de la session a detruire
	 */
	public void destroySession(String sessionName) {
		if (sessionManager.destroySession(sessionName)) {
			Coloane.getLogger().finer("OK pour la destruction de la session"); //$NON-NLS-1$
			ui.platformState(com.isAuthenticated(), SessionManager.ERROR);
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
			modelImpl = new ModelImplAdapter(model, getFormalismManager().loadFormalism("ReachabilityGraph")); //$NON-NLS-1$
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
			Coloane.getLogger().finer("OK pour la reprise de session" + name); //$NON-NLS-1$
			ui.platformState(com.isAuthenticated(), sessionManager.getSessionStatus(name));
			ui.redrawMenus();
		} else {
			Coloane.getLogger().warning("Echec lors de la reprise de session" + name); //$NON-NLS-1$
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
}
