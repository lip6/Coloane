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

import fr.lip6.move.coloane.interfaces.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * Describe an export extension.<br>
 * This kind of extension is used to translate a model into a dedicated format.<br>
 * Export plug-ins must provide :
 * <ul>
 * 	<li>a list of supported formalism</li>
 * 	<li>a wizard identifier in charge of creating a file for the exported model</li>
 * 	<li>an extension for exported model</li>
 *  <li>a class that does the job</li>
 * </ul>
 *
 * @see Wizard
 */
public final class ExportToExtension {

	/**
	 * Extension attributes
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.exports"; //$NON-NLS-1$

	private static final String WIZREF_EXTENSION = "wizard_id"; //$NON-NLS-1$
	private static final String CLASS_EXTENSION = "class"; //$NON-NLS-1$
	private static final String EXT_EXTENSION = "extension"; //$NON-NLS-1$
	private static final String FORMALISMS_EXTENSION = "id"; //$NON-NLS-1$

	/**
	 * Constructor
	 */
	private ExportToExtension() { }

	/**
	 * Tells if a formalism can be exported thanks to this export extension.
	 * @param exportType The name of the wizard
	 * @param formalism The formalism to export to
	 * @return <code>true</code> if the export operation is supported by the export extension
	 */
	public static boolean canPerform(String exportType, IFormalism formalism) {
		// If the export extension doesn't specify a formalism, we assume that it's a generic exporter
		boolean canPerform = true;

		// Fetch all available export extensions
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		for (IConfigurationElement contribution : contributions) {
			// Select only relevant export extension. Those that match the wizard
			if (contribution.getAttribute(WIZREF_EXTENSION).equals(exportType)) {
				// If the formalism is included in the list of supported formalism
				for (IConfigurationElement child : contribution.getChildren()) {
					if (child.getAttribute(FORMALISMS_EXTENSION).equals(formalism.getId())) {
						return true;
					}
					canPerform = false;
				}
			}
		}
		return canPerform;
	}

	/**
	 * Looks for the extension to add to the exported file.
	 * @param exportType The wizard reference (used to find the correct extension among all available ones)
	 * @return The extension or an empty string if no extension has been found
	 */
	public static String findExtension(String exportType) {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		for (int i = 0; i < contributions.length; i++) {
			if (contributions[i].getAttribute(WIZREF_EXTENSION).equals(exportType)) {
				return contributions[i].getAttribute(EXT_EXTENSION);
			}
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * Build an export worker
	 * @param exportType The wizard reference (used to find the correct extension among all available ones)
	 * @return The export builder that will be able to transform the model
	 * @throws CoreException Something went wring during the extension initialization
	 */
	public static IExportTo createConvertInstance(String exportType) throws CoreException {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		IConfigurationElement convertContribution = null;
		for (int i = 0; i < contributions.length; i++) {
			if (contributions[i].getAttribute(WIZREF_EXTENSION).equals(exportType)) {
				convertContribution = contributions[i];
				break;
			}
		}

		IExportTo convertInstance = null;
		if (convertContribution != null) {
			convertInstance = (IExportTo) convertContribution.createExecutableExtension(CLASS_EXTENSION);
		}
		return convertInstance;
	}
}
