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
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.tools.its;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "fr.lip6.move.coloane.tools.its"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	public enum Tool {Reach, CTL, LTL};
	private static URI toolUri [] = new URI [3];
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/** {@inheritDoc} */
	public final void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/** {@inheritDoc} */
	public final void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}


	private static final Logger log = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	
	
	public static URI getProgramURI(Tool tool) throws IOException {
		if (toolUri[tool.ordinal()] == null) {
			URL tmpURL = FileLocator.toFileURL(Activator.getDefault().getBundle().getResource("bin/its-reach-" + getArchOS()));

			// use of the multi-argument constructor for URI in order to escape appropriately illegal characters
			URI uri;
			try {
				uri = new URI(tmpURL.getProtocol(), tmpURL.getPath(), null);
			} catch (URISyntaxException e) {
				throw new IOException("Could not create a URI to access the binary tool :", e);
			}
			 toolUri[tool.ordinal()] = uri;
			 log.fine("Location of the binary : " + toolUri);

			File crocExec = new File(uri);
			if (!crocExec.setExecutable(true)) {
				log.severe("unable to make the command-line tool executable [" + toolUri + "]");
				throw new IOException("unable to make the command-line tool executable");
			}		

		}
		return toolUri[tool.ordinal()];
	}
		/**
		 * A method that returns the correct Crocodile executable suffix, depending on the OS and architecture
		 * @return the suffix of the corresponding Crocodile executable
		 * @throws ServiceException if the suffix could not be determined
		 */
		private static String getArchOS() throws IOException {
			String osName = System.getProperty("os.name").toLowerCase();
			String archName = System.getProperty("os.arch").toLowerCase();
			if (osName.contains("mac os x")) {
				return "Darwin";
			} else if (osName.contains("linux")) {
				String result;
				result = "linux";
				if (archName.contains("64")) {
					result = result + "64";
					return result;
				} else if (archName.contains("86")) {
					result = result + "32";
					return result;
				}
			}

			throw new IOException("System architecture not supported : " + osName + " " + archName);
		}
	}
