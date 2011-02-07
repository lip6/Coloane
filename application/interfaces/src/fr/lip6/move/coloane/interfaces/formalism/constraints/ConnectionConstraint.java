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
package fr.lip6.move.coloane.interfaces.formalism.constraints;

import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * Define a link-constraint between 2 formalism elements.<br>
 * This constraint forbids the connection between a <code>source</code> element and a <code>target</code> element.
 *
 * @author Jean-Baptiste Voron
 */
public class ConnectionConstraint implements IConstraint, IConstraintLink, IExecutableExtension {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Source element */
	private String source;

	/** Target element */
	private String target;

	/** Arc type */
	private String arcType;

	/**
	 * Constructor used by the FormalismManager.<br>
	 * The class is initialize by the method {@link #setInitializationData(IConfigurationElement, String, Object)}
	 * @see {@link FormalismManager}
	 */
	public ConnectionConstraint() {	}

	/** {@inheritDoc} */
	public final boolean isSatisfied(INode source, INode target, IArcFormalism arcFormalism) {
		// Check that source and target are different from the constrained elements
		if ((this.arcType == null) || (arcFormalism.getName().equals(this.arcType))) {
			return (!(this.source.equals(source.getNodeFormalism().getName())) || !(this.target.equals(target.getNodeFormalism().getName())));
		// For all other cases, the connection is allowed
		} else {
			return true;
		}
	}

	/** {@inheritDoc} */
	public final void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		// Fetch parameters from the description
		Map<String, String> myParams = new HashMap<String, String>();
		IConfigurationElement[] parameters = config.getChildren(PARAMETER_ID);
		for (IConfigurationElement param : parameters) {
			if ((param.getAttribute(PARAMETER_NAME) != null) && (param.getAttribute(PARAMETER_VALUE) != null)) {
				myParams.put(param.getAttribute(PARAMETER_NAME), param.getAttribute(PARAMETER_VALUE));
			}
		}

		// Check that mandatory parameters have been provided
		if (!myParams.containsKey("source") || !myParams.containsKey("target")) {  //$NON-NLS-1$//$NON-NLS-2$
			LOGGER.warning("Missing parameter (\"source\" or \"target\") in constraint definition..."); //$NON-NLS-1$
			IStatus errorStatus = new Status(IStatus.ERROR, "fr.lip6.move.coloane.extensions.tools", "Missing parameter (\"source\" or \"target\") in constraint definition...");  //$NON-NLS-1$ //$NON-NLS-2$
			throw new CoreException(errorStatus);
		}

		this.source = myParams.get("source"); //$NON-NLS-1$
		this.target = myParams.get("target"); //$NON-NLS-1$
		this.arcType = myParams.get("arcType"); //$NON-NLS-1$
 	}
}
