package fr.lip6.move.coloane.motor;

import fr.lip6.move.coloane.interfaces.IComMotor;
import fr.lip6.move.coloane.interfaces.IMotorCom;
import fr.lip6.move.coloane.interfaces.IMotorUi;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.motor.session.Session;
import fr.lip6.move.coloane.motor.session.SessionManager;
import fr.lip6.move.coloane.ui.dialogs.SaveReceivedModel;
import fr.lip6.move.coloane.ui.model.IModelImpl;
import fr.lip6.move.coloane.ui.model.ModelImplAdapter;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class Motor implements IMotorCom, IMotorUi {
	private static FormalismManager formalismManager;
	private static SessionManager sessionManager;

	/* Le module de communications */
	private IComMotor com = null;

	private IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

	/**
	 * Constructeur du module moteur
	 */
	public Motor() {
		Motor.formalismManager = new FormalismManager();
		Motor.sessionManager = new SessionManager();
	}

	/**
	 * Recupere une poignee sur le moteur
	 * @param com le module de communication
	 */
	public final void setCom(IComMotor moduleCom) {
		this.com = moduleCom;
	}

	/**
	 * Ouvre une connexion pour un modele
	 *
	 * @param model Le modele adapte
	 * @param sessionName Le nom de la session eclipse
	 * @return booleen Le resultat de l'operation
	 * @throws Exception
	 */
	public final boolean openSession(IModelImpl model, String eclipseSessionName) throws Exception {

		try {

			// Verification de l'existence du module de communications
			if (com == null) {
				throw new Exception(Coloane.traduction.getString("motor.Motor.0")); //$NON-NLS-1$
			}


			// On doit controller si une session ne se nomme deja pas pareil
			if (Motor.sessionManager.getSession(eclipseSessionName) != null) {
				System.err.println("Une session homonyme existe deja..."); //$NON-NLS-1$
				return false;
			}

			// Creation d'une nouvelle session
			Session session = new Session(eclipseSessionName, Session.cntSession++);
			session.setModel(model); // On associe le modele a la session
			Motor.sessionManager.setSession(session); // On ajoute la session au moteur de sessions

			// Demande de connexion du modele au module de communications
			boolean result = com.openSession(model);

			// Si l'ouverture de connexion echoue, on supprime la session existante
			if (!result) {
				Motor.sessionManager.destroyCurrentSession();
			}

			return result;

		} catch (Exception e) {
			throw e;
		}
	}

	public final boolean closeSession() throws Exception {
		boolean res = com.closeSession();
		Motor.sessionManager.destroyCurrentSession();
		return res;
	}


	/**
	 * Creation d'un nouveau modele et affichage dans l'editeur
	 * Cette creation implique la creation d'un nouveau fichier dans le workspace.
	 * Cette action est particulierement utile lors de la generation d'un modele par FK
	 * @param model le model brut
	 */
	public final void setNewModel(IModel model) {
		// Construit le modele en memoire a partir du modele generique recu
		//IModelImpl modelImpl = new ModelImplAdapter(model,getFormalismManager().loadFormalism(model.getFormalism())); //$NON-NLS-1$
		IModelImpl modelImpl = new ModelImplAdapter(model, getFormalismManager().loadFormalism("ReachabilityGraph")); //$NON-NLS-1$

		// Affichage de la boite de dialogue pour demander la sauvegarde du modele
		Display.getDefault().asyncExec(new SaveReceivedModel(modelImpl, window));
	}

	/**
	 * Donne la main sur le SessionManager
	 * @return SessionManager Le gestionnaire de sessions
	 */
	public final SessionManager getSessionManager() {
		return Motor.sessionManager;
	}

	/**
	 * Donne la main sur le FormalismManager
	 * @return FormalismManager Le gestionnairede formalismes
	 */
	public final FormalismManager getFormalismManager() {
		return Motor.formalismManager;
	}
}
