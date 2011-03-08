/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id$
 */

package fr.lip6.move.coloane.extensions.examples.plugin;

import org.eclipse.ui.plugin.AbstractUIPlugin;


public class ColoaneExamplesPlugin
	extends AbstractUIPlugin {

	// The shared instance.
	private static ColoaneExamplesPlugin plugin;
	
	public ColoaneExamplesPlugin() {
		super();
		plugin = this;
	}
	
	/**
	 * Returns the shared instance.
	 */
	public static ColoaneExamplesPlugin getDefault() {
		return plugin;
	}
}
