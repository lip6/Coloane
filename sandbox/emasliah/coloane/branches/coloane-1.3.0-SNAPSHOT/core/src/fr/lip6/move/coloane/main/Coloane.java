package fr.lip6.move.coloane.main;

import fr.lip6.move.coloane.communications.Com;
import fr.lip6.move.coloane.motor.Motor;
import fr.lip6.move.coloane.ui.UserInterface;
import fr.lip6.move.coloane.ui.model.IModelImpl;

import java.util.ResourceBundle;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Coloane extends AbstractUIPlugin {

	public static ResourceBundle translate = null;
	private static Coloane plugin;
	private Com com = null;
	private Motor motor = null;
	private UserInterface ui = null;

	public Coloane() {
		plugin = this;
		try {
			translate = ResourceBundle.getBundle("resources/LNG");
		} catch (Exception e) {
			System.err.println("Localization files missing : " + e.getMessage());
		}
	}

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

			//System.out.println(traduction.getString("main.Coloane.0"));
			System.out.println("-- Initialisation du plugin Coloane --");

			// Initialisation de l'interface graphique
			ui = new UserInterface();
			if (ui == null) {
				System.err.println("Erreur lors du chargement de l'interface utilisateur");
			}

			// Initialisation du moteur
			motor = new Motor();
			if (motor == null) {
				System.err.println("Erreur lors du chargement du module moteur");
			}

			// Initialisation du module de communications
			com = Com.getInstance();
			if (com == null) {
				System.err.println("Erreur lors du chargement du module de communications");
			}

			// Creation des liens
			com.setUi(ui);
			com.setMotor(motor);

			motor.setCom(com);

			ui.setCom(com);
			ui.setMotor(motor);

		} catch (Exception e) {
			System.err.println("Erreur : " + e.getMessage());
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
	 * Retourne le module de traduction
	 * @return ResourceBundle
	 */
	public static ResourceBundle getTranslate() {
		return translate;
	}

	/**
	 * Le methode de fin de vie du plugin
	 * @param context Parametre systeme fourni par Eclipse
	 * TODO: Appeler les methodes de fin pour chaque module
	 */
	@Override
	public final void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
		translate = null;
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
				System.out.println("OK pour l'update");
				plugin.com.toUpdate(dateUpdate);
			}
		}
	}

	/**
	 * Affiche un message d'erreur sous forme de boite de dialogue
	 * @param msg Le message a afficher
	 */
	public static void showErrorMsg(String msg) {
		MessageDialog.openError(getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(), "Coloane Error", msg);
	}

	/**
	 * Affiche un message d'avertissment sous forme de boite de dialogue
	 * @param msg Message a afficher
	 */
	public static void showWarningMsg(String msg) {
		MessageDialog.openWarning(getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(), "Coloane Warning", msg);
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
}
