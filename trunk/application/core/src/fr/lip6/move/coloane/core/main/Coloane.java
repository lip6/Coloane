package fr.lip6.move.coloane.core.main;

import fr.lip6.move.coloane.core.communications.Com;
import fr.lip6.move.coloane.core.motor.Motor;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.UserInterface;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.interfaces.utils.ColoaneLogFormatter;
import fr.lip6.move.coloane.interfaces.utils.ColoaneLogHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
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
			motor.setUi(ui);
			ui.setCom(com);
			ui.setMotor(motor);

			// Pour afficher la version et le numero de build
			ui.printHistoryMessage("Core Version : " + getVersion()); //$NON-NLS-1$
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
	 * Le methode de fin de vie du plugin
	 * @param context Parametre systeme fourni par Eclipse
	 */
	@Override
	public final void stop(BundleContext context) throws Exception {
		super.stop(context);
		coreLog.config("Arret du plugin"); //$NON-NLS-1$
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
			if ((dateUpdate != 0) && (getDefault().getMotor().getSessionManager().getCurrentSessionStatus() == SessionManager.CONNECTED)) {
				coreLog.fine("Demande de mise a jour du modele sur la plateforme"); //$NON-NLS-1$
				plugin.com.toUpdate(dateUpdate);
			}
		}
	}

	/**
	 * Affiche un message d'erreur sous forme de boite de dialogue
	 * @param msg Le message a afficher
	 */
	public static void showErrorMsg(String msg) {
		coreLog.fine("Affichage d'un message d'erreur : " + msg); //$NON-NLS-1$
		MessageDialog.openError(getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(), "Coloane Error", msg); //$NON-NLS-1$
	}

	/**
	 * Affiche un message d'avertissment sous forme de boite de dialogue
	 * @param msg Message a afficher
	 */
	public static void showWarningMsg(String msg) {
		coreLog.fine("Affichage d'un message de warning : " + msg); //$NON-NLS-1$
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
		coreLog = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
		coreLog.setLevel(Level.ALL); // On loggue tout !
		try {
			ColoaneLogHandler handler = ColoaneLogHandler.getInstance();
			ColoaneLogFormatter format = new ColoaneLogFormatter();
			format.setVersion(getVersion());
			handler.setFormatter(format);
			coreLog.addHandler(handler);
		} catch (Exception e) {
			System.err.println("Impossible d'initialiser le gestionnaire de logs sur fichier"); //$NON-NLS-1$
		}
	}

	/**
	 * Retourne le gestionnaire de logs
	 * @return Le logger
	 */
	public static Logger getLogger() {
		return coreLog;
	}

	/**
	 * Retourne la version du Core
	 * @return String
	 */
	private String getVersion() {
		return (String) getBundle().getHeaders().get("Bundle-Version"); //$NON-NLS-1$
	}

	/**
	 * Initialise le groupe de preferences avec des valeurs par defaut pour ce plugin
	 * @param store Le groupe ou stocker les preferences
	 */
	@Override
	protected final void initializeDefaultPreferences(IPreferenceStore store) {
		store.setDefault("LOGIN", getParam("LOGIN_DEFAULT")); //$NON-NLS-1$ //$NON-NLS-2$

		//Server name, IP and port for Framekit
		store.setDefault("SERVER", getParam("SERVER_DEFAULT")); //$NON-NLS-1$ //$NON-NLS-2$
		store.setDefault("IP", getParam("IP_DEFAULT")); //$NON-NLS-1$ //$NON-NLS-2$
		store.setDefault("PORT", getParam("PORT_DEFAULT")); //$NON-NLS-1$ //$NON-NLS-2$

		//Node color
		Color color = Display.getDefault().getSystemColor(SWT.COLOR_BLUE);
		PreferenceConverter.setDefault(store, "COLORNODE", color.getRGB()); //$NON-NLS-1$

		//Node highlight
		color = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GREEN);
		PreferenceConverter.setDefault(store, "COLORNODE_HIGHLIGHT", color.getRGB()); //$NON-NLS-1$

		//Node mouse over
		color = Display.getDefault().getSystemColor(SWT.COLOR_YELLOW);
		PreferenceConverter.setDefault(store, "COLORNODE_MOUSE", color.getRGB()); //$NON-NLS-1$

		//Arc color
		color = Display.getDefault().getSystemColor(SWT.COLOR_BLUE);
		PreferenceConverter.setDefault(store, "COLORARC", color.getRGB()); //$NON-NLS-1$

		//Arc highlight
		color = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GREEN);
		PreferenceConverter.setDefault(store, "COLORARC_HIGHLIGHT", color.getRGB()); //$NON-NLS-1$
	}

	/**
	 * Remise a zero des preferences du plugin
	 */
	public final void setDefaultPreference() {
		Coloane.getDefault().getPreferenceStore().setValue("LOGIN", getParam("LOGIN"));
		Coloane.getDefault().getPreferenceStore().setValue("SERVER", getParam("SERVER"));
		Coloane.getDefault().getPreferenceStore().setValue("IP", getParam("IP"));
		Coloane.getDefault().getPreferenceStore().setValue("PORT", getParam("PORT"));
	}

	/**
	 * Change la valeur d'un preference
	 * @param key La clef de la propriete a changer
	 * @param value La nouvelle valeur a attribuer
	 */
	public final void setPreference(String key, String value) {
		Coloane.getDefault().getPreferenceStore().setValue(key, value);
	}

	/**
	 * Retourne la valeur d'une preference
	 * @param key La clef de la preference
	 * @return La valeur de la prefrence choisie
	 */
	public final String getPreference(String key) {
		return Coloane.getDefault().getPreferenceStore().getString(key);
	}

	/**
	 * Modifie le niveau de verbosite du log du Core
	 * @param niveau le nouveau niveau du log
	 */
	public static void setVerbosity(Level niveau) {
		coreLog.setLevel(niveau);
	}
}
