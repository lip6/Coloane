package fr.lip6.move.coloane.main;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import fr.lip6.move.coloane.communications.Com;
import fr.lip6.move.coloane.motor.Motor;
import fr.lip6.move.coloane.motor.models.ModelImplAdapter;
import fr.lip6.move.coloane.motor.session.Session;
import fr.lip6.move.coloane.ui.UserInterface;

public class Coloane extends AbstractUIPlugin {
	
	private static Coloane plugin;
	private Com com = null;	
	private Motor motor = null;
	private UserInterface ui = null;
	
	public Coloane () {
		plugin = this;
	}
	
	/**
	 * Methode de lancement du plugin
	 * C'est la premiere methode a etre appelee lors du chargement d'une classe du plugin
	 * @param context Parametre systeme fourni par Eclipse
	 * @throws Exception
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		try {
			System.out.println("-- Initialisation du plugin Coloane --");
			
			// Initialisation
			ui = new UserInterface();
			if (ui == null) {
				System.err.println("Erreur lors du chargement de l'interface utilisateur");				
			}
			
			motor = new Motor();
			if (motor == null) {
				System.err.println("Erreur lors du chargement du module moteur");
			}
			
			com = new Com(ui);
			if (com == null) {
				System.err.println("Erreur lors du chargement du module de communications");
			}
			
			// Creation des liens
			com.setMotor(motor);
			motor.setCom(com);
			
		} catch (Exception e) {
			System.err.println("Erreur : "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Permet de recuperer le plugin
	 * @return le plugin
	 */
	public static Coloane getDefault() {
		return plugin;
	}
	
	/**
	 * Permet de recuperer le workspace
	 * @return le workspace
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}
	
	/**
	 * Le methode de fin de vie du plugin
	 * @param context Parametre systeme fourni par Eclipse
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}
	
	
	/**
	 * Recupere le parametre dans le fichier de configuration
	 * @param key L'identifiant du parametre
	 * @return String le parametre demande
	 */
	public static String getParam(String key) {
		try {
			return Platform.getResourceBundle(getDefault().getBundle()).getString(key);
		} catch (Exception e) {
			return key;
		}

	}
	
	/**
	 * Notifier le changement du modèle de la 
	 * session courrante
	 *
	 */
	public void notifyModelChange() {
		Session currentSession = Coloane.getDefault().getMotor().getSessionManager().getSession();
		if (currentSession != null) {
			ModelImplAdapter model = currentSession.getSessionModel();
			if (model != null) {

				//try {
					//getDefault().getCom().notifyModification(model.getDate());
				//} catch (Exception e) {
					//System.err.println("Erreur : "e.printStackTrace());
				//}
				System.out.println("Changmeent du modele");
			model.modifyDate();

			} else {
				System.out.println("Le modele est nul");
				//doLog(IStatus.ERROR, "Model null in session");
			}
		} else {
			
		}
	}
	
	/**
	 * Affiche un message d'erreur sous forme de boite de dialogue
	 * @param msg Le message a afficher
	 */
	public static void showErrorMsg(String msg) {
		MessageDialog.openError(getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(), "Coloane",msg);

	}

	/**
	 * Affiche un message d'avertissment sous forme de boite de dialogue
	 * @param msg Message a afficher
	 */
	public static void showWarningMsg(String msg) {
		MessageDialog.openWarning(getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(), "Coloane",msg);

	}
	
	/**
	 * Donne la main sur le module de communication
	 * @return Com le module de communication
	 */
	public Com getCom() {
		return com;
	}
	
	/**
	 * Donne la main sur le module de communication
	 * @return Com le module de communication
	 */
	public Motor getMotor() {
		return motor;
	}
	
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin("fr.lip6.move.coloane",path);
	}
	
	public static Composite getParent () {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	}
}