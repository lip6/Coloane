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
package fr.lip6.move.coloane.its.ui.forms;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
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
}
