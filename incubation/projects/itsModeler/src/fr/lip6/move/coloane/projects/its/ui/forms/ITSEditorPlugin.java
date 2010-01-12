/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package fr.lip6.move.coloane.projects.its.ui.forms;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import java.io.File;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
/**
 * The main plugin class to be used in the desktop.
 */
public final class ITSEditorPlugin extends AbstractUIPlugin {
	public static final String IMG_FORM_BG = "formBg"; //$NON-NLS-1$
	public static final String IMG_LARGE = "large"; //$NON-NLS-1$
	public static final String IMG_HORIZONTAL = "horizontal"; //$NON-NLS-1$
	public static final String IMG_VERTICAL = "vertical"; //$NON-NLS-1$
	public static final String IMG_SAMPLE = "sample"; //$NON-NLS-1$
	public static final String IMG_WIZBAN = "wizban"; //$NON-NLS-1$
	public static final String IMG_LINKTO_HELP = "linkto_help"; //$NON-NLS-1$
	public static final String IMG_HELP_TOPIC = "help_topic"; //$NON-NLS-1$
	public static final String IMG_CLOSE = "close"; //$NON-NLS-1$
	public static final String IMG_VARIABLE = "variable";
	public static final String IMG_REFRESH = "refresh";
	public static final String IMG_USETVAR = "variable_unset";
	public static final String IMG_SETVAR = "variable_set";
	public static final String IMG_RESULTOK = "result_ok";
	public static final String IMG_RESULTNOK = "result_nok";
	public static final String IMG_REACH_SERVICE = "reach_service";
	public static final String IMG_COMPOSITE = "composite_formalism";
	public static final String IMG_TPNFORM = "tpn_formalism";
	public static final String IMG_INSTANCE = "instance";

	
	public static final String ITS_REACH_NAME = "its-reach";
	public static final String ITS_CTL_NAME = "its-ctl";

	
	private static String ID = ITSEditorPlugin.class.getPackage().getName();

	//The shared instance.
	private static ITSEditorPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
	private FormColors formColors;

	/**
	 * The constructor.
	 */
	public ITSEditorPlugin() {
		plugin = this;
		System.out.println("<<<< ITS >>>>");
		try {
			resourceBundle = ResourceBundle
			.getBundle("org.eclipse.ui.forms.examples.internal.ExamplesPluginResources"); //$NON-NLS-1$
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}
	
	/**
	 * @return the ID
	 */
	public static String getID() {
		return ID;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initializeImageRegistry(ImageRegistry registry) {
		registerImage(registry, IMG_FORM_BG, "form_banner.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_LARGE, "large_image.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_HORIZONTAL, "th_horizontal.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_VERTICAL, "th_vertical.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_SAMPLE, "sample.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_WIZBAN, "newprj_wiz.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_LINKTO_HELP, "linkto_help.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_HELP_TOPIC, "help_topic.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_CLOSE, "close_view.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_VARIABLE, "variable_tab.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_REFRESH, "refresh.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_SETVAR, "set_variable.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_USETVAR, "unset_variable.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_RESULTOK, "success_check.gif"); //$NON-NLS-1$
		registerImage(registry, IMG_RESULTNOK, "error_check.gif"); //$NON-NLS-1$
		
		// images stolen from other plugins
		registry.put(IMG_REACH_SERVICE, AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/elcl16/progress_rem.gif")); //$NON-NLS-1$ //$NON-NLS-2$

		// images grabbed from coloane formalisms
		IFormalism f = FormalismManager.getInstance().getFormalismById("ITS Composite");
		registry.put(IMG_COMPOSITE, ImageDescriptor.createFromFile(Coloane.class, f.getImageName()));

		String img = f.getMasterGraph().getElementFormalism("instance").getGraphicalDescription().getIcon24px();
		registry.put(IMG_INSTANCE, ImageDescriptor.createFromFile(Coloane.class, img));
		
		f = FormalismManager.getInstance().getFormalismById("Time Petri Net");
		registry.put(IMG_TPNFORM, ImageDescriptor.createFromFile(Coloane.class, f.getImageName()));
	
	
	}

	/**
	 * Add an image to registry
	 * @param registry the referential
	 * @param key the image id
	 * @param fileName the image file path
	 */
	private void registerImage(ImageRegistry registry, String key,
			String fileName) {
		try {
			IPath path = new Path("icons/" + fileName); //$NON-NLS-1$
			URL url = FileLocator.find(getBundle(), path, null);
			if (url != null) {
				ImageDescriptor desc = ImageDescriptor.createFromURL(url);
				registry.put(key, desc);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * returns form colors of a display (singleton factory)
	 * @param display the display
	 * @return the new formcolors or existing one
	 */
	public FormColors getFormColors(Display display) {
		if (formColors == null) {
			formColors = new FormColors(display);
			formColors.markShared();
		}
		return formColors;
	}
	/**
	 * Returns the shared instance.
	 * @return sole instance
	 */
	public static ITSEditorPlugin getDefault() {
		return plugin;
	}
	/**
	 * Returns the workspace instance.
	 * @return sole instance
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}
	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 * @param key to the bundle
	 * @return the id of a bundle
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = ITSEditorPlugin.getDefault().getResourceBundle();
		try {
			// CHECKSTYLE OFF
			return (bundle != null ? bundle.getString(key) : key);
			// CHECKSTYLE ON
		} catch (MissingResourceException e) {
			return key;
		}
	}
	/**
	 * Returns the plugin's resource bundle
	 * @return my own resource bundle
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		try {
			if (formColors != null) {
				formColors.dispose();
				formColors = null;
			}
		} finally {
			super.stop(context);
		}
	}
	/**
	 * Great user API to get icons
	 * @param key uri of the image
	 * @return an image
	 */
	public Image getImage(String key) {
		return getImageRegistry().get(key);
	}
	
	/**
	 * Great user API to get icons
	 * @param key uri of the image
	 * @return an image
	 */
	public ImageDescriptor getImageDescriptor(String key) {
		return getImageRegistry().getDescriptor(key);
	}
	
	/**
	 * Returns whether the given file is executable. Depending on the platform
	 * we might not get this right.
	 * @param file the file to test
	 * @return true if executable is detected with certitude
	 */
	public static boolean isExecutable(File file) {
		if (!file.isFile()) {
			return false;
		}
		if (Platform.getOS().equals(Platform.OS_WIN32)) {
			// executable attribute is a *ix thing, on Windows all files are
			// executable
			return true;
		}
		IFileStore store = EFS.getLocalFileSystem().fromLocalFile(file);
		if (store == null) {
			return false;
		}
		return store.fetchInfo().getAttribute(EFS.ATTRIBUTE_EXECUTABLE);
	}

	public void setITSReachPath(String text) {
		setPreference(ITS_REACH_NAME,text);
	}
	public void setITSCTLPath(String text) {
		setPreference(ITS_CTL_NAME,text);		
	}

	
	/** Grab the its-reach path from prefs.
	 * 
	 * @return the path
	 */
	public final IPath getITSReachPath() {
		return new Path(getPreference(ITS_REACH_NAME));
	}

	public IPath getITSCTLPath() {
		return new Path(getPreference(ITS_CTL_NAME));
	}

	
	/** Returns the preference with the given name
	 * @param preferenceName the pref
	 * @return the value */
	public final String getPreference(String preferenceName) {
		Preferences node =
						Platform.getPreferencesService().getRootNode().node(InstanceScope.SCOPE).node(
										ITSEditorPlugin.ID);
		return node.get(preferenceName, null);
	}

	
	/**
	 * Sets the given preference to the given value.
	 * @param preferenceName the preference
	 * @param value the value to set
	 */
	private void setPreference(String preferenceName, String value) {
		IEclipsePreferences root = Platform.getPreferencesService().getRootNode();
		Preferences node = root.node(InstanceScope.SCOPE).node(ID);
		node.put(preferenceName, value);
		try {
			node.flush();
		} catch (BackingStoreException e) {
			warning("Error updating preferences."+e);
		}
	}

	public static void warning(String e) {
		Logger.getLogger(ID).warning(e);		
	}



}
