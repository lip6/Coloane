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
package fr.lip6.move.coloane.extensions.pnexamples;

import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.interfaces.exceptions.PluginException;
import fr.lip6.move.coloane.interfaces.extensions.IExample;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.net.URISyntaxException;
import java.net.URL;

/**
 * Create the PHILOSOPHER classical net from a resource file
 *
 * @author Jean-Baptiste Voron
 */
public class Philosophers implements IExample {

	/**
	 * Default constructor
	 */
	public Philosophers() { }

	/**
	 * {@inheritDoc}
	 */
	public final IGraph buildModel(IFormalism formalism) throws PluginException {
		URL xmlExample = Activator.class.getResource("/src/main/resources/Philo.model");
		IGraph model = null;
		try {
			model = ModelLoader.loadGraphFromXML(xmlExample.toURI());
		} catch (URISyntaxException e) {
			throw new PluginException(Activator.PLUGIN_ID, "Bad URI: " + e.getMessage());
		}
		return model;
	}
}
