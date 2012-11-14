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
package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.ui.files.FormalismHandler;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.interfaces.exceptions.PluginException;
import fr.lip6.move.coloane.interfaces.extensions.IExample;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * Allow plug-ins to define some model examples.<br>
 * Those examples will be used and displayed during the creation wizard.
 *
 * @author Jean-Baptiste Voron
 */
public final class ExampleExtension {

	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * Extension attributes
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.examples"; //$NON-NLS-1$
	private static final String FROM_CODE = "example"; //$NON-NLS-1$
	private static final String FROM_FILE = "example-from-file"; //$NON-NLS-1$
	private static final String NAME = "name"; //$NON-NLS-1$
	private static final String BUILDER = "build"; //$NON-NLS-1$
	private static final String DESCRIPTION = "description"; //$NON-NLS-1$
	private static final String FORMALISM = "formalism"; //$NON-NLS-1$
	private static final String FILE = "file"; //$NON-NLS-1$
	
	/** Private constructor */
	private ExampleExtension() { }
	
	/**
	 * Looks for all example models available for this formalism
	 * @param formalism The formalism
	 * @return a map with the name and description of each example
	 */
	public static Map<String, String> getModelsName(IFormalism formalism) {
		Map<String, String> result = new HashMap<String, String>();
		for (IConfigurationElement contribution: Arrays.asList(Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID))) {
			try {
				boolean keep = false;
				String type = contribution.getName();
				// Select only the models that correspond to the formalism:
				if (FROM_CODE.equals(type)) {
					if (contribution.getAttribute(FORMALISM).equalsIgnoreCase(formalism.getName())) {
						keep = true;
					}
				} else if (FROM_FILE.equals(type)) {
					Bundle bundle = Platform.getBundle(contribution.getDeclaringExtension().getNamespaceIdentifier());
					URI uri = bundle.findEntries("/", contribution.getAttribute(FILE), true).nextElement().toURI(); //$NON-NLS-1$
					IFormalism f = ModelLoader.loadFromXML(uri, new FormalismHandler()).getFormalism();
					if (f.getId().equals(formalism.getId())) {
						keep = true;
					}
				} else {
					LOGGER.warning("Unknown element '" + type + "' for example extension point.");  //$NON-NLS-1$//$NON-NLS-2$
				}
				if (keep) {
					result.put(contribution.getAttribute(NAME), contribution.getAttribute(DESCRIPTION));
				}
			} catch (URISyntaxException e) {
				LOGGER.warning("'" + contribution.getAttribute(FILE) + "' in an invalid URI, given for an example model file."); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		return result;
	}

	/**
	 * Create the chosen example model
	 * @param name The model name
	 * @param formalism The formalism
	 * @return The associated graph object
	 * @throws ColoaneException If something went wrong
	 */
	public static IGraph getModel(String name, IFormalism formalism) throws ColoaneException {
		for (IConfigurationElement contribution: Arrays.asList(Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID))) {
			try {
				if (contribution.getAttribute(NAME).equals(name)) {
					String type = contribution.getName();
					if (FROM_CODE.equals(type)) {
						IExample convertInstance = (IExample) contribution.createExecutableExtension(BUILDER);
						return convertInstance.buildModel(formalism);
					} else if (FROM_FILE.equals(type)) {
						Bundle bundle = Platform.getBundle(contribution.getDeclaringExtension().getNamespaceIdentifier());
						URI uri = bundle.findEntries("/", contribution.getAttribute(FILE), true).nextElement().toURI(); //$NON-NLS-1$
						return ModelLoader.loadGraphFromXML(uri);
					} else {
						LOGGER.warning("Unknown element '" + type + "' for example extension point.");  //$NON-NLS-1$//$NON-NLS-2$
					}
				}
			} catch (PluginException e) {
				throw new ColoaneException("Plugin error " + e.getMessage() + "[" + e.getPluginName() + "]");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
			} catch (CoreException e) {
				throw new ColoaneException("Could load example model with name '" + name + "'.");  //$NON-NLS-1$//$NON-NLS-2$
			} catch (URISyntaxException e) {
				throw new ColoaneException("Could not load example from file '" + name + "', because it is an invalid URI.");  //$NON-NLS-1$//$NON-NLS-2$
			}
		}
		throw new ColoaneException("Could not find any example model with name '" + name + "'.");  //$NON-NLS-1$//$NON-NLS-2$
	}
}
