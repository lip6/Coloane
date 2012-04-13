/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.main;

import fr.lip6.move.coloane.interfaces.utils.ColoaneLogFormatter;
import fr.lip6.move.coloane.interfaces.utils.ColoaneLogHandler;
import fr.lip6.move.coloane.interfaces.utils.ConsoleHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Coloane Plug-in : main class
 */
public class Coloane extends AbstractUIPlugin {

	/** Plug-in singleton instance */
	private static Coloane instance = null;

	/** Log manager */
	private static Logger LOGGER;

	/** Build the plug-in */
	public Coloane() { instance = this; }

	/**
	 * Fetch the unique instance of Coloane plug-in
	 * @return Coloane plug-in instance
	 */
	public static Coloane getInstance() {
		return instance;
	}

	/**
	 * This is the first called method when the Coloane perspective is loaded
	 * @param context System parameters
	 * @see AbstractUIPlugin
	 * @exception Exception if super.start() does not work.
	 */
	@Override
	public final void start(BundleContext context) throws Exception {
		super.start(context);

		// Init the log manager
		this.initializeLogger();
		this.initializeDefaultPreferences(getPreferenceStore());
		LOGGER.config("-- Initialisation du plugin Coloane --"); //$NON-NLS-1$
		// Send information about the configuration
		if (Boolean.parseBoolean(getInstance().getPreference("STATS_STATUS"))) { //$NON-NLS-1$
			this.sendProperties();
		}
	}

	/**
	 * To ease the debug of recurrent bugs, some information is very useful for developers.<br>
	 * Those are sent to the Coloane Dev Center (through Internet).<br>
	 * <ul>
	 * 	<li>Eclipse Platform Version</li>
	 * 	<li>OS Version / Architecture</li>
	 * 	<li>Window Manager</li>
	 * </ul>
	 */
	private void sendProperties() {
		new Thread(new Runnable() {
			public void run() {
				// Compute current eclipse platform version
				String eclipseVersion = null;
			    String aboutText = Platform.getProduct().getProperty("aboutText"); //$NON-NLS-1$
			    String pattern = "Version: (.*)\n"; //$NON-NLS-1$
			    Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(aboutText);
				if (m.find()) { eclipseVersion = m.group(1); }
				if (eclipseVersion == null) { LOGGER.warning("Your eclipse version cannot be computed... Perhaps an installation problem ?"); }  //$NON-NLS-1$

				// Build the QueryString
				StringBuilder querystring = new StringBuilder();
				querystring.append("?platform=eclipse_" + eclipseVersion); //$NON-NLS-1$
				querystring.append("&os=" + Platform.getOS() + "-" + Platform.getOSArch()); //$NON-NLS-1$ //$NON-NLS-2$
				querystring.append("&wm=" + Platform.getWS()); //$NON-NLS-1$

				String requestUrl = "http://coloane.lip6.fr/track.php" + querystring.toString(); //$NON-NLS-1$
				try {
			        URL url = new URL(requestUrl.toString());
			        InputStream is = url.openConnection().getInputStream();
			        is.close();
			        LOGGER.info("Information about your configuration has been sent to the coloane developper center !"); //$NON-NLS-1$
				} catch (MalformedURLException e) {
					LOGGER.warning("Track information are invalid : " + querystring); //$NON-NLS-1$
				} catch (IOException e) {
					LOGGER.warning("Error while sending track information"); //$NON-NLS-1$
				}
			}
		}).start();
	}

	/**
	 * What to do when Coloane plug-in is stopped ?
	 * @param context System parameters
	 * @exception Exception if super.stop() fails
	 */
	@Override
	public final void stop(BundleContext context) throws Exception {
		super.stop(context);
		LOGGER.config("-- Arret du plugin Coloane --"); //$NON-NLS-1$
		instance = null;
	}

	/**
	 * Fetch the value associated to a plug-in property<br>
	 * All the properties are stored in a single file: plugin.properties<br>
	 * @param key parameter identifier
	 * @return parameter value or <code>null</code> if the parameter does not exist
	 */
	public static String getParam(String key) {
		try {
			return Platform.getResourceBundle(instance.getBundle()).getString(key);
		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * Display an error dialog box
	 * @param msg the message to be displayed
	 */
	public static void showErrorMsg(String msg) {
		LOGGER.warning("Affichage d'un message d'erreur : " + msg); //$NON-NLS-1$
		IWorkbenchWindow activeWorkbenchWindow = instance.getWorkbench().getActiveWorkbenchWindow();
		if (activeWorkbenchWindow != null) {
			MessageDialog.openError(activeWorkbenchWindow.getShell(), "Coloane Error", msg); //$NON-NLS-1$
		}
	}

	/**
	 * Display a warning dialog box
	 * @param msg the message to be displayed
	 */
	public static void showWarningMsg(String msg) {
		LOGGER.fine("Affichage d'un message de warning : " + msg); //$NON-NLS-1$
		MessageDialog.openWarning(instance.getWorkbench().getActiveWorkbenchWindow().getShell(), "Coloane Warning", msg); //$NON-NLS-1$
	}

	/**
	 * Fetch the graphical parent of the Coloane plug-in
	 * @return Composite the container or <code>null</code> if something went wrong
	 */
	public static Composite getParent() {
		if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
			return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		} else {
			return null;
		}
	}

	/**
	 * Init the log manager
	 */
	private void initializeLogger() {
		LOGGER = Logger.getLogger("fr.lip6.move.coloane"); //$NON-NLS-1$
		// TODO: Should be defined in a property page...
		LOGGER.setLevel(Level.FINE); // Finer/Finest messages are discarded

		// Log messages are displayed in the system console
		LOGGER.addHandler(new ConsoleHandler());

		// And also dump into a file
		try {
			ColoaneLogHandler handler = ColoaneLogHandler.getInstance();
			ColoaneLogFormatter format = new ColoaneLogFormatter();
			format.setVersion(getVersion());
			handler.setFormatter(format);
			LOGGER.addHandler(handler);
		} catch (IOException ioe) {
			System.err.println("FileHandler cannot be instanciated... Please contact the Dev Team"); //$NON-NLS-1$
			showErrorMsg("Error while creating the log file. Please report this error to the DevTeam"); //$NON-NLS-1$
		} catch (SecurityException se) {
			System.err.println("FileHandler cannot be instanciated... Please contact the Dev Team"); //$NON-NLS-1$
			showErrorMsg("Error while creating the log file. Please report this error to the DevTeam"); //$NON-NLS-1$
		}

		LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	}

	/**
	 * Get Coloane core plug-in version
	 * @return the current version
	 */
	private String getVersion() {
		return (String) getBundle().getHeaders().get("Bundle-Version"); //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	@Override
	protected final void initializeDefaultPreferences(IPreferenceStore store) {
		store.setDefault("LOGIN", getParam("LOGIN_DEFAULT")); //$NON-NLS-1$ //$NON-NLS-2$

		// Server Name, IP and Port for FrameKit
		store.setDefault("SERVER", getParam("SERVER_DEFAULT")); //$NON-NLS-1$ //$NON-NLS-2$
		store.setDefault("IP", getParam("IP_DEFAULT")); //$NON-NLS-1$ //$NON-NLS-2$
		store.setDefault("PORT", getParam("PORT_DEFAULT")); //$NON-NLS-1$ //$NON-NLS-2$

		// Statistics Status
		store.setDefault("STATS_STATUS", false); //$NON-NLS-1$

		// Node color
		Color color = Display.getDefault().getSystemColor(SWT.COLOR_BLUE);
		PreferenceConverter.setDefault(store, "COLORNODE", color.getRGB()); //$NON-NLS-1$

		// Node highlight
		color = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GREEN);
		PreferenceConverter.setDefault(store, "COLORNODE_HIGHLIGHT", color.getRGB()); //$NON-NLS-1$

		// Node mouse over
		color = Display.getDefault().getSystemColor(SWT.COLOR_YELLOW);
		PreferenceConverter.setDefault(store, "COLORNODE_MOUSE", color.getRGB()); //$NON-NLS-1$

		// Arc color
		color = Display.getDefault().getSystemColor(SWT.COLOR_BLUE);
		PreferenceConverter.setDefault(store, "COLORARC", color.getRGB()); //$NON-NLS-1$

		// Arc line style
		store.setDefault("COLORARC_LINESTYLE", true); //$NON-NLS-1$

		// Arc highlight
		color = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GREEN);
		PreferenceConverter.setDefault(store, "COLORARC_HIGHLIGHT", color.getRGB()); //$NON-NLS-1$

		// Tip foreground
		RGB rgb = new RGB(240, 185, 183);
		PreferenceConverter.setDefault(store, "COLORTIP_FOREGROUND", rgb); //$NON-NLS-1$

		// Tip background
		rgb = new RGB(218, 80, 75);
		PreferenceConverter.setDefault(store, "COLORTIP_BACKGROUND", rgb); //$NON-NLS-1$
	}

	/**
	 * Reset plug-in connection properties
	 */
	public final void setDefaultPreference() {
		instance.getPreferenceStore().setValue("LOGIN", getParam("LOGIN")); //$NON-NLS-1$ //$NON-NLS-2$
		instance.getPreferenceStore().setValue("SERVER", getParam("SERVER")); //$NON-NLS-1$ //$NON-NLS-2$
		instance.getPreferenceStore().setValue("IP", getParam("IP")); //$NON-NLS-1$ //$NON-NLS-2$
		instance.getPreferenceStore().setValue("PORT", getParam("PORT")); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Set a preference
	 * @param key preference identifier
	 * @param value preference value
	 */
	public final void setPreference(String key, String value) {
		instance.getPreferenceStore().setValue(key, value);
	}

	/**
	 * Get a preference value
	 * @param key preference identifier
	 * @return preference value
	 */
	public final String getPreference(String key) {
		return instance.getPreferenceStore().getString(key);
	}

	/**
	 * Change the log details level
	 * @param level the new log level
	 */
	public static void setVerbosity(Level level) {
		LOGGER.setLevel(level);
	}
}
