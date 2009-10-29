package fr.lip6.move.coloane.core.main;

import fr.lip6.move.coloane.interfaces.utils.ColoaneLogFormatter;
import fr.lip6.move.coloane.interfaces.utils.ColoaneLogHandler;
import fr.lip6.move.coloane.interfaces.utils.ConsoleHandler;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Définition principale du plugin Coloane<br>
 * Création des sous-composants :
 * <ul>
 * 	<li>Le module de communications</li>
 * 	<li>Le module de gestion des sessions et formalismes</li>
 * 	<li>Le module de gestion de l'interface graphique</li>
 * </ul>
 */
public class Coloane extends AbstractUIPlugin {

	/** L'instance du plugin */
	private static Coloane instance;

	/** Journalisation du projet */
	private static Logger LOGGER;

	/**
	 * Constructeur du plugin
	 */
	public Coloane() {
		instance = this;
//		throw new NullPointerException("test");
	}

	/**
	 * Methode de lancement du plugin
	 * C'est la premiere methode a etre appelee lors du chargement d'une classe du plugin
	 * @param context Parametre systeme fourni par Eclipse
	 * @throws Exception Si quelque chose se passe mal lors de l'initialisation dans Eclipse
	 * @see AbstractUIPlugin
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin
	 */
	@Override
	public final void start(BundleContext context) throws Exception {
		super.start(context);

		// Initialisation du logger
		this.initializeLogger();
		LOGGER.config("-- Initialisation du plugin Coloane --"); //$NON-NLS-1$
	}

	/**
	 * Permet de recuperer le plugin
	 * @return une instance de Coloane
	 */
	public static Coloane getInstance() {
		return instance;
	}

	/**
	 * La méthode de fin de vie du plugin
	 * @param context Parametre système fourni par Eclipse
	 * @throws Exception si quelque chose se passe mal lors de l'arrêt du plugin
	 */
	@Override
	public final void stop(BundleContext context) throws Exception {
		super.stop(context);
		LOGGER.config("Arret du plugin"); //$NON-NLS-1$
		instance = null;
	}

	/**
	 * Recupere la valeur d'un paramètre dans le fichier de configuration (plugin.properties)
	 * @param key L'identifiant du parametre
	 * @return la valeur du paramètre demandé ou <code>null</code> si le paramètre n'existe pas
	 */
	public static String getParam(String key) {
		try {
			return Platform.getResourceBundle(instance.getBundle()).getString(key);
		} catch (NullPointerException ne) {
			return null;
		}
	}

	/**
	 * Affiche un message d'erreur sous forme de boite de dialogue
	 * @param msg Le message a afficher
	 */
	public static void showErrorMsg(String msg) {
		LOGGER.fine("Affichage d'un message d'erreur : " + msg); //$NON-NLS-1$
		MessageDialog.openError(instance.getWorkbench().getActiveWorkbenchWindow().getShell(), "Coloane Error", msg); //$NON-NLS-1$
	}

	/**
	 * Affiche un message d'avertissment sous forme de boite de dialogue
	 * @param msg Message a afficher
	 */
	public static void showWarningMsg(String msg) {
		LOGGER.fine("Affichage d'un message de warning : " + msg); //$NON-NLS-1$
		MessageDialog.openWarning(instance.getWorkbench().getActiveWorkbenchWindow().getShell(), "Coloane Warning", msg); //$NON-NLS-1$
	}

	/**
	 * Retourne le conteneur graphique de haut niveau
	 * @return Composite Le conteneur parent ou null
	 */
	public static Composite getParent() {
		if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
			return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		} else {
			return null;
		}
	}

	/**
	 * Initialisation du logger d'evenements
	 */
	private void initializeLogger() {
		LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
		LOGGER.setLevel(Level.ALL); // On loggue tout !

		// Les logs sont affichés dans la console
		LOGGER.addHandler(new ConsoleHandler());

		// Les logs sont enregistrés dans un fichier.
		try {
			ColoaneLogHandler handler = ColoaneLogHandler.getInstance();
			ColoaneLogFormatter format = new ColoaneLogFormatter();
			format.setVersion(getVersion());
			handler.setFormatter(format);
			LOGGER.addHandler(handler);
		} catch (IOException ioe) {
			System.err.println("FileHandler cannot be instanciated... Please contact the dev team"); //$NON-NLS-1$
		} catch (SecurityException se) {
			System.err.println("FileHandler cannot be instanciated... Please contact the dev team"); //$NON-NLS-1$
		}
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

		//Tip foreground
		RGB rgb = new RGB(240, 185, 183);
		PreferenceConverter.setDefault(store, "COLORTIP_FOREGROUND", rgb); //$NON-NLS-1$

		//Tip background
		rgb = new RGB(218, 80, 75);
		PreferenceConverter.setDefault(store, "COLORTIP_BACKGROUND", rgb); //$NON-NLS-1$
	}

	/**
	 * Remise a zero des preferences du plugin
	 */
	public final void setDefaultPreference() {
		instance.getPreferenceStore().setValue("LOGIN", getParam("LOGIN")); //$NON-NLS-1$ //$NON-NLS-2$
		instance.getPreferenceStore().setValue("SERVER", getParam("SERVER")); //$NON-NLS-1$ //$NON-NLS-2$
		instance.getPreferenceStore().setValue("IP", getParam("IP")); //$NON-NLS-1$ //$NON-NLS-2$
		instance.getPreferenceStore().setValue("PORT", getParam("PORT")); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Change la valeur d'un preference
	 * @param key La clef de la propriete a changer
	 * @param value La nouvelle valeur a attribuer
	 */
	public final void setPreference(String key, String value) {
		instance.getPreferenceStore().setValue(key, value);
	}

	/**
	 * Retourne la valeur d'une preference
	 * @param key La clef de la preference
	 * @return La valeur de la prefrence choisie
	 */
	public final String getPreference(String key) {
		return instance.getPreferenceStore().getString(key);
	}

	/**
	 * Modifie le niveau de verbosite du log du Core
	 * @param niveau le nouveau niveau du log
	 */
	public static void setVerbosity(Level niveau) {
		LOGGER.setLevel(niveau);
	}
}
