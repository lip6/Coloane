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

import fr.lip6.move.coloane.communications.models.Model;
import fr.lip6.move.coloane.interfaces.IComMotor;
import fr.lip6.move.coloane.interfaces.IMotorCom;
import fr.lip6.move.coloane.interfaces.IMotorUi;
import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.motor.formalism.FormalismManager;
import fr.lip6.move.coloane.motor.models.ModelImplAdapter;
import fr.lip6.move.coloane.motor.session.Session;
import fr.lip6.move.coloane.motor.session.SessionManager;

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
	 * @param model 
	 * @param sessionName
	 * @return le resultat de l'operation
	 * @throws Exception
	 */
	public boolean openConnexion(ModelImplAdapter model, String sessionName) throws Exception {

		try {
			// Creation d'une nouvelle session
			Session session = new Session(sessionName, Session.cntSession++);
			
			if (session == null) {
				throw new Exception("Echec lors de la creation de la session");
			}
			
			// On associe le modele à la session
			session.setSessionModel(model);
			// On ajoute la session au gestionnaire de session
			Motor.sessionManager.setSession(session);
			
			
			if (com == null) {
				throw new Exception("Module de communication non instancié");
			}
			
			boolean result = com.connexion(model);
			return result;
		
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Donne la main sur le SessionManager
	 */
	public SessionManager getSessionManager() {
		return Motor.sessionManager;
	}
	
	/**
	 * Donne la main sur le FormalismManager
	 */
	public FormalismManager getFormalismManager() {
		return Motor.formalismManager;
	}
	
	public void giveAModel(ModelImplAdapter model) {
		// Affecte le modele à la session courante
		Motor.sessionManager.getCurrentSession().setSessionModel(model);
	}

	public void setNewModel(Model model) {
		// affecte le modèle à la session courante
		final ModelImplAdapter modelImpl = new ModelImplAdapter(model,getFormalismManager().loadFormalism("ReachabilityGraph"));
		final Shell shell = window.getShell();
						
		Display.getDefault().asyncExec(new Runnable(){
	        public void run(){
	    
	       	   SaveAsDialog sd = new SaveAsDialog(shell) {
	       		   protected void configureShell(Shell shell) {
	       				super.configureShell(shell);
	       				shell.setText("Enregistrement du modele recu");
	       			}
	       		};
	       		
	       		sd.setOriginalName(Motor.sessionManager.getCurrentSession().getName());
	        	   
	       		if (sd.open() == SaveAsDialog.OK) {
	       			IPath path = sd.getResult();
		
	       			final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
	       			try {
						new ProgressMonitorDialog(shell).run(false, // don't fork
							false, // not cancelable
							new WorkspaceModifyOperation() { // run this operation
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
							});
					} catch (Exception e) {
						Coloane.showErrorMsg(e.getMessage());
						e.printStackTrace();
					}
				};
	        }
		});
	}	
}
