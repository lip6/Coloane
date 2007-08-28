package fr.lip6.move.coloane.main;

import fr.lip6.move.coloane.communications.Com;
import fr.lip6.move.coloane.interfaces.utils.ColoaneLogHandler;
import fr.lip6.move.coloane.motor.Motor;
import fr.lip6.move.coloane.ui.UserInterface;
import fr.lip6.move.coloane.ui.model.IModelImpl;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Coloane extends AbstractUIPlugin {

	/** L'instance du plugin */
	private static Coloane plugin;

	/** Le module de communication */
	private Com com = null;

	/** Le moteur de formalisme et de sessions */
	private Motor motor = null;

	/** L'interface utilisateur */
	private UserInterface ui = null;

	/** Journalisation du projet */
	private static Logger coreLog;

	public Coloane() { plugin = this; }

	/**
	 * Methode de lancement du plugin
	 * C'est la premiere methode a etre appelee lors du chargement d'une classe du plugin
	 * @param context Parametre systeme fourni par Eclipse
	 * @throws Exception
	 */
	@Override
	public final void start(BundleContext context) throws Exception {
		super.start(context);

		try {
			// Initialisation du logger
			this.initializeLogger();
			coreLog.config("-- Initialisation du plugin Coloane --"); //$NON-NLS-1$

			// Initialisation de l'interface graphique
			ui = UserInterface.getInstance();
			if (ui == null) {
				coreLog.warning("Erreur lors du chargement de l'interface utilisateur"); //$NON-NLS-1$
			}

			// Initialisation du moteur
			motor = Motor.getInstance();
			if (motor == null) {
				coreLog.warning("Erreur lors du chargement du module moteur"); //$NON-NLS-1$
			}

			// Initialisation du module de communications
			com = Com.getInstance();
			if (com == null) {
				coreLog.warning("Erreur lors du chargement du module de communications"); //$NON-NLS-1$
			}

			// Creation des liens
			com.setUi(ui);
			com.setMotor(motor);
			motor.setCom(com);
			ui.setCom(com);
			ui.setMotor(motor);

			// Pour afficher la version et le numero de build
			String version = (String) getBundle().getHeaders().get("Implementation-Version"); //$NON-NLS-1$
			String build = (String) getBundle().getHeaders().get("Implementation-Build"); //$NON-NLS-1$
			if ((version != null) && (build != null)) {
				ui.printHistoryMessage("Core Version : " + version + " Build : " + build); //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				ui.printHistoryMessage("Core Version (Dev) : " + getBundle().getHeaders().get("Bundle-Version")); //$NON-NLS-1$ //$NON-NLS-2$
			}
		} catch (Exception e) {
			System.err.println("Erreur : " + e.getMessage()); //$NON-NLS-1$
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
	@Override
	public final void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Recupere le parametre dans le fichier de configuration
	 * @param key L'identifiant du parametre
	 * @return String Le parametre demande
	 */
	public static String getParam(String key) {
		return Platform.getResourceBundle(getDefault().getBundle()).getString(key);
	}

	/**
	 * Notifier le changement du modele de la session courrante
	 * @param model Le modele manipule par l'UI
	 */
	public static void notifyModelChange(IModelImpl model) {
		if (model != null) {
			int dateUpdate = model.modifyDate();
			if ((dateUpdate != 0) && (getDefault().getMotor().getSessionManager().getCurrentSession() != null)) {
				System.out.println("OK pour l'update"); //$NON-NLS-1$
				plugin.com.toUpdate(dateUpdate);
			}
		}
	}

	/**
	 * Affiche un message d'erreur sous forme de boite de dialogue
	 * @param msg Le message a afficher
	 */
	public static void showErrorMsg(String msg) {
		MessageDialog.openError(getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(), "Coloane Error", msg); //$NON-NLS-1$
	}

	/**
	 * Affiche un message d'avertissment sous forme de boite de dialogue
	 * @param msg Message a afficher
	 */
	public static void showWarningMsg(String msg) {
		MessageDialog.openWarning(getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(), "Coloane Warning", msg); //$NON-NLS-1$
	}

	/**
	 * Donne la main sur le module de communication
	 * @return Com le module de communication
	 */
	public final Com getCom() {
		return com;
	}

	/**
	 * Donne la main sur le module de communication
	 * @return Motor le module de communication
	 */
	public final Motor getMotor() {
		return motor;
	}

	/**
	 * Retourne le conteneur graphique de haut niveau
	 * @return Composite Le conteneur parent
	 */
	public static Composite getParent() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	}

	/**
	 * Initialisation du logger d'evenements
	 */
	private void initializeLogger() {
		coreLog = Logger.getLogger("fr.lip6.move.coloane.api"); //$NON-NLS-1$
		coreLog.setLevel(Level.FINEST); // On loggue tout !
		coreLog.addHandler(new ColoaneLogHandler());
	}

	/**
	 * Retourne le gestionnaire de logs
	 * @return Le logger
	 */
	public static Logger getLogger() {
		return coreLog;
	}
}
