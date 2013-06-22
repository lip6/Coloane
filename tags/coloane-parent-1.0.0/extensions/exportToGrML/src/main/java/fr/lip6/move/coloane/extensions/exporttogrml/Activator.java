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
package fr.lip6.move.coloane.extensions.exporttogrml;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {
	// The plug-in ID
	public static final String PLUGIN_ID = "fr.lip6.move.coloane.extensions.exportToGrML";

    /**
     * Extension attributes
     */
    public static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.extensions.exportToGrML.exportGrML"; //$NON-NLS-1$
    public static final String NAME_EXTENSION = "name"; //$NON-NLS-1$
    public static final String DESCRIPTION_EXTENSION = "description"; //$NON-NLS-1$
    public static final String CLASS_EXTENSION = "class"; //$NON-NLS-1$
    public static final String FMLURL_EXTENSION = "fmlurl"; //$NON-NLS-1$
	
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	// The shared instance
	private static Activator plugin;
	
	private final Map<String, Exporter> contributionMap = new HashMap<String, Exporter>();

	/**
	 * The constructor
	 */
	public Activator() {
		super();
	}

	/** {@inheritDoc} */
	public final void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		for (int i = 0; i < contributions.length; i++) {
			String key = contributions[i].getAttribute(NAME_EXTENSION);
			LOGGER.info("Export to grml for the formalism '" + key + "'");
			contributionMap.put(key, new Exporter(contributions[i]));
		}

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

	/**
	 * Returns the map of contributions
	 * 
	 * @return the map of contributions
	 */
	public final Map<String, Exporter> getMap() {
		return contributionMap;
	}
}
