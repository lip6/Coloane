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
package fr.lip6.move.coloane.interfaces.exceptions;

/**
 * Exception that notifies something went wrong inside a plugin.<br>
 * Two things are detailed:
 * <ul>
 * 	<li>The plugin name</li>
 * 	<li>The reason of the exception</li>
 * </ul>
 *
 * @author Jean-Baptiste Voron
 */
public class PluginException extends ExtensionException {

	/** Serialization stuff */
	private static final long serialVersionUID = 1L;

	/** Plugin name */
	private String pluginName;

	/** Default constructor */
	public PluginException() {
		super();
		this.pluginName = "Unknown Plugin"; //$NON-NLS-1$
	}

	/**
	 * Constructor
	 * @param message The exception reason
	 */
	public PluginException(final String message) {
		super(message);
		this.pluginName = "Unknown Plugin"; //$NON-NLS-1$
	}

	/**
	 * Constructor
	 * @param pluginName The plugin name
	 * @param message The exception reason
	 */
	public PluginException(final String pluginName, final String message) {
		super(message);
		this.pluginName = pluginName; //$NON-NLS-1$
	}

	/**
	 * @return The name of the plugin that has raised the exception
	 */
	public final String getPluginName() {
		return this.pluginName;
	}
}
