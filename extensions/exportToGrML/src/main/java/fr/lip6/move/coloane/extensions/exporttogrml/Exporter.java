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

import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

/**
 * A simple class encapsulating an instance of an exporting class and the corresponding FML URL
 * 
 * @author Maximilien Colange
 *
 */
public final class Exporter {
	/**
	 * Extension attributes
	 */
	//private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.extensions.exporttogrml"; //$NON-NLS-1$
	//private static final String NAME_EXTENSION = "name"; //$NON-NLS-1$
	//private static final String DESCRIPTION_EXTENSION = "description"; //$NON-NLS-1$
	private static final String CLASS_EXTENSION = "class"; //$NON-NLS-1$
	private static final String FMLURL_EXTENSION = "fmlurl"; //$NON-NLS-1$
	
	private String formalismURL;
	private IGrMLExport instance;
	
	/**
	 * The constructor
	 * 
	 * @param contrib an extension contribution
	 * @throws CoreException if the creation of the instance of the exporting class fails
	 * @throws ServiceException if the formalism URL is not a correct URL
	 */
	Exporter(IConfigurationElement contrib) throws CoreException, ServiceException {
		String form = contrib.getAttribute(FMLURL_EXTENSION);
		// test whether the given formalism is a correct URL
		try {
			new URL(form);
		} catch (MalformedURLException e) {
			throw new ServiceException("provided FML \"" + form + "\" is not a valid URL : " + e.getMessage());
		}
		formalismURL = form;
		instance = (IGrMLExport) contrib.createExecutableExtension(CLASS_EXTENSION);
	}
	
	/**
	 * Accessor for <code>formalismURL</code>
	 * 
	 * @return formalismURL
	 */
	String getFormalism() {
		return formalismURL;
	}
	
	/**
	 * Accessor for <code>instance</code>
	 * 
	 * @return <code>instance</code>
	 */
	IGrMLExport getInstance() {
		return instance;
	}
}
