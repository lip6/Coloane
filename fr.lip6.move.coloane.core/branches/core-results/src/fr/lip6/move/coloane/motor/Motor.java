package fr.lip6.move.coloane.motor;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.IDE;

import fr.lip6.move.coloane.interfaces.IComMotor;
import fr.lip6.move.coloane.interfaces.IMotorCom;
import fr.lip6.move.coloane.interfaces.IMotorUi;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.motor.session.Session;
import fr.lip6.move.coloane.motor.session.SessionManager;
import fr.lip6.move.coloane.ui.model.IModelImpl;
import fr.lip6.move.coloane.ui.model.ModelImplAdapter;

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
	public void setCom(IComMotor com) {
		this.com = com;
	}

	/**
	 * Ouvre une connexion pour un modele
	 * 
	 * @param model Le modele adapte
	 * @param sessionName Le nom de la session eclipse
	 * @return booleen Le resultat de l'operation
	 * @throws Exception
	 */
	public boolean openSession(IModelImpl model, String eclipseSessionName) throws Exception {

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

	public boolean closeSession() throws Exception {
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
	public void setNewModel(IModel model) {
		// affecte le mod�le � la session courante
		final IModelImpl modelImpl = new ModelImplAdapter(model,getFormalismManager().loadFormalism("ReachabilityGraph")); //$NON-NLS-1$
		final Shell shell = window.getShell();

		Display.getDefault().asyncExec(new Runnable(){
			public void run(){

				SaveAsDialog sd = new SaveAsDialog(shell) {
					protected void configureShell(Shell shell) {
						super.configureShell(shell);
						shell.setText(Coloane.traduction.getString("motor.Motor.3")); //$NON-NLS-1$
					}
				};

				sd.setOriginalName(Motor.sessionManager.getCurrentSession().getName());

				if (sd.open() == SaveAsDialog.OK) {
					IPath path = sd.getResult();

					final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
					try {
						new ProgressMonitorDialog(shell).run(false,	false, new WorkspaceModifyOperation() {
							public void execute(final IProgressMonitor monitor) {
								try {
									ByteArrayOutputStream out = new ByteArrayOutputStream();
									ObjectOutputStream oos = new ObjectOutputStream(out);

									oos.writeObject(modelImpl);
									oos.close();

									file.create(new ByteArrayInputStream(
											out.toByteArray()), // contents
											true, // keep saving, even if IFile is out of sync with the Workspace
											monitor); // progress monitor

									// Open editor
									IDE.openEditor(window.getActivePage(),file, true);
								} catch (Exception e) {
									Coloane.showErrorMsg(e.getMessage());
									e.printStackTrace();
								}
							}
						}
						);
					} catch (Exception e) {
						Coloane.showErrorMsg(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
		);
	}

	/**
	 * Donne la main sur le SessionManager
	 * @return SessionManager Le gestionnaire de sessions
	 */
	public SessionManager getSessionManager() {
		return Motor.sessionManager;
	}

	/**
	 * Donne la main sur le FormalismManager
	 * @return FormalismManager Le gestionnairede formalismes
	 */
	public FormalismManager getFormalismManager() {
		return Motor.formalismManager;
	}
}
